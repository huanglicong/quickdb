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
package org.hlc.quickdb.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hlc.quickdb.builder.SqlSource;
import org.hlc.quickdb.executor.statement.StatementHandler;
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.session.SessionException;
import org.hlc.quickdb.transaction.Transaction;
import org.hlc.quickdb.util.DatabaseUtils;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月22日 下午12:47:33
 */
public class JdbcExecutor implements Executor {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());
	private final Transaction transaction;
	private final Configuration configuration;
	private boolean closed;

	public JdbcExecutor(Transaction transaction, Configuration configuration) {

		this.transaction = transaction;
		this.configuration = configuration;
	}

	@Override
	public int doUpdate(SqlSource sql) {

		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		StatementHandler statementHandler = configuration.newStatementHandler(sql);
		try {
			Statement statement = statementHandler.prepare(transaction.getConnection());
			return statementHandler.update(statement);
		} catch (SQLException e) {
			throw new SessionException("执行更新错误", e);
		}
	}

	@Override
	public int[] doBatchUpdate(SqlSource sql) {
		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		StatementHandler statementHandler = configuration.newStatementHandler(sql);
		try {
			Statement statement = statementHandler.prepare(transaction.getConnection());
			return statementHandler.batch(statement);
		} catch (SQLException e) {
			throw new SessionException("执行更新错误", e);
		}
	}

	@Override
	public <T> List<T> doQuery(SqlSource sql, Class<T> type) {

		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		StatementHandler statementHandler = configuration.newStatementHandler(sql);
		Statement statement = null;
		try {
			statement = statementHandler.prepare(transaction.getConnection());
			return statementHandler.query(statement, configuration.newResultHandler(type));
		} catch (SQLException e) {
			throw new SessionException("执行查询错误", e);
		} finally {
			DatabaseUtils.colseStatement(statement);
		}
	}

	@Override
	public int doUpdateCallable(SqlSource call) {

		if (logger.isDebugEnabled()) {
			logger.debug(call);
		}
		StatementHandler statementHandler = configuration.newCallableStatement(call);
		try {
			Statement statement = statementHandler.prepare(transaction.getConnection());
			return statementHandler.update(statement);
		} catch (SQLException e) {
			throw new SessionException("执行更新错误", e);
		}
	}

	@Override
	public <T> List<T> doQueryCallable(SqlSource call, Class<T> resultType) {

		if (logger.isDebugEnabled()) {
			logger.debug(call);
		}
		StatementHandler statementHandler = configuration.newCallableStatement(call);
		Statement statement = null;
		try {
			statement = statementHandler.prepare(transaction.getConnection());
			return statementHandler.query(statement, configuration.newResultHandler(resultType));
		} catch (SQLException e) {
			throw new SessionException("执行查询错误", e);
		} finally {
			DatabaseUtils.colseStatement(statement);
		}
	}

	@Override
	public void commit() throws SQLException {

		transaction.commit();
	}

	@Override
	public void rollback() throws SQLException {

		transaction.rollback();
	}

	@Override
	public Transaction getTransaction() {

		return transaction;
	}

	@Override
	public void close() throws SQLException {

		closed = true;
		transaction.close();
	}

	@Override
	public boolean isClosed() {

		return closed;
	}

}
