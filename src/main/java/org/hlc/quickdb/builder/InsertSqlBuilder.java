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

import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.session.Session;

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
	private Session session;

	public InsertSqlBuilder(Object record, TableMetadata table, boolean selective, boolean capital) {

		this.record = record;
		this.table = table;
		this.selective = selective;
		this.capital = capital;
	}

	@Override
	public SqlSource build() {

		StringBuilder before = new StringBuilder();
		StringBuilder after = new StringBuilder();
		ColumnMetadata temp = null;
		Iterator<ColumnMetadata> iterator = table.iterator();
		List<ColumnMetadata> list = new ArrayList<ColumnMetadata>();
		List<StatementParameter> params = new ArrayList<StatementParameter>();
		int index = 0;
		Object value = null;
		// 抽取SQL参数
		while (iterator.hasNext()) {
			temp = iterator.next();
			//自动生成Sequence
			if (temp.getSequenceGenerater() != null) {
				temp.getSequenceGenerater().generation(record, session);
			}
			value = getValue(record, temp.getField());
			if (selective && value == null) {
				continue;
			}
			params.add(new StatementParameter(index, temp.getTypeHandler(), value));
			list.add(temp);
			index++;
		}

		// 构建SQL语句
		int size = list.size();
		index = 0;
		for (ColumnMetadata item : list) {
			if (capital) {
				before.append(item.getName().toUpperCase(Locale.getDefault()));
			} else {
				before.append(item.getName());
			}
			after.append("?");
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

		SqlSource sqlSource = new SqlSource();
		sqlSource.setSql(sql.toString());
		sqlSource.add(params.toArray(new StatementParameter[] {}));
		return sqlSource;
	}
}
