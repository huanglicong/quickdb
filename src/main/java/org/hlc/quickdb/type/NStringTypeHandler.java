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
package org.hlc.quickdb.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 上午11:36:53
 */
public class NStringTypeHandler extends BaseTypeHandler<String> {

	@Override
	public Class<?> getType() {

		return String.class;
	}

	@Override
	public void setNonNullParameter(PreparedStatement statement, int index, String parameter, int jdbcType) throws SQLException {

		statement.setString(index, parameter);
	}

	@Override
	public String getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

		return resultSet.getString(columnName);
	}

	@Override
	public String getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

		return resultSet.getString(columnIndex);
	}

	@Override
	public String getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		return callableStatement.getString(columnIndex);
	}

}
