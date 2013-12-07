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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 下午12:37:51
 */
public class TypeHandlerRegistry {

	private final Map<Integer, TypeHandler<?>> JDBC_TYPE_HANDLER_MAP = new LinkedHashMap<Integer, TypeHandler<?>>();
	private final Map<Class<?>, TypeHandler<?>> TYPE_HANDLER_MAP = new HashMap<Class<?>, TypeHandler<?>>();
	private final Map<TypeMapping, TypeHandler<?>> TYPEMAPPING_HANDLER_MAP = new HashMap<TypeMapping, TypeHandler<?>>();

	public TypeHandlerRegistry() {

		register(Boolean.class, new BooleanTypeHandler(JdbcType.BOOLEAN));
		register(boolean.class, new BooleanTypeHandler(JdbcType.BOOLEAN));
		register(JdbcType.BOOLEAN, new BooleanTypeHandler(JdbcType.BOOLEAN));
		register(JdbcType.BIT, new BooleanTypeHandler(JdbcType.BIT));

		register(Byte.class, new ByteTypeHandler(JdbcType.TINYINT));
		register(byte.class, new ByteTypeHandler(JdbcType.TINYINT));
		register(JdbcType.TINYINT, new ByteTypeHandler(JdbcType.TINYINT));

		register(Short.class, new ShortTypeHandler(JdbcType.SMALLINT));
		register(short.class, new ShortTypeHandler(JdbcType.SMALLINT));
		register(JdbcType.SMALLINT, new ShortTypeHandler(JdbcType.SMALLINT));

		register(Integer.class, new IntegerTypeHandler(JdbcType.INTEGER));
		register(int.class, new IntegerTypeHandler(JdbcType.INTEGER));
		register(JdbcType.INTEGER, new IntegerTypeHandler(JdbcType.INTEGER));

		register(Long.class, new LongTypeHandler(JdbcType.DECIMAL));
		register(long.class, new LongTypeHandler(JdbcType.DECIMAL));

		register(Float.class, new FloatTypeHandler(JdbcType.FLOAT));
		register(float.class, new FloatTypeHandler(JdbcType.FLOAT));
		register(JdbcType.FLOAT, new FloatTypeHandler(JdbcType.FLOAT));

		register(Double.class, new DoubleTypeHandler(JdbcType.DOUBLE));
		register(double.class, new DoubleTypeHandler(JdbcType.DOUBLE));
		register(JdbcType.DOUBLE, new DoubleTypeHandler(JdbcType.DOUBLE));

		register(String.class, new StringTypeHandler(JdbcType.VARCHAR));
		register(String.class, JdbcType.CHAR, new StringTypeHandler(JdbcType.CHAR));
		register(String.class, JdbcType.CLOB, new ClobTypeHandler(JdbcType.CLOB));
		register(String.class, JdbcType.VARCHAR, new StringTypeHandler(JdbcType.VARCHAR));
		register(String.class, JdbcType.LONGVARCHAR, new ClobTypeHandler(JdbcType.LONGVARCHAR));
		register(String.class, JdbcType.NVARCHAR, new NStringTypeHandler(JdbcType.NVARCHAR));
		register(String.class, JdbcType.NCHAR, new NStringTypeHandler(JdbcType.NCHAR));
		register(String.class, JdbcType.NCLOB, new NClobTypeHandler(JdbcType.NCLOB));
		register(JdbcType.CHAR, new StringTypeHandler(JdbcType.CHAR));
		register(JdbcType.VARCHAR, new StringTypeHandler(JdbcType.VARCHAR));
		register(JdbcType.CLOB, new ClobTypeHandler(JdbcType.CLOB));
		register(JdbcType.LONGVARCHAR, new ClobTypeHandler(JdbcType.LONGVARCHAR));
		register(JdbcType.NVARCHAR, new NStringTypeHandler(JdbcType.NVARCHAR));
		register(JdbcType.NCHAR, new NStringTypeHandler(JdbcType.NCHAR));
		register(JdbcType.NCLOB, new NClobTypeHandler(JdbcType.NCLOB));

		register(Object.class, JdbcType.ARRAY, new ArrayTypeHandler(JdbcType.ARRAY));
		register(JdbcType.ARRAY, new ArrayTypeHandler(JdbcType.ARRAY));

		register(BigInteger.class, new BigIntegerTypeHandler(JdbcType.BIGINT));
		register(JdbcType.BIGINT, new LongTypeHandler(JdbcType.BIGINT));

		register(BigDecimal.class, new BigDecimalTypeHandler(JdbcType.DECIMAL));
		register(JdbcType.REAL, new BigDecimalTypeHandler(JdbcType.REAL));
		register(JdbcType.DECIMAL, new BigDecimalTypeHandler(JdbcType.DECIMAL));
		register(JdbcType.NUMERIC, new BigDecimalTypeHandler(JdbcType.NUMERIC));

		register(Byte[].class, new ByteObjectArrayTypeHandler(JdbcType.BLOB));
		register(Byte[].class, JdbcType.BLOB, new BlobByteObjectArrayTypeHandler(JdbcType.BLOB));
		register(Byte[].class, JdbcType.LONGVARBINARY, new BlobByteObjectArrayTypeHandler(JdbcType.LONGVARBINARY));
		register(byte[].class, new ByteArrayTypeHandler(JdbcType.BLOB));
		register(byte[].class, JdbcType.BLOB, new BlobTypeHandler(JdbcType.BLOB));
		register(byte[].class, JdbcType.LONGVARBINARY, new BlobTypeHandler(JdbcType.LONGVARBINARY));
		register(JdbcType.LONGVARBINARY, new BlobTypeHandler(JdbcType.LONGVARBINARY));
		register(JdbcType.BLOB, new BlobTypeHandler(JdbcType.BLOB));

		// register(Object.class, UNKNOWN_TYPE_HANDLER);
		// register(Object.class, JdbcType.OTHER, UNKNOWN_TYPE_HANDLER);
		// register(JdbcType.OTHER, UNKNOWN_TYPE_HANDLER);

		register(Date.class, new DateTypeHandler(JdbcType.TIMESTAMP));
		register(Date.class, JdbcType.DATE, new DateOnlyTypeHandler(JdbcType.DATE));
		register(Date.class, JdbcType.TIME, new TimeOnlyTypeHandler(JdbcType.TIME));
		register(JdbcType.TIMESTAMP, new DateTypeHandler(JdbcType.TIMESTAMP));
		register(JdbcType.DATE, new DateOnlyTypeHandler(JdbcType.DATE));
		register(JdbcType.TIME, new TimeOnlyTypeHandler(JdbcType.TIME));

		register(java.sql.Date.class, new SqlDateTypeHandler(JdbcType.TIMESTAMP));
		register(java.sql.Time.class, new SqlTimeTypeHandler(JdbcType.TIMESTAMP));
		register(java.sql.Timestamp.class, new SqlTimestampTypeHandler(JdbcType.TIMESTAMP));

		// issue #273
		register(Character.class, new CharacterTypeHandler(JdbcType.CHAR));
		register(char.class, new CharacterTypeHandler(JdbcType.CHAR));
	}

