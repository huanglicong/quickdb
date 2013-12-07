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

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午11:29:57
 */
public class DeleteSqlBuilder extends BaseSqlBuilder {

	private TableMetadata table;
	private boolean capital;
	private Object params;

	public DeleteSqlBuilder(Object params, TableMetadata table, boolean capital) {

		this.table = table;
		this.capital = capital;
		this.params = params;
	}

	@Override
	public SqlSource build() {

		StringBuilder sql = new StringBuilder();

		// 1.构建头部分
		sql.append("delete from ");
		if (capital) {
			sql.append(table.getTableName().toUpperCase(Locale.getDefault()));
		} else {
			sql.append(table.getTableName());
		}

		// 2.构建条件部分
		sql.append(" where ");
		Iterator<ColumnMetadata> keyIterator = table.getPrimarykeys().iterator();
		ColumnMetadata temp = null;
		Object value = null;
		int index = 0;
		int size = table.getPrimarykeys().size();
		List<StatementParameter> params = new ArrayList<StatementParameter>();
		while (keyIterator.hasNext()) {
			temp = keyIterator.next();
			if (size > 1) {
				value = getValue(this.params, temp.getField());
			} else {
				value = params;
			}
			sql.append(temp.getName()).append(" = ?");
			if (index < size - 1) {
				sql.append(" and ");
			}
			params.add(new StatementParameter(index, temp.getTypeHandler(), value));
			index++;
		}

		SqlSource sqlSource = new SqlSource();
		sqlSource.setSql(sql.toString());
		sqlSource.add(params.toArray(new StatementParameter[] {}));
		return sqlSource;
	}

}
