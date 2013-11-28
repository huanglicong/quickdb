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

		register(Boolean.class, new BooleanTypeHandler());
		register(boolean.class, new BooleanTypeHandler());
		register(JdbcType.BOOLEAN, new BooleanTypeHandler());
		register(JdbcType.BIT, new BooleanTypeHandler());

		register(Byte.class, new ByteTypeHandler());
		register(byte.class, new ByteTypeHandler());
		register(JdbcType.TINYINT, new ByteTypeHandler());

		register(Short.class, new ShortTypeHandler());
		register(short.class, new ShortTypeHandler());
		register(JdbcType.SMALLINT, new ShortTypeHandler());

		register(Integer.class, new IntegerTypeHandler());
		register(int.class, new IntegerTypeHandler());
		register(JdbcType.INTEGER, new IntegerTypeHandler());

		register(Long.class, new LongTypeHandler());
		register(long.class, new LongTypeHandler());

		register(Float.class, new FloatTypeHandler());
		register(float.class, new FloatTypeHandler());
		register(JdbcType.FLOAT, new FloatTypeHandler());

		register(Double.class, new DoubleTypeHandler());
		register(double.class, new DoubleTypeHandler());
		register(JdbcType.DOUBLE, new DoubleTypeHandler());

		register(String.class, new StringTypeHandler());
		register(String.class, JdbcType.CHAR, new StringTypeHandler());
		register(String.class, JdbcType.CLOB, new ClobTypeHandler());
		register(String.class, JdbcType.VARCHAR, new StringTypeHandler());
		register(String.class, JdbcType.LONGVARCHAR, new ClobTypeHandler());
		register(String.class, JdbcType.NVARCHAR, new NStringTypeHandler());
		register(String.class, JdbcType.NCHAR, new NStringTypeHandler());
		register(String.class, JdbcType.NCLOB, new NClobTypeHandler());
		register(JdbcType.CHAR, new StringTypeHandler());
		register(JdbcType.VARCHAR, new StringTypeHandler());
		register(JdbcType.CLOB, new ClobTypeHandler());
		register(JdbcType.LONGVARCHAR, new ClobTypeHandler());
		register(JdbcType.NVARCHAR, new NStringTypeHandler());
		register(JdbcType.NCHAR, new NStringTypeHandler());
		register(JdbcType.NCLOB, new NClobTypeHandler());

		register(Object.class, JdbcType.ARRAY, new ArrayTypeHandler());
		register(JdbcType.ARRAY, new ArrayTypeHandler());

		register(BigInteger.class, new BigIntegerTypeHandler());
		register(JdbcType.BIGINT, new LongTypeHandler());

		register(BigDecimal.class, new BigDecimalTypeHandler());
		register(JdbcType.REAL, new BigDecimalTypeHandler());
		register(JdbcType.DECIMAL, new BigDecimalTypeHandler());
		register(JdbcType.NUMERIC, new BigDecimalTypeHandler());

		register(Byte[].class, new ByteObjectArrayTypeHandler());
		register(Byte[].class, JdbcType.BLOB, new BlobByteObjectArrayTypeHandler());
		register(Byte[].class, JdbcType.LONGVARBINARY, new BlobByteObjectArrayTypeHandler());
		register(byte[].class, new ByteArrayTypeHandler());
		register(byte[].class, JdbcType.BLOB, new BlobTypeHandler());
		register(byte[].class, JdbcType.LONGVARBINARY, new BlobTypeHandler());
		register(JdbcType.LONGVARBINARY, new BlobTypeHandler());
		register(JdbcType.BLOB, new BlobTypeHandler());

		// register(Object.class, UNKNOWN_TYPE_HANDLER);
		// register(Object.class, JdbcType.OTHER, UNKNOWN_TYPE_HANDLER);
		// register(JdbcType.OTHER, UNKNOWN_TYPE_HANDLER);

		register(Date.class, new DateTypeHandler());
		register(Date.class, JdbcType.DATE, new DateOnlyTypeHandler());
		register(Date.class, JdbcType.TIME, new TimeOnlyTypeHandler());
		register(JdbcType.TIMESTAMP, new DateTypeHandler());
		register(JdbcType.DATE, new DateOnlyTypeHandler());
		register(JdbcType.TIME, new TimeOnlyTypeHandler());

		register(java.sql.Date.class, new SqlDateTypeHandler());
		register(java.sql.Time.class, new SqlTimeTypeHandler());
		register(java.sql.Timestamp.class, new SqlTimestampTypeHandler());

		// issue #273
		register(Character.class, new CharacterTypeHandler());
		register(char.class, new CharacterTypeHandler());
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
