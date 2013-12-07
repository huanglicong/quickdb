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

import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.executor.parameter.handler.AbstractParameterResolver;
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.util.GenericTokenParser;

/**
 * TODO.
 * 
 * @author windows
 * @version V1.0
 */
public class UserDefinedSqlBuilder implements SqlBuilder {

	private String sql;
	private Object params;
	private Configuration configuration;

	public UserDefinedSqlBuilder(String sql, Object params, Configuration configuration) {
		this.sql = sql;
		this.params = params;
		this.configuration = configuration;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hlc.quickdb.builder.SqlBuilder#build()
	 */
	@Override
	public SqlSource build() {

		AbstractParameterResolver parameterHandler = configuration.newParameterResolver((params == null ? null : params.getClass()), params);
		GenericTokenParser parser = new GenericTokenParser("${", "}", parameterHandler);
		this.sql = parser.parse(sql);
		List<StatementParameter> list = parameterHandler.getStatementParameterList();

		SqlSource sqlSource = new SqlSource();
		sqlSource.setSql(sql.toString());
		sqlSource.add(list.toArray(new StatementParameter[] {}));
		return sqlSource;
	}

}
