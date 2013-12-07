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
 * @since 1.0 2013年11月24日 上午10:14:03
 */
public class BooleanTypeHandler extends BaseTypeHandler<Boolean> {

	public BooleanTypeHandler(int jdbcType) {
		super(jdbcType);
	}

	@Override
	public Class<?> getType() {

		return Boolean.class;
	}

	@Override
	public void setNonNullParameter(PreparedStatement statement, int index, Boolean parameter, int jdbcType) throws SQLException {

		statement.setBoolean(index, parameter);
	}

	@Override
	public Boolean getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

		return resultSet.getBoolean(columnName);
	}

	@Override
	public Boolean getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

		return resultSet.getBoolean(columnIndex);
	}

	@Override
	public Boolean getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		return callableStatement.getBoolean(columnIndex);
	}

}
