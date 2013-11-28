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
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.session.SessionException;
import org.hlc.quickdb.statement.StatementHandler;
import org.hlc.quickdb.transaction.Transaction;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月22日 下午12:47:33
 */
public class SimpleExecutor implements Executor {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());
	private final Transaction transaction;
	private final Configuration configuration;
	private boolean closed;

	public SimpleExecutor(Transaction transaction, Configuration configuration) {

		this.transaction = transaction;
		this.configuration = configuration;
	}

	@Override
	public int doUpdate(String sql, Object params) {

		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		SqlSource sqlSource = new SqlSource(sql, params, configuration);
		StatementHandler statementHandler = configuration.newStatementHandler(sqlSource);
		try {
			Statement statement = statementHandler.prepare(transaction.getConnection());
			if (params != null) {
				statementHandler.parameterize(statement);
			}
			statementHandler.update(statement);
		} catch (SQLException e) {
			throw new SessionException("执行更新错误", e);
		}
		return 0;
	}

	@Override
	public <T> List<T> doQuery(String sql, Object params, Class<T> type) {

		if (logger.isDebugEnabled()) {
			logger.debug(sql);
		}
		return null;
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
