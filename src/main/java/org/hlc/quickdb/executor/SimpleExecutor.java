
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
 * @since 1.0 2013年11月22日 下午12:47:33
 */
public class SimpleExecutor  implements Executor{

	@Override
	public int doUpdate(String sql, Object params) {
		
		System.out.println(sql);
		return 0;
	}

	@Override
	public <T> List<T> doQuery(String sql, Object params, Class<T> type) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void commit() throws SQLException {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws SQLException {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public Transaction getTransaction() {
		
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public void close() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClosed() {
		
		// TODO Auto-generated method stub
		return false;
		
	}

}

