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
import org.hlc.quickdb.builder.UpdateSqlBuilder;
import org.hlc.quickdb.executor.Executor;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.transaction.Transaction;
import org.hlc.quickdb.transaction.TransactionFactory;

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

	private boolean capital = true;

	public Configuration(TransactionFactory transactionFactory, DataSource dataSource) {
		super();
		this.transactionFactory = transactionFactory;
		this.dataSource = dataSource;
	}

	public TableMetadata put(Class<?> arg0, TableMetadata arg1) {
		return metadataRepository.put(arg0.getName(), arg1);
	}

	public SqlBuilder newSqlBuilder(Class<?> type, Object record, boolean selective, SqlCommandType commandType) {

		TableMetadata table = findTableMetadata(type);
		SqlBuilder sqlBuilder = null;
		if (commandType == SqlCommandType.INSERT) {
			sqlBuilder = new InsertSqlBuilder(record, table, selective, capital);
		} else if (commandType == SqlCommandType.UPDATE) {
			sqlBuilder = new UpdateSqlBuilder(record, table, selective, capital);
		} else if (commandType == SqlCommandType.DELETE) {
			sqlBuilder = new DeleteSqlBuilder(table, capital);
		} else if (commandType == SqlCommandType.SELECT) {
			sqlBuilder = new SelectSqlBuilder(table, capital);
		}
		return sqlBuilder;
	}

	public TableMetadata findTableMetadata(Class<?> type) {

		TableMetadata table = metadataRepository.get(type.getName());
		if (table == null) {
			throw new BuilderException("没有找到" + type.getName() + "的配置");
		}
		return table;
	}

	public Executor newExecutor(Transaction transaction) {
		return null;
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

}
