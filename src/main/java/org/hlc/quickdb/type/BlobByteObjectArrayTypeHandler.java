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

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hlc.quickdb.util.ArrayUtils;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 上午12:37:38
 */
public class BlobByteObjectArrayTypeHandler extends BaseTypeHandler<Byte[]> {

	public BlobByteObjectArrayTypeHandler(int jdbcType) {
		super(jdbcType);
	}

	@Override
	public Class<?> getType() {

		return Byte[].class;
	}

	@Override
	public void setNonNullParameter(PreparedStatement statement, int index, Byte[] parameter, int jdbcType) throws SQLException {

		ByteArrayInputStream inputStream = new ByteArrayInputStream(ArrayUtils.convertToPrimitiveArray(parameter));
		statement.setBinaryStream(index, inputStream);
	}

	private Byte[] getBytes(Blob blob) throws SQLException {
		Byte[] returnValue = null;
		if (blob != null) {
			returnValue = ArrayUtils.convertToObjectArray(blob.getBytes(1, (int) blob.length()));
		}
		return returnValue;
	}

	@Override
	public Byte[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {

		Blob blob = resultSet.getBlob(columnName);
		return getBytes(blob);
	}

	@Override
	public Byte[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {

		Blob blob = resultSet.getBlob(columnIndex);
		return getBytes(blob);
	}

	@Override
	public Byte[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {

		Blob blob = callableStatement.getBlob(columnIndex);
		return getBytes(blob);
	}

}
