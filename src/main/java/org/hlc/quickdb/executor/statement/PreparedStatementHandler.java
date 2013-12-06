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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hlc.quickdb.builder.SqlSource;
import org.hlc.quickdb.exception.PersistenceException;
import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.executor.result.ResultHandler;

/**
 * 
 * 采用PreparedStatement实现{@link StatementHandler}接口
 * 
 * @author huanglicong
 * @version V1.0
 */
public class PreparedStatementHandler implements StatementHandler {

	protected final Log logger = LogFactory.getLog(getClass());
	private SqlSource sqlWrapper;

	public PreparedStatementHandler(SqlSource sqlWrapper) {
		this.sqlWrapper = sqlWrapper;
	}

	@Override
	public Statement prepare(Connection connection) throws SQLException {
		return connection.prepareStatement(sqlWrapper.getSql());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void parameterize(Statement statement) throws SQLException {

		if (logger.isDebugEnabled()) {
			logger.debug(sqlWrapper);
			logger.debug(params);
		}
		for (StatementParameter item : params) {
			item.parameterize(statement);
		}
	}

	@Override
	public void batch(Statement statement) throws SQLException {

	}

	@Override
	public int update(Statement statement) throws SQLException {

		if (statement instanceof PreparedStatement) {
			return ((PreparedStatement) statement).executeUpdate();
		}
		throw new PersistenceException(statement.getClass() + "不是PreparedStatement类型");
	}

	@Override
	public <E> List<E> query(Statement statement, ResultHandler<E> resultHandler) throws SQLException {

		if (statement instanceof PreparedStatement) {
			ResultSet resultSet = ((PreparedStatement) statement).executeQuery();
			return resultHandler.handleResult(resultSet);
		}
		throw new PersistenceException(statement.getClass() + "不是PreparedStatement类型");
	}

}
