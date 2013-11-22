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

// TODO: Auto-generated Javadoc
/**
 * 构建异常.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午9:13:42
 */
public class BuilderException extends IllegalArgumentException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1023632503693143074L;

	/**
	 * Instantiates a new builder exception.
	 */
	public BuilderException() {

		super();
	}

	/**
	 * Instantiates a new builder exception.
	 * 
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public BuilderException(String arg0, Throwable arg1) {

		super(arg0, arg1);
	}

	/**
	 * Instantiates a new builder exception.
	 * 
	 * @param arg0 the arg0
	 */
	public BuilderException(String arg0) {

		super(arg0);
	}

	/**
	 * Instantiates a new builder exception.
	 * 
	 * @param arg0 the arg0
	 */
	public BuilderException(Throwable arg0) {

		super(arg0);
	}

}
