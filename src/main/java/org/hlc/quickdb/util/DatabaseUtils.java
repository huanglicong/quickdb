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
package org.hlc.quickdb.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hlc.quickdb.exception.PersistenceException;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月29日 下午3:06:37
 */
public class DatabaseUtils {

	private DatabaseUtils() {
	}

	public static void colseStatement(Statement statement) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}

	public static void colseResultSet(ResultSet resultSet) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
		}
	}

}
