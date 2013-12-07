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
package org.hlc.quickdb.executor.parameter.handler;

import ognl.Ognl;
import ognl.OgnlException;

import org.hlc.quickdb.builder.BuilderException;
import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.type.TypeHandler;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月28日 下午3:01:33
 */
public class MetadateParameterResolver extends AbstractParameterResolver {

	private TableMetadata table;

	public MetadateParameterResolver(TableMetadata table, Object target) {

		super(target);
		this.table = table;
	}

	@Override
	public String handler(int index, String paramName) {

		ColumnMetadata column = table.findColumnMetadata(paramName);
		if (column == null) {
			throw new BuilderException("在" + table.getTableName() + "中没有找到对应的字段");
		}
		TypeHandler<?> typeHandler = column.getTypeHandler();
		if (typeHandler == null) {
			throw new BuilderException(table.getTableName() + "中" + column.getName() + "没有类型的处理器");
		}
		// TODO 后续加入NULL处理类型
		Object value = null;
		try {
			value = Ognl.getValue(paramName, target);
		} catch (OgnlException e) {
			throw new BuilderException("参数获取错误，", e);
		}
		addParameter(new StatementParameter(index, typeHandler, value));
		return "?";
	}

}
