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
package org.hlc.quickdb.statement;

import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.hlc.quickdb.builder.BuilderException;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.sequence.SequenceGenerater;
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.type.TypeHandler;
import org.hlc.quickdb.util.TokenHandler;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月23日 下午11:20:14
 */
public class StatementParameterHandler implements TokenHandler {

	private final List<StatementParameter> statementParameterList = new ArrayList<StatementParameter>();
	private final Object params;
	private Configuration configuration;
	private int count = 0;

	public StatementParameterHandler(Configuration configuration, Object params) {

		this.configuration = configuration;
		this.params = params;
	}

	@Override
	public String handleToken(String content) {

		// TODO 是否有必要SQL中指定JDBC类型
		if (params == null) {
			return "NULL";
		}
		Object value = null;
		try {
			value = Ognl.getValue(content, params);
		} catch (OgnlException e) {
			throw new BuilderException("参数获取错误，", e);
		}

		TableMetadata table = configuration.findTableMetadata(params.getClass());
		TypeHandler<?> typeHandler = null;
		SequenceGenerater sequenceGenerater = null;
		int jdbcType = Integer.MIN_VALUE;
		if (table != null) {
			ColumnMetadata column = table.findColumnMetadata(content);
			if (column != null) {
				typeHandler = column.getTypeHandler();
				sequenceGenerater = column.getSequenceGenerater();
//				jdbcType = column.get
			}
		}
		if (typeHandler == null && value != null) {
			typeHandler = configuration.getTypeHandlerRegistry().getHandler(value.getClass());
		}
		if (typeHandler == null) {
			return "NULL";
		}
//		statementParameterList.add(new StatementParameter(content, count++, t, typeHandler, sequenceGenerater, value));
		return "?";
	}

	public List<StatementParameter> getStatementParameterList() {

		return statementParameterList;
	}

}
