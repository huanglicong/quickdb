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
package org.hlc.quickdb.metadata;

import java.lang.reflect.Field;

import org.hlc.quickdb.sequence.SequenceGenerater;
import org.hlc.quickdb.type.TypeHandler;

// TODO: Auto-generated Javadoc
/**
 * 字段元数据.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月12日 下午3:27:28
 */
public class ColumnMetadata {

	/** 字段名称. */
	protected String name;

	/** 是否是主键. */
	protected boolean primaryKey;

	/** 序列生成器. */
	private SequenceGenerater sequenceGenerater;

	/** 类型处理器. */
	private TypeHandler<?> typeHandler;

	/** The field. */
	private Field field;

	/**
	 * 
	 * 创建一个新的ColumnMetadata实例.
	 * 
	 */
	public ColumnMetadata() {
	}

	/**
	 * Instantiates a new column metadata.
	 * 
	 * @param name the name
	 * @param primaryKey the primary key
	 * @param sequenceGenerater the sequence generater
	 * @param typeHandler the type handler
	 * @param field the field
	 */
	public ColumnMetadata(String name, boolean primaryKey, SequenceGenerater sequenceGenerater, TypeHandler<?> typeHandler, Field field) {

		this.name = name;
		this.primaryKey = primaryKey;
		this.sequenceGenerater = sequenceGenerater;
		this.typeHandler = typeHandler;
		this.field = field;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Checks if is primary key.
	 * 
	 * @return true, if is primary key
	 */
	public boolean isPrimaryKey() {

		return primaryKey;
	}

	/**
	 * Sets the primary key.
	 * 
	 * @param primaryKey the new primary key
	 */
	public void setPrimaryKey(boolean primaryKey) {

		this.primaryKey = primaryKey;
	}

	/**
	 * Gets the field.
	 * 
	 * @return the field
	 */
	public Field getField() {

		return field;
	}

	/**
	 * Sets the field.
	 * 
	 * @param field the new field
	 */
	public void setField(Field field) {

		this.field = field;
	}

	/**
	 * Gets the sequence generater.
	 * 
	 * @return the sequence generater
	 */
	public SequenceGenerater getSequenceGenerater() {

		return sequenceGenerater;
	}

	/**
	 * Sets the sequence generater.
	 * 
	 * @param sequenceGenerater the new sequence generater
	 */
	public void setSequenceGenerater(SequenceGenerater sequenceGenerater) {

		this.sequenceGenerater = sequenceGenerater;
	}

	/**
	 * Gets the type handler.
	 * 
	 * @return the type handler
	 */
	public TypeHandler<?> getTypeHandler() {

		return typeHandler;
	}

	/**
	 * Sets the type handler.
	 * 
	 * @param typeHandler the new type handler
	 */
	public void setTypeHandler(TypeHandler<?> typeHandler) {

		this.typeHandler = typeHandler;
	}

	/**
	 * To string.
	 * 
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColumnMetadata [name=");
		builder.append(name);
		builder.append(", primaryKey=");
		builder.append(primaryKey);
		builder.append(", sequenceGenerater=");
		builder.append(sequenceGenerater);
		builder.append(", typeHandler=");
		builder.append(typeHandler);
		builder.append(", field=");
		builder.append(field);
		builder.append("]");
		return builder.toString();
	}

}
