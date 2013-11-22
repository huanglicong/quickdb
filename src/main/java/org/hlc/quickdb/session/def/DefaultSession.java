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
	public int executeUpdate(String sql) {

		return executeUpdate(sql, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int executeUpdate(String sql, Object record) {

		return executor.doUpdate(sql, record);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int insert(Object record, boolean selective) {

		String sql = configuration.newSqlBuilder(record.getClass(), record, selective, SqlCommandType.INSERT).build();
		return executeUpdate(sql, record);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int update(Object record, boolean selective) {

		String sql = configuration.newSqlBuilder(record.getClass(), record, selective, SqlCommandType.UPDATE).build();
		return executeUpdate(sql, record);
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

		String sql = configuration.newSqlBuilder(type, null, false, SqlCommandType.UPDATE).build();
		return executeUpdate(sql, id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T selectOne(String sql, Class<T> resultType) {

		return selectOne(sql, null, resultType);
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

		String sql = configuration.newSqlBuilder(type, null, false, SqlCommandType.UPDATE).build();
		return selectOne(sql, id, type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int selectCount(String sql) {

		return selectCount(sql, null);
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
	public <T> List<T> selectList(String sql, Class<T> resultType) {

		return selectList(sql, null, resultType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> List<T> selectList(String sql, Object params, Class<T> resultType) {

		return executor.doQuery(sql, params, resultType);
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

		executor.close();
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
