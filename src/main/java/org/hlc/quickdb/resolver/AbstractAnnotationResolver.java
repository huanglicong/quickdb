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
package org.hlc.quickdb.resolver;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import org.hlc.quickdb.annotation.Column;
import org.hlc.quickdb.annotation.PrimaryKey;
import org.hlc.quickdb.annotation.Sequence;
import org.hlc.quickdb.annotation.Table;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.util.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * 
 * 提供公共的解析实体类的方法.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月12日 下午5:00:27
 */
public abstract class AbstractAnnotationResolver implements AnnotationResolver {

	private final Map<Class<?>, String> types = new LinkedHashMap<Class<?>, String>();

	/**
	 * Resolve table metadata.
	 * 
	 * @param classes the classes
	 * @return the table metadata
	 */
	protected TableMetadata resolveTableMetadata(Class<?> classes) {

		Table table = classes.getAnnotation(Table.class);
		if (table == null) {
			return null;
		}
		String tableName = table.value();
		if (StringUtils.isEmpty(tableName)) {
			tableName = classes.getSimpleName();
		}
		return new TableMetadata(tableName, classes);
	}

	protected ColumnMetadata resolveColumnMetadata(Field field) {

		ColumnMetadata metadata = null;
		// 1.普通字段解析
		Column column = field.getAnnotation(Column.class);
		if (column != null) {
			String columnName = column.value();
			if (StringUtils.isEmpty(columnName)) {
				columnName = field.getName();
			}
			String columnType = column.type();
			if (StringUtils.isEmpty(columnType)) {
				columnType = types.get(field.getType());
			}
			if (columnType == null) {
				throw new IllegalArgumentException("没有找到" + columnName + "字段对应" + field.getType() + "的DB类型");
			}
			metadata = new ColumnMetadata(columnName, columnType, false, field);
		}

		// 2.解析主键字段
		PrimaryKey key = field.getAnnotation(PrimaryKey.class);
		if (key != null) {
			String columnName = key.value();
			if (StringUtils.isEmpty(columnName)) {
				columnName = field.getName();
			}
			String columnType = key.type();
			if (StringUtils.isEmpty(columnType)) {
				columnType = types.get(field.getType());
			}
			if (columnType == null) {
				throw new IllegalArgumentException("没有找到" + columnName + "主键字段对应" + field.getType() + "的DB类型");
			}
			metadata = new ColumnMetadata(columnName, columnType, true, field);
		}

		// 3.解析序列
		Sequence sequence = field.getAnnotation(Sequence.class);
		if (sequence != null && metadata != null) {
			metadata.setSequence(sequence.value());
		}

		return metadata;
	}

	public void clear() {
		types.clear();
	}

	public String get(Object key) {
		return types.get(key);
	}

	public String put(Class<?> key, String value) {
		return types.put(key, value);
	}

	public String remove(Object key) {
		return types.remove(key);
	}

	public Map<Class<?>, String> getTypes() {

		return types;
	}

}
