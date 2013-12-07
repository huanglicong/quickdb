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
import org.hlc.quickdb.type.TypeHandler;

/**
 * 封装Statement执行SQL所需的参数.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月23日 下午11:11:55
 */
public class StatementParameter {

	private int index;
	@SuppressWarnings("rawtypes")
	private TypeHandler typeHandler;
	private Object value;

	@SuppressWarnings("rawtypes")
	public StatementParameter(int index, TypeHandler typeHandler, Object value) {
		this.index = index;
		this.typeHandler = typeHandler;
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	public void parameterize(Statement statement) throws SQLException {
		if (statement instanceof PreparedStatement) {
			typeHandler.setParameter((PreparedStatement) statement, index, value, typeHandler.getJdbcType());
		} else {
			throw new PersistenceException(statement.getClass() + "不是PreparedStatement类型");
		}
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@SuppressWarnings("rawtypes")
	public TypeHandler getTypeHandler() {
		return typeHandler;
	}

	@SuppressWarnings("rawtypes")
	public void setTypeHandler(TypeHandler typeHandler) {
		this.typeHandler = typeHandler;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
