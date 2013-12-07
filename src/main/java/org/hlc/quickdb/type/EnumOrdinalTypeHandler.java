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
 * @since 1.0 2013年11月24日 上午10:54:31
 */
public class EnumOrdinalTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

	private Class<E> type;
	private final E[] enums;

	public EnumOrdinalTypeHandler(Class<E> type, int jdbcType) {
		super(jdbcType);
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
		this.enums = type.getEnumConstants();
		if (this.enums == null)
			throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
	}

	@Override
	public Class<?> getType() {

		return type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement statement, int index, E parameter, int jdbcType) throws SQLException {

		statement.setInt(index, parameter.ordinal());
	}

	@Override
	public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

		int i = resultSet.getInt(columnName);
		if (resultSet.wasNull()) {
			return null;
		} else {
			try {
				return enums[i];
			} catch (Exception ex) {
				throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}

	@Override
	public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

		int i = resultSet.getInt(columnIndex);
		if (resultSet.wasNull()) {
			return null;
		} else {
			try {
				return enums[i];
			} catch (Exception ex) {
				throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}

	@Override
	public E getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		int i = callableStatement.getInt(columnIndex);
		if (callableStatement.wasNull()) {
			return null;
		} else {
			try {
				return enums[i];
			} catch (Exception ex) {
				throw new IllegalArgumentException("Cannot convert " + i + " to " + type.getSimpleName() + " by ordinal value.", ex);
			}
		}
	}

}
