
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
package org.hlc.quickdb.transaction;

import org.hlc.quickdb.exception.PersistenceException;

// TODO: Auto-generated Javadoc
/**
 * TODO.
 *
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午9:45:50
 */
public class TransactionException extends PersistenceException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7699990396404595050L;

	/**
	 * Instantiates a new transaction exception.
	 */
	public TransactionException() {
		
		super();
	}

	/**
	 * Instantiates a new transaction exception.
	 *
	 * @param arg0 the arg0
	 * @param arg1 the arg1
	 */
	public TransactionException(String arg0, Throwable arg1) {
		
		super(arg0, arg1);
	}

	/**
	 * Instantiates a new transaction exception.
	 *
	 * @param arg0 the arg0
	 */
	public TransactionException(String arg0) {
		
		super(arg0);
	}

	/**
	 * Instantiates a new transaction exception.
	 *
	 * @param arg0 the arg0
	 */
	public TransactionException(Throwable arg0) {
		
		super(arg0);
	}

}

