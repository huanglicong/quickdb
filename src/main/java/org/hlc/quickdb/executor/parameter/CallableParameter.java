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
package org.hlc.quickdb.executor.parameter;

// TODO: Auto-generated Javadoc
/**
 * 子程序参数，说明参数类型与值.
 * 
 * @author huanglicong
 * @since 1.0 2013年12月6日 上午12:10:04
 */
public class CallableParameter {

	/**
	 * The Enum CallableType.
	 * 
	 * @author huanglicong
	 * @since quickdb
	 * @version V1.3
	 */
	public enum CallableType {

		/** The in. */
		IN,
		/** The out. */
		OUT,
		/** The inout. */
		INOUT;
	}

	/** The type. */
	private final CallableType type;

	/** The value. */
	private final Object value;

	/**
	 * Instantiates a new callable parameter.
	 * 
	 * @param type the type
	 * @param value the value
	 */
	public CallableParameter(CallableType type, Object value) {

		this.type = type;
		this.value = value;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public CallableType getType() {

		return type;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Object getValue() {

		return value;
	}

}
