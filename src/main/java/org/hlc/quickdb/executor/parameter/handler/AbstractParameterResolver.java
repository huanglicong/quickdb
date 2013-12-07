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

import java.util.ArrayList;
import java.util.List;

import org.hlc.quickdb.executor.parameter.StatementParameter;
import org.hlc.quickdb.util.TokenHandler;

// TODO: Auto-generated Javadoc
/**
 * 定义抽象的StatementParameter解析类，结合各种方法将参数对象进行解析得到Statement执行所需要的各种信息.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月28日 下午2:40:30
 */
public abstract class AbstractParameterResolver implements TokenHandler {

	/** The statement parameter list. */
	private final List<StatementParameter> statementParameterList = new ArrayList<StatementParameter>();

	/** The target. */
	protected final Object target;

	/** The count. */
	private int count = 1;

	/**
	 * Instantiates a new abstract parameter resolver.
	 * 
	 * @param target
	 *            the target
	 */
	public AbstractParameterResolver(Object target) {
		this.target = target;
	}

	/**
	 * Handle token.
	 * 
	 * @param content
	 *            the content
	 * @return the string
	 */
	@Override
	public String handleToken(String content) {

		try {
			return handler(count, content);
		} finally {
			count++;
		}
	}

	/**
	 * Handler.
	 * 
	 * @param index
	 *            the index
	 * @param paramName
	 *            the param name
	 * @return the string
	 */
	public abstract String handler(int index, String paramName);

	/**
	 * 添加一个StatementParameter参数.
	 * 
	 * @param param
	 *            the param
	 */
	public void addParameter(StatementParameter param) {

		this.statementParameterList.add(param);
	}

	/**
	 * Gets the statement parameter list.
	 * 
	 * @return the statement parameter list
	 */
	public List<StatementParameter> getStatementParameterList() {

		return statementParameterList;
	}

	/**
	 * Gets the target.
	 * 
	 * @return the target
	 */
	public Object getTarget() {

		return target;
	}

}
