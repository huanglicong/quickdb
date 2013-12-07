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

import org.hlc.quickdb.util.ArrayUtils;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 上午10:34:17
 */
public class ByteObjectArrayTypeHandler extends BaseTypeHandler<Byte[]> {

	public ByteObjectArrayTypeHandler(int jdbcType) {
		super(jdbcType);
	}

	@Override
	public Class<?> getType() {

		return Byte[].class;
	}

	private Byte[] getBytes(byte[] bytes) {
		Byte[] returnValue = null;
		if (bytes != null) {
			returnValue = ArrayUtils.convertToObjectArray(bytes);
		}
		return returnValue;
	}

	@Override
	public void setNonNullParameter(PreparedStatement statement, int index, Byte[] parameter, int jdbcType) throws SQLException {

		statement.setBytes(index, ArrayUtils.convertToPrimitiveArray(parameter));
	}

	@Override
	public Byte[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

		byte[] bytes = resultSet.getBytes(columnName);
		return getBytes(bytes);
	}

	@Override
	public Byte[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

		byte[] bytes = resultSet.getBytes(columnIndex);
		return getBytes(bytes);
	}

	@Override
	public Byte[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		byte[] bytes = callableStatement.getBytes(columnIndex);
		return getBytes(bytes);
	}

}
