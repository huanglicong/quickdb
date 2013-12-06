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
import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.type.TypeHandler;

/**
 * 默认的StatementParameter分解器，用于处理普通的Java对象，即非Quickdb注解的数据对象.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月28日 下午2:39:19
 */
public class DefaultParameterResolver extends AbstractParameterResolver {

	private Configuration configuration;

	public DefaultParameterResolver(Configuration configuration, Object target) {

		super(target);
		this.configuration = configuration;
	}

	@Override
	public String handler(int index, String paramName) {

		Object value = null;
		try {
			value = Ognl.getValue(paramName, target);
		} catch (OgnlException e) {
			throw new BuilderException("参数获取错误，", e);
		}
		if (value == null) {
			throw new IllegalArgumentException("暂时不支持普通对象字段为空");
		}
		TypeHandler<?> typeHandler = configuration.findTypeHandler(value.getClass());
		if (typeHandler == null) {
			throw new BuilderException("没有找到支持" + value.getClass() + "类型的处理器");
		}
		addParameter(new StatementParameter(paramName, index, 0, typeHandler, null, value));
		return "?";
	}
}
