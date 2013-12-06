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
package org.hlc.quickdb.session;

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;

/**
 * 数据库会话接口，定义常用的数据库操作方法，所有持久化操作都在事务环境下进行.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午4:24:32
 */
public interface Session extends Closeable {

	// ////////////////////////////////对象操作////////////////////////////////

	/**
	 * 插入对象.
	 * 
	 * @param record the record
	 * @param selective the selective
	 * @return the int
	 */
	int insert(Object record, boolean selective);

	/**
	 * 更新对象.
	 * 
	 * @param record the record
	 * @param selective the selective
	 * @return the int
	 */
	int update(Object record, boolean selective);

	/**
	 * 批量更新列表，允许新增的对象.
	 * 
	 * @param <T> the generic type
	 * @param records the records
	 * @param selective the selective
	 * @return the int
	 */
	<T> int batchUpdateList(List<T> records, boolean selective);

	/**
	 * 删除对象.
	 * 
	 * @param type the type
	 * @param id the id
	 * @return the int
	 */
	int removeById(Class<?> type, Object id);

	/**
	 * 查询对象.
	 * 
	 * @param <T> the generic type
	 * @param type the type
	 * @param id the id
	 * @return the t
	 */
	<T> T selectById(Class<T> type, Object id);

	// ////////////////////////////////SQL操作////////////////////////////////

	/**
	 * 更新SQL，支持ognl表达式，参数来源于params对象.
	 * 
	 * @param sql the sql
	 * @param params the params
	 * @return the int
	 */
	int executeUpdate(String sql, Object params);

	/**
	 * 执行查询SQL，返回至多一条记录，支持ognl表达式，参数来源于params对象.
	 * 
	 * @param <T> the generic type
	 * @param sql the sql
	 * @param params the params
	 * @param resultType the result type
	 * @return the t
	 */
	<T> T selectOne(String sql, Object params, Class<T> resultType);

	/**
	 * 执行统计SQL，支持ognl表达式，参数来源于params对象.
	 * 
	 * @param sql the sql
	 * @param params the params
	 * @return the int
	 */
	int selectCount(String sql, Object params);

	/**
	 * 执行查询SQL，返回多条记录，支持ognl表达式，参数来源于params对象.
	 * 
	 * @param <T> the generic type
	 * @param sql the sql
	 * @param params the params
	 * @param resultType the result type
	 * @return the list
	 */
	<T> List<T> selectList(String sql, Object params, Class<T> resultType);

	// ////////////////////////////////存储过程操作////////////////////////////////

	int executeCallable(String call, Object params);

	<T> List<T> queryCallable(String call, Object params, Class<T> resultType);

	// ////////////////////////////////事务操作////////////////////////////////

	/**
	 * 提交事务.
	 */
	void commit();

	/**
	 * 回滚事务.
	 */
	void rollback();

	/**
	 * 关闭Session.
	 */
	void close();

	/**
	 * 获取数据库连接.
	 * 
	 * @return the connection
	 */
	Connection getConnection();

	/**
	 * 获取上下文配置对象.
	 * 
	 * @return the configuration
	 */
	Configuration getConfiguration();
}
