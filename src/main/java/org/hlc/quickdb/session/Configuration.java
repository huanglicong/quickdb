/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hlc.quickdb.session;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.hlc.quickdb.builder.BuilderException;
import org.hlc.quickdb.builder.DeleteSqlBuilder;
import org.hlc.quickdb.builder.InsertSqlBuilder;
import org.hlc.quickdb.builder.SelectSqlBuilder;
import org.hlc.quickdb.builder.SqlBuilder;
import org.hlc.quickdb.builder.SqlCommandType;
import org.hlc.quickdb.builder.SqlSource;
import org.hlc.quickdb.builder.UpdateSqlBuilder;
import org.hlc.quickdb.builder.UserDefinedSqlBuilder;
import org.hlc.quickdb.executor.Executor;
import org.hlc.quickdb.executor.JdbcExecutor;
import org.hlc.quickdb.executor.parameter.handler.AbstractParameterResolver;
import org.hlc.quickdb.executor.parameter.handler.ParameterResolverFactory;
import org.hlc.quickdb.executor.result.ResultHandler;
import org.hlc.quickdb.executor.result.ResultHandlerFactory;
import org.hlc.quickdb.executor.statement.CallableStatementHandler;
import org.hlc.quickdb.executor.statement.PreparedStatementHandler;
import org.hlc.quickdb.executor.statement.StatementHandler;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.transaction.Transaction;
import org.hlc.quickdb.transaction.TransactionFactory;
import org.hlc.quickdb.type.TypeHandler;
import org.hlc.quickdb.type.TypeHandlerRegistry;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午9:35:06
 */
public class Configuration {

	/** The metadata repository. */
	private final Map<String, TableMetadata> metadataRepository = new LinkedHashMap<String, TableMetadata>();

	private final TransactionFactory transactionFactory;
	private final DataSource dataSource;
	private TypeHandlerRegistry typeHandlerRegistry;
	private ParameterResolverFactory parameterResolverFactory;
	private ResultHandlerFactory resultHandlerFactory;

	private boolean capital = true;

	public Configuration(TransactionFactory transactionFactory, DataSource dataSource) {

		this.transactionFactory = transactionFactory;
		this.dataSource = dataSource;
		this.typeHandlerRegistry = new TypeHandlerRegistry();
		this.parameterResolverFactory = new ParameterResolverFactory(this);
		this.resultHandlerFactory = new ResultHandlerFactory(this);
	}

	public TableMetadata put(Class<?> arg0, TableMetadata arg1) {
		return metadataRepository.put(arg0.getName(), arg1);
	}

	public SqlBuilder newSqlBuilder(Class<?> type, Object params, boolean selective, SqlCommandType commandType) {

		TableMetadata table = findTableMetadata(type);
		SqlBuilder sqlBuilder = null;
		if (commandType == SqlCommandType.INSERT) {
			sqlBuilder = new InsertSqlBuilder(params, table, selective, capital);
		} else if (commandType == SqlCommandType.UPDATE) {
			sqlBuilder = new UpdateSqlBuilder(params, table, selective, capital);
		} else if (commandType == SqlCommandType.DELETE) {
			sqlBuilder = new DeleteSqlBuilder(params, table, selective);
		} else if (commandType == SqlCommandType.SELECT) {
			sqlBuilder = new SelectSqlBuilder(params, table, selective);
		}
		return sqlBuilder;
	}

	public SqlBuilder newSqlBuilder(String sql, Object params) {

		return new UserDefinedSqlBuilder(sql, params, this);
	}

	public TableMetadata findTableMetadata(Class<?> type) {

		TableMetadata table = metadataRepository.get(type.getName());
		if (table == null) {
			throw new BuilderException("没有找到" + type.getName() + "的配置");
		}
		return table;
	}

	public TypeHandler<?> findTypeHandler(Class<?> type) {
		return typeHandlerRegistry.getHandler(type);
	}

	public Executor newExecutor() {
		Transaction transaction = transactionFactory.newTransaction(dataSource);
		return new JdbcExecutor(transaction, this);
	}

	public StatementHandler newStatementHandler(SqlSource sqlSource) {
		return new PreparedStatementHandler(sqlSource);
	}

	public StatementHandler newCallableStatement(SqlSource sqlSource) {
		return new CallableStatementHandler(sqlSource);
	}

	public <E> ResultHandler<E> newResultHandler(Class<E> resultType) {

		return resultHandlerFactory.createResultHandler(resultType);
	}

	public AbstractParameterResolver newParameterResolver(Class<?> paramType, Object target) {
		return parameterResolverFactory.createParameterResolver(paramType, target);
	}

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public TransactionFactory getTransactionFactory() {
		return transactionFactory;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public TypeHandlerRegistry getTypeHandlerRegistry() {
		return typeHandlerRegistry;
	}

	public void setTypeHandlerRegistry(TypeHandlerRegistry typeHandlerRegistry) {
		this.typeHandlerRegistry = typeHandlerRegistry;
	}

}
