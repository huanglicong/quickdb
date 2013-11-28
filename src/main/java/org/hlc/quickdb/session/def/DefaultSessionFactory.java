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
package org.hlc.quickdb.session.def;

import org.hlc.quickdb.session.Configuration;
import org.hlc.quickdb.session.Session;
import org.hlc.quickdb.session.SessionFactory;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月22日 上午12:08:01
 */
public class DefaultSessionFactory implements SessionFactory {

	private Configuration configuration;

	public DefaultSessionFactory(Configuration configuration) {

		this.configuration = configuration;
	}

	@Override
	public Session openSession() {

		return new DefaultSession(configuration, configuration.newExecutor());
	}
}
