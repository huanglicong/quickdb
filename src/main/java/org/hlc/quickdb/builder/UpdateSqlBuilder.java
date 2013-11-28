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
 * @since 1.0 2013年11月21日 下午11:27:02
 */
public class UpdateSqlBuilder extends BaseSqlBuilder {

	private Object record;
	private TableMetadata table;
	private boolean selective;
	private boolean capital;

	public UpdateSqlBuilder(Object record, TableMetadata table, boolean selective, boolean capital) {

		this.record = record;
		this.table = table;
		this.selective = selective;
		this.capital = capital;
	}

	@Override
	public String build() {

		StringBuilder sql = new StringBuilder();

		// 1.构建头部分
		sql.append("update ");
		if (capital) {
			sql.append(table.getTableName().toUpperCase(Locale.getDefault()));
		} else {
			sql.append(table.getTableName());
		}

		// 2.构建设值部分
		sql.append(" set ");
		Iterator<ColumnMetadata> iterator = table.getNotPrimarykeys().iterator();
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
				sql.append(item.getName().toUpperCase(Locale.getDefault()));
			} else {
				sql.append(item.getName());
			}
			sql.append(" = ${" + item.getField().getName() + "}");
			if (index < size - 1) {
				sql.append(", ");
			}
			index++;
		}
		if (index == 0) {
			throw new IllegalArgumentException("至少更新一个非主键字段");
		}

		// 3.构建条件部分
		sql.append(" where ");
		Iterator<ColumnMetadata> keyIterator = table.getPrimarykeys().iterator();
		size = table.getPrimarykeys().size();
		index = 0;
		while (keyIterator.hasNext()) {
			temp = keyIterator.next();
			if (getValue(record, temp.getField()) == null) {
				throw new IllegalArgumentException(temp.getName() + "主键字段值不能为空");
			}
			sql.append(temp.getName()).append(" = ${" + temp.getField().getName() + "}");
			if (index < size - 1) {
				sql.append(" and ");
			}
			index++;
		}
		return sql.toString();
	}

}