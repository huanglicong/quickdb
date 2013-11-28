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
package org.hlc.quickdb.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hlc.quickdb.builder.SqlSource;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月22日 下午5:26:50
 */
public class PreparedStatementHandler implements StatementHandler {

	protected final Log logger = LogFactory.getLog(getClass());
	private final String sql;
	private final List<StatementParameter> params;

	public PreparedStatementHandler(SqlSource sqlSource) {

		this.sql = sqlSource.getSql();
		this.params = sqlSource.getParams();
	}

	@Override
	public Statement prepare(Connection connection) throws SQLException {

		return connection.prepareCall(sql);
	}

	@Override
	public void parameterize(Statement statement) throws SQLException {

		if (logger.isDebugEnabled()) {
			logger.debug(sql);
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

		return 0;
	}

	@Override
	public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {

		return null;
	}

}
