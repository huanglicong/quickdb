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
 * 映射数据库表中的主键字段，如果标注多个字段，则表示被标注的多个字段组合成复合主键.
 * 
 * @author huanglicong
 * @since 1.0 2013-6-30 下午8:08:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PrimaryKey {

	/**
	 * 
	 * 字段名称，默认为被标注类的字段名.
	 * 
	 * @return
	 */
	String value() default "";

	/**
	 * 
	 * 字段类型，默认为被标注类的字段类型。可以直接指定Java类，如<code>java.lang.String</code>，
	 * 但默认要配置TypeConveter.
	 * 
	 * @see org.hlc.quickdb.core.conveter.TypeConveter
	 * @see org.hlc.quickdb.core.resolver.impl.resolver.SchemaResolverFactory
	 * @return
	 */
	String type() default "";

}