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
package org.hlc.quickdb.executor.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.hlc.quickdb.exception.PersistenceException;
import org.hlc.quickdb.executor.sequence.SequenceGenerater;
import org.hlc.quickdb.type.TypeHandler;

/**
 * 封装Statement执行SQL所需的参数.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月23日 下午11:11:55
 */
public class StatementParameter<T> {

	private int index;
	private String name;
	private TypeHandler<T> typeHandler;
	private SequenceGenerater sequenceGenerater;
	private T value;
	private int jdbcType;

	public StatementParameter(String name, int index, int jdbcType, TypeHandler<T> typeHandler, SequenceGenerater sequenceGenerater, T value) {

		this.index = index;
		this.name = name;
		this.typeHandler = typeHandler;
		this.sequenceGenerater = sequenceGenerater;
		this.value = value;
		this.jdbcType = jdbcType;
	}

	public void parameterize(Statement statement) throws SQLException {

		if (statement instanceof PreparedStatement) {
			typeHandler.setParameter((PreparedStatement) statement, index, value, jdbcType);
		} else {
			throw new PersistenceException(statement.getClass() + "不是PreparedStatement类型");
		}
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public TypeHandler<?> getTypeHandler() {

		return typeHandler;
	}

	public void setTypeHandler(TypeHandler<T> typeHandler) {

		this.typeHandler = typeHandler;
	}

	public T getValue() {

		return value;
	}

	public void setValue(T value) {

		this.value = value;
	}

	public SequenceGenerater getSequenceGenerater() {

		return sequenceGenerater;
	}

	public void setSequenceGenerater(SequenceGenerater sequenceGenerater) {

		this.sequenceGenerater = sequenceGenerater;
	}

	public int getIndex() {

		return index;
	}

	public void setIndex(int index) {

		this.index = index;
	}

	public int getJdbcType() {

		return jdbcType;
	}

	public void setJdbcType(int jdbcType) {

		this.jdbcType = jdbcType;
	}

}
