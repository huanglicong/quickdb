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

import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.session.Configuration;

// TODO: Auto-generated Javadoc
/**
 * 参数分解工厂，更具参数的类型来决定采用什么分解器来进行分解.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月28日 下午2:47:29
 */
public class ParameterResolverFactory {

	/** The configuration. */
	private final Configuration configuration;

	/**
	 * Instantiates a new parameter resolver factory.
	 * 
	 * @param configuration the configuration
	 */
	public ParameterResolverFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Creates a new ParameterResolver object.
	 * 
	 * @param paramType the param type
	 * @param target the target
	 * @return the abstract parameter resolver
	 */
	public AbstractParameterResolver createParameterResolver(Class<?> paramType, Object target) {

		AbstractParameterResolver parameterResolver = null;
		if (paramType == null) {
			parameterResolver = new EmptyParameterResolver(target);
		} else {
			// paramType是否是Quickdb标注的数据对象，如果是采用元数据对象分解器，如果不是采用普通对象分解器
			TableMetadata metadata = configuration.findTableMetadata(paramType);
			if (metadata == null) {
				parameterResolver = new DefaultParameterResolver(configuration, target);
			} else {
				parameterResolver = new MetadateParameterResolver(metadata, target);
			}
		}
		return parameterResolver;
	}

}
