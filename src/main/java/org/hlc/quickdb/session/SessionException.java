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
package org.hlc.quickdb.session;

import org.hlc.quickdb.exception.PersistenceException;

// TODO: Auto-generated Javadoc
/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午9:26:56
 */
public class SessionException extends PersistenceException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -766115346466857796L;

	/**
	 * Instantiates a new session exception.
	 */
	public SessionException() {

		super();
	}

	/**
	 * Instantiates a new session exception.
	 * 
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public SessionException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Instantiates a new session exception.
	 * 
	 * @param arg0 the arg0
	 */
	public SessionException(String arg0) {

		super(arg0);
	}

	/**
	 * Instantiates a new session exception.
	 * 
	 * @param arg0 the arg0
	 */
	public SessionException(Throwable arg0) {

		super(arg0);
	}

}
