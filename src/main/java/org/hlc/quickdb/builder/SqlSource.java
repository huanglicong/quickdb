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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hlc.quickdb.executor.parameter.StatementParameter;

/**
 * 
 * 对SQL以及SQL相关的参数进行封装，需要支持批处理是需要的参数集合.
 * 
 * @author huanglicong
 * @version V1.0
 */
public class SqlSource {

	/** 最终执行的SQL语句 */
	private String sql;

	/** 执行SQL所需的参数，支持批处理 */
	private Set<StatementParameter[]> params = new HashSet<StatementParameter[]>();

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public boolean isEmpty() {
		return params.isEmpty();
	}

	public Iterator<StatementParameter[]> iterator() {
		return params.iterator();
	}

	public boolean add(StatementParameter[] e) {
		return params.add(e);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SqlSource [sql=");
		builder.append(sql);
		builder.append(", params=");
		builder.append(params);
		builder.append("]");
		return builder.toString();
	}

}
