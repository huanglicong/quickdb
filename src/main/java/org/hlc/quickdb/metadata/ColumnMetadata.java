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

	/** 字段类型. */
	protected String type;

	/** 是否是主键. */
	protected boolean primaryKey;

	/** 序列名称. */
	private String sequence;

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
	 * @param type the type
	 * @param primaryKey the primary key
	 * @param field the field
	 */
	public ColumnMetadata(String name, String type, boolean primaryKey, Field field) {
		super();
		this.name = name;
		this.type = type;
		this.primaryKey = primaryKey;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {

		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type the new type
	 */
	public void setType(String type) {

		this.type = type;
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
	 * Gets the sequence.
	 *
	 * @return the sequence
	 */
	public String getSequence() {

		return sequence;
	}

	/**
	 * Sets the sequence.
	 *
	 * @param sequence the new sequence
	 */
	public void setSequence(String sequence) {

		this.sequence = sequence;
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
		builder.append(", type=");
		builder.append(type);
		builder.append(", primaryKey=");
		builder.append(primaryKey);
		builder.append(", field=");
		builder.append(field);
		builder.append("]");
		return builder.toString();
	}

}
