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
package org.hlc.quickdb.executor;

import java.sql.SQLException;
import java.util.List;

import org.hlc.quickdb.transaction.Transaction;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午10:20:55
 */
public interface Executor {

	int doUpdate(String sql, Object params);

	<T> List<T> doQuery(String sql, Object params, Class<T> type);

	void commit() throws SQLException;

	void rollback() throws SQLException;

	Transaction getTransaction();

	void close() throws SQLException;

	boolean isClosed();

}
