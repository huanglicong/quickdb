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
package org.hlc.quickdb.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 将数据库主键字段与Java字段做映射，复合主键重复标注.
 * 
 * @author huanglicong
 * @since 1.0 2013-6-30 下午8:08:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {

	/**
	 * 
	 * 告诉我你定义的数据库字段名称是什么.
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 
	 * 告诉我你定义的哪种数据库类型.
	 * 
	 * @return
	 */
	int type() default Integer.MIN_VALUE;

}