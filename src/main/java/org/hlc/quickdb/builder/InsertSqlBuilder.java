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
package org.hlc.quickdb.builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午11:21:07
 */
public class InsertSqlBuilder extends BaseSqlBuilder {

	private Object record;
	private TableMetadata table;
	private boolean selective;
	private boolean capital;

	public InsertSqlBuilder(Object record, TableMetadata table, boolean selective, boolean capital) {

		this.record = record;
		this.table = table;
		this.selective = selective;
		this.capital = capital;
	}

	@Override
	public String build() {

		StringBuilder before = new StringBuilder();
		StringBuilder after = new StringBuilder();
		Iterator<ColumnMetadata> iterator = table.iterator();
		ColumnMetadata temp = null;

		List<ColumnMetadata> list = new ArrayList<ColumnMetadata>();
		while (iterator.hasNext()) {
			temp = iterator.next();
			if (selective && getValue(record, temp.getField()) == null) {
				continue;
			}
			list.add(temp);
		}

		int size = list.size();
		int index = 0;
		for (ColumnMetadata item : list) {
			if (capital) {
				before.append(item.getName().toUpperCase(Locale.getDefault()));
			} else {
				before.append(item.getName());
			}
			after.append("#{" + item.getField().getName() + ",jdbcType=" + item.getType() + "}");
			if (index < size - 1) {
				before.append(", ");
				after.append(", ");
			}
			index++;
		}
		if (index == 0) {
			throw new IllegalArgumentException("至少插入一个字段");
		}

		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");
		if (capital) {
			sql.append(table.getTableName().toUpperCase(Locale.getDefault()));
		} else {
			sql.append(table.getTableName());
		}
		sql.append("(");
		sql.append(before);
		sql.append(")  values  (");
		sql.append(after);
		sql.append(")");
		return sql.toString();
	}

}
