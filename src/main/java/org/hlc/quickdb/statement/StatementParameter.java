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
package org.hlc.quickdb.statement;

import java.sql.Statement;

import org.hlc.quickdb.sequence.SequenceGenerater;
import org.hlc.quickdb.type.TypeHandler;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月23日 下午11:11:55
 */
public class StatementParameter {

	private int index;
	private String name;
	private TypeHandler<?> typeHandler;
	private SequenceGenerater sequenceGenerater;
	private Object value;
	private int jdbcType;

	public StatementParameter(String name, int index, int jdbcType, TypeHandler<?> typeHandler, SequenceGenerater sequenceGenerater, Object value) {

		this.index = index;
		this.name = name;
		this.typeHandler = typeHandler;
		this.sequenceGenerater = sequenceGenerater;
		this.value = value;
		this.jdbcType = jdbcType;
	}

	public void parameterize(Statement statement) {

		// typeHandler.setParameter((PreparedStatement) statement, index, value,
		// jdbcType);
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

	public void setTypeHandler(TypeHandler<?> typeHandler) {

		this.typeHandler = typeHandler;
	}

	public Object getValue() {

		return value;
	}

	public void setValue(Object value) {

		this.value = value;
	}

	public SequenceGenerater getSequenceGenerater() {

		return sequenceGenerater;
	}

	public void setSequenceGenerater(SequenceGenerater sequenceGenerater) {

		this.sequenceGenerater = sequenceGenerater;
	}

}
