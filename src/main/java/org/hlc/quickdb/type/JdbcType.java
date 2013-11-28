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

import java.sql.Types;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 上午11:15:25
 */
public class JdbcType {

	/* 标准SQL类型 */

	public final static int BIT = Types.BIT;
	public final static int TINYINT = Types.TINYINT;
	public final static int SMALLINT = Types.SMALLINT;
	public final static int INTEGER = Types.INTEGER;
	public final static int BIGINT = Types.BIGINT;
	public final static int FLOAT = Types.FLOAT;
	public final static int REAL = Types.REAL;
	public final static int DOUBLE = Types.DOUBLE;
	public final static int NUMERIC = Types.NUMERIC;
	public final static int DECIMAL = Types.DECIMAL;
	public final static int CHAR = Types.CHAR;
	public final static int VARCHAR = Types.VARCHAR;
	public final static int LONGVARCHAR = Types.LONGVARCHAR;
	public final static int DATE = Types.DATE;
	public final static int TIME = Types.TIME;
	public final static int TIMESTAMP = Types.TIMESTAMP;
	public final static int BINARY = Types.BINARY;
	public final static int VARBINARY = Types.VARBINARY;
	public final static int LONGVARBINARY = Types.LONGVARBINARY;
	public final static int NULL = Types.NULL;
	public final static int OTHER = Types.OTHER;
	public final static int JAVA_OBJECT = Types.JAVA_OBJECT;
	public final static int DISTINCT = Types.DISTINCT;
	public final static int STRUCT = Types.STRUCT;
	public final static int ARRAY = Types.ARRAY;
	public final static int BLOB = Types.BLOB;
	public final static int CLOB = Types.CLOB;
	public final static int REF = Types.REF;
	public final static int DATALINK = Types.DATALINK;
	public final static int BOOLEAN = Types.BOOLEAN;
	public final static int ROWID = Types.ROWID;
	public static final int NCHAR = Types.NCHAR;
	public static final int NVARCHAR = Types.NVARCHAR;
	public static final int LONGNVARCHAR = Types.LONGNVARCHAR;
	public static final int NCLOB = Types.NCLOB;
	public static final int SQLXML = Types.SQLXML;

	/* oracle */

	// public static final int LONG;
	// public static final int NUMBER=Types.DOUBLE;
	// public static final int VARCHAR2 = Types.VARCHAR;

	/* mysql */

	// public static final int DATETIME;
	// public static final int LONGBLOB;
	// public static final int LONGTEXT;

}
