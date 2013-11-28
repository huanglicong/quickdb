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

import java.util.List;

import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.statement.StatementParameter;
import org.hlc.quickdb.statement.StatementParameterHandler;
import org.hlc.quickdb.util.GenericTokenParser;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月22日 下午6:23:34
 */
public class SqlSource {

	private String sql;
	private final List<StatementParameter> params;

	public SqlSource(String sql, Object params, Configuration configuration) {

		StatementParameterHandler parameterHandler = new StatementParameterHandler(configuration, params);
		GenericTokenParser parser = new GenericTokenParser("${", "}", parameterHandler);
		this.sql = parser.parse(sql);
		this.params = parameterHandler.getStatementParameterList();
	}

	public String getSql() {

		return sql;
	}

	public void setSql(String sql) {

		this.sql = sql;
	}

	public List<StatementParameter> getParams() {

		return params;
	}

}
