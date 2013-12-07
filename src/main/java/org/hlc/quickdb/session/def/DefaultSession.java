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
package org.hlc.quickdb.session.def;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hlc.quickdb.builder.SqlCommandType;
import org.hlc.quickdb.builder.SqlSource;
import org.hlc.quickdb.executor.Executor;
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.session.Session;
import org.hlc.quickdb.session.SessionException;

/**
 * 
 * 默认的Session实现类.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午5:29:36
 */
public class DefaultSession implements Session {

	/** The logger. */
	protected final Log logger = LogFactory.getLog(getClass());

	/** The configuration. */
	private final Configuration configuration;

	/** The executor. */
	private final Executor executor;

	/**
	 * Instantiates a new default session.
	 * 
	 * @param configuration the configuration
	 * @param executor the executor
	 */
	public DefaultSession(Configuration configuration, Executor executor) {

		this.configuration = configuration;
		this.executor = executor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int executeUpdate(String sql, Object params) {

		return executor.doUpdate(configuration.newSqlBuilder(sql, params).build());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insert(Object record, boolean selective) {

		SqlSource sql = configuration.newSqlBuilder(record.getClass(), record, selective, SqlCommandType.INSERT).build();
		return executor.doUpdate(sql);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Object record, boolean selective) {

		SqlSource sql = configuration.newSqlBuilder(record.getClass(), record, selective, SqlCommandType.UPDATE).build();
		return executor.doUpdate(sql);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> int batchUpdateList(List<T> records, boolean selective) {

		// TODO
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int removeById(Class<?> type, Object id) {
		SqlSource sql = configuration.newSqlBuilder(type, id, false, SqlCommandType.DELETE).build();
		return executor.doUpdate(sql);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T selectOne(String sql, Object params, Class<T> resultType) {
		List<T> list = selectList(sql, params, resultType);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T selectById(Class<T> type, Object id) {
		SqlSource sql = configuration.newSqlBuilder(type, id, false, SqlCommandType.SELECT).build();
		List<T> list = executor.doQuery(sql, type);
		if (list == null || list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int selectCount(String sql, Object params) {
		Integer count = selectOne(sql, params, Integer.class);
		return (count == null ? 0 : count.intValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> selectList(String sql, Object params, Class<T> resultType) {
		SqlSource sqlSource = configuration.newSqlBuilder(sql, params).build();
		return executor.doQuery(sqlSource, resultType);
	}

	@Override
	public int executeCallable(String call, Object params) {
		SqlSource sqlSource = configuration.newSqlBuilder(call, params).build();
		return executor.doUpdateCallable(sqlSource);
	}

	@Override
	public <T> List<T> queryCallable(String call, Object params, Class<T> resultType) {
		SqlSource sqlSource = configuration.newSqlBuilder(call, params).build();
		return executor.doQueryCallable(sqlSource, resultType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void commit() {

		try {
			executor.commit();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void rollback() {

		try {
			executor.rollback();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void close() {

		try {
			executor.close();
		} catch (SQLException e) {
			throw new SessionException("关闭执行器错误", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Connection getConnection() {

		try {
			return executor.getTransaction().getConnection();
		} catch (SQLException e) {
			throw new SessionException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Configuration getConfiguration() {
		return this.configuration;
	}

}
