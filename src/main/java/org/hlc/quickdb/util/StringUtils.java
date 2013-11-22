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
package org.hlc.quickdb.util;

import java.util.Date;

/**
 * 
 * 字符串工具，提供常用字符串操作的静态方法.
 * 
 * @author huanglicong
 * @since 1.0 2013-6-30 下午9:22:58
 */
public final class StringUtils {

	private StringUtils() {
	}

	/**
	 * 
	 * 判断是否未空.
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {

		if (source == null || "".equals(source))
			return true;

		for (int i = 0; i < source.length(); i++) {
			char c = source.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * 是否匹配正则表达式,如果不匹配返回false，如果匹配返回true.
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static boolean matches(String source, String pattern) {

		if (source == null || !source.matches(pattern)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 扩展String的trim方法，如果字符串对象为<code>null</code>，那么则返回""字符串.
	 * 
	 * @param source
	 * @return
	 */
	public static String trim(String source) {

		return source == null ? "" : source.trim();
	}

	/**
	 * 
	 * 将数组转换成以分隔符分割字符串.
	 * 
	 * @param array
	 * @param separator
	 * @return
	 */
	public static String arrayToString(String[] array, String separator) {

		if (array == null || array.length == 0) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		for (String temp : array) {
			result.append(temp).append(separator);
		}
		return result.substring(0, result.length() - 2);
	}

	/**
	 * 
	 * 将含有分隔符的字符串转换成数组.
	 * 
	 * @param source
	 * @param separator
	 * @return
	 */
	public static String[] toArray(String source, String separator) {

		if (isEmpty(source)) {
			return null;
		}
		return source.split(",");
	}

	/**
	 * 
	 * 将对象数组转换为字符串数组，采用对象toString方法进行转换.
	 * 
	 * @param sources
	 * @return
	 */
	public static String[] toArray(Object[] sources) {

		if (sources == null || sources.length == 0) {
			return null;
		}
		String[] temps = new String[sources.length];
		String value = "";
		for (int i = 0; i < temps.length; i++) {

			if (sources[i] == null) {
				continue;
			}
			if (Date.class.equals(sources[i].getClass())) {
				value = DateUtils.toDateString((Date) sources[i], DateUtils.DATE_TIME_FORMAT);
			} else {
				value = sources[i].toString();
			}
			temps[i] = value;
		}
		return temps;
	}

	/**
	 * 
	 * 首字母大写.
	 * 
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {

		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str.length());
		sb.append(Character.toUpperCase(str.charAt(0)));
		sb.append(str.toLowerCase().substring(1));
		return sb.toString();
	}

	/**
	 * 
	 * 比给定的长度更长.
	 * 
	 * @param source
	 * @param length
	 * @return
	 */
	public static boolean larger(String source, int length) {

		return (source != null && source.length() > length);
	}

	/**
	 * 
	 * 扩展对象的toString方法，如果为空则返回""字符串.
	 * 
	 * @param source
	 * @return
	 */
	public static String toString(Object source) {

		return (source == null ? "" : source.toString());
	}

}
