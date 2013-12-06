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

import org.hlc.quickdb.builder.BuilderException;

/**
 * 由于SQL中不含有OGNL表达式，因此该对象不会被调用.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月29日 上午10:14:58
 */
public class EmptyParameterResolver extends AbstractParameterResolver {

	public EmptyParameterResolver(Object target) {

		super(target);
	}

	@Override
	public String handler(int index, String paramName) {

		throw new BuilderException("由于由于SQL中不含有OGNL表达式，该方法不应该被调用");
	}

}
