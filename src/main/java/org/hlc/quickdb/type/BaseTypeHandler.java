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
 * @since 1.0 2013年11月24日 上午12:26:37
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

	private int jdbcType;

	public BaseTypeHandler(int jdbcType) {
		this.jdbcType = jdbcType;
	}

	@Override
	public void setParameter(PreparedStatement statement, int index, T parameter, int jdbcType) throws SQLException {

		if (parameter == null) {
			statement.setNull(index, jdbcType);
		} else {
			setNonNullParameter(statement, index, parameter, jdbcType);
		}
	}

	@Override
	public T getResult(ResultSet resultSet, String columnName) throws SQLException {

		T result = getNullableResult(resultSet, columnName);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return result;
		}
	}

	@Override
	public T getResult(ResultSet resultSet, int columnIndex) throws SQLException {

		T result = getNullableResult(resultSet, columnIndex);
		if (resultSet.wasNull()) {
			return null;
		} else {
			return result;
		}
	}

	@Override
	public T getResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		T result = getNullableResult(callableStatement, columnIndex);
		if (callableStatement.wasNull()) {
			return null;
		} else {
			return result;
		}
	}

	public abstract void setNonNullParameter(PreparedStatement statement, int index, T parameter, int jdbcType) throws SQLException;

	public abstract T getNullableResult(ResultSet resultSet, String columnName) throws SQLException;

	public abstract T getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException;

	public abstract T getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException;

	@Override
	public int getJdbcType() {
		return jdbcType;
	}

}
