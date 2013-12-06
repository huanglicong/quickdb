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

// TODO: Auto-generated Javadoc
/**
 * 
 * 定义执行数据库操作方法.
 * 
 * @author huanglicong
 * @version V1.0
 */
public interface Executor {

	/**
	 * 执行普通更新语句.
	 * 
	 * @param sql the sql
	 * @param params the params
	 * @return the int
	 */
	int doUpdate(String sql, Object params);

	/**
	 * 执行普通查询语句.
	 * 
	 * @param <T> the generic type
	 * @param sql the sql
	 * @param params the params
	 * @param type the type
	 * @return the list
	 */
	<T> List<T> doQuery(String sql, Object params, Class<T> type);

	/**
	 * 执行无返回值存储过程.
	 * 
	 * @param call the call
	 * @param params the params
	 * @return the int
	 */
	int doUpdateCallable(String call, Object params);

	/**
	 * 执行存储过程查询.
	 * 
	 * @param <T> the generic type
	 * @param call the call
	 * @param params the params
	 * @param resultType the result type
	 * @return the list
	 */
	<T> List<T> doQueryCallable(String call, Object params, Class<T> resultType);

	/**
	 * 提交事务.
	 * 
	 * @throws SQLException the sQL exception
	 */
	void commit() throws SQLException;

	/**
	 * 回滚事务.
	 * 
	 * @throws SQLException the sQL exception
	 */
	void rollback() throws SQLException;

	/**
	 * 获取事务接口.
	 * 
	 * @return the transaction
	 */
	Transaction getTransaction();

	/**
	 * 同时关闭数据库连接.
	 * 
	 * @throws SQLException the sQL exception
	 */
	void close() throws SQLException;

	/**
	 * 判断数据库连接是否关闭.
	 * 
	 * @return true, if is closed
	 */
	boolean isClosed();

}
