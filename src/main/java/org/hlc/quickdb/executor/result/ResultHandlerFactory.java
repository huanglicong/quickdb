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

import java.util.Map;

import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.session.Configuration;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月29日 下午2:24:16
 */
public class ResultHandlerFactory {

	private Configuration configuration;

	public ResultHandlerFactory(Configuration configuration) {
		this.configuration = configuration;
	}

	@SuppressWarnings("unchecked")
	public <E> ResultHandler<E> createResultHandler(Class<E> resultType) {

		ResultHandler<E> resultHandler = null;
		TableMetadata table = configuration.findTableMetadata(resultType);
		if (table != null) {
			resultHandler = new MetaResultHandler<E>(table);
		} else if (resultType == Map.class) {
			resultHandler = (ResultHandler<E>) new MapResultHandler();
		} else {
			resultHandler = new DefaultResultHandler<E>(resultType);
		}
		return resultHandler;
	}

}
