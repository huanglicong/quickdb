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
package org.hlc.quickdb.executor.result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.hlc.quickdb.builder.BuilderException;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.util.DatabaseUtils;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月29日 下午12:01:40
 */
public class MetaResultHandler<E> implements ResultHandler<E> {

	private TableMetadata table;

	public MetaResultHandler(TableMetadata table) {
		this.table = table;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> handleResult(ResultSet resultSet) throws SQLException {

		List<E> list = new ArrayList<E>();
		E temp = null;
		Object value = null;
		try {
			while (resultSet.next()) {
				temp = (E) table.getClasses().newInstance();
				for (ColumnMetadata item : table.getColumns()) {
					System.out.println(item);
					value = item.getTypeHandler().getResult(resultSet, item.getName());
					Ognl.setValue(item.getName(), temp, value);
				}
				list.add(temp);
			}
			DatabaseUtils.colseResultSet(resultSet);
		} catch (InstantiationException e) {
			throw new BuilderException(e);
		} catch (IllegalAccessException e) {
			throw new BuilderException(e);
		} catch (OgnlException e) {
			throw new BuilderException(e);
		}
		return list;
	}
}
