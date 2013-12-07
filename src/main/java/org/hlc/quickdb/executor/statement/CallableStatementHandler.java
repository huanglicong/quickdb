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
package org.hlc.quickdb.executor.statement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hlc.quickdb.builder.SqlSource;
import org.hlc.quickdb.exception.PersistenceException;
import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.executor.result.ResultHandler;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年12月5日 下午11:22:30
 */
public class CallableStatementHandler implements StatementHandler {

	protected final Log logger = LogFactory.getLog(getClass());
	private SqlSource sql;

	public CallableStatementHandler(SqlSource sql) {
		this.sql = sql;
	}

	@Override
	public Statement prepare(Connection connection) throws SQLException {

		return connection.prepareCall(sql.getSql());
	}

	public void batchParameterize(CallableStatement statement) throws SQLException {

	}

	@Override
	public int[] batch(Statement statement) throws SQLException {

		return null;
	}

	public void parameterize(CallableStatement statement) throws SQLException {
		Iterator<StatementParameter[]> iterator = sql.iterator();
		StatementParameter[] temps = null;
		if (iterator.hasNext()) {
			temps = iterator.next();
			for (StatementParameter item : temps) {
				item.parameterize(statement);
			}
		}
	}

	@Override
	public int update(Statement statement) throws SQLException {

		if (statement instanceof CallableStatement) {
			parameterize((CallableStatement) statement);
			return ((CallableStatement) statement).execute() ? 1 : -1;
		}
		throw new PersistenceException(statement.getClass() + "不是CallableStatement类型");
	}

	@Override
	public <E> List<E> query(Statement statement, ResultHandler<E> resultHandler) throws SQLException {

		return null;
	}

}