	public class TypeMapping {

		public Class<?> javaType;
		public int jdbcType;

		public TypeMapping(Class<?> javaType, int jdbcType) {

			this.javaType = javaType;
			this.jdbcType = jdbcType;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((javaType == null) ? 0 : javaType.hashCode());
			result = prime * result + jdbcType;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			TypeMapping other = (TypeMapping) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (javaType == null) {
				if (other.javaType != null)
					return false;
			} else if (!javaType.equals(other.javaType))
				return false;
			if (jdbcType != other.jdbcType)
				return false;
			return true;
		}

		private TypeHandlerRegistry getOuterType() {
			return TypeHandlerRegistry.this;
		}

	}

	public void register(Class<?> javaType, TypeHandler<?> typeHandler) {
		TYPE_HANDLER_MAP.put(javaType, typeHandler);
	}

	public void register(int jdbcType, TypeHandler<?> typeHandler) {
		JDBC_TYPE_HANDLER_MAP.put(jdbcType, typeHandler);
	}

	public void register(Class<?> javaType, int jdbcType, TypeHandler<?> typeHandler) {

		TYPEMAPPING_HANDLER_MAP.put(new TypeMapping(javaType, jdbcType), typeHandler);
	}

	public TypeHandler<?> getHandler(Class<?> javaType) {

		return TYPE_HANDLER_MAP.get(javaType);
	}

	public TypeHandler<?> getHandler(int jdbcType) {
		return JDBC_TYPE_HANDLER_MAP.get(jdbcType);
	}

	public TypeHandler<?> getHandler(Class<?> javaType, int jdbcType) {

		return TYPEMAPPING_HANDLER_MAP.get(new TypeMapping(javaType, jdbcType));
	}

}
