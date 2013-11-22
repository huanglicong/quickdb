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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 日期时间工具类，提供常用操作日期时间的静态方法.
 * 
 * @author huanglicong
 * @since 1.0 2013-6-30 下午8:56:06
 */
public final class DateUtils {

	// 用于转换
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm";
	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PARRERN = "\\d{1,4}\\-\\d{1,2}\\-\\d{1,2}";
	// 用于判断
	public static final String DATE_TIME_PATTERN = "\\d{1,4}\\-\\d{1,2}\\-\\d{1,2}\\s+\\d{1,2}:\\d{1,2}:\\d{1,2}";
	public static final String PART_TIME_FORMAT = "yyyy-MM-dd HH:mm";

	private DateUtils() {
	}

	/**
	 * 
	 * 将日期格式"yyyy-MM-dd"或"yyyy-MM-dd HH:mm:ss"的字符串转换为Date对象.
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date toDate(String dateString) {

		if (StringUtils.matches(dateString, DATE_PARRERN)) {
			return toDate(dateString, DATE_FORMAT);
		}
		if (StringUtils.matches(dateString, DATE_TIME_PATTERN)) {
			return toDate(dateString, DATE_TIME_FORMAT);
		}
		return null;
	}

	/**
	 * 
	 * 指定某种格式的日期字符串转换成Date对象.
	 * 
	 * @param dateString
	 * @param pattern
	 * @return
	 */
	public static Date toDate(String dateString, String pattern) {
		if (dateString == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		ParsePosition position = new ParsePosition(0);
		Date strtodate = formatter.parse(dateString, position);
		return strtodate;
	}

	/**
	 * 
	 * 将Date对象转换成"yyyy-MM-dd"的字符串.
	 * 
	 * @param date
	 * @return
	 */
	public static String toDateString(Date date) {
		return toDateString(date, DATE_FORMAT);
	}

	/**
	 * 
	 * 将Date对象转换成指定格式的日期字符串.
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String toDateString(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 
	 * 比较两个日期的大小.
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long compare(Date d1, Date d2) {

		if (d1 == null) {
			throw new NullPointerException("比较时间参数d1不能为空");
		}
		if (d2 == null) {
			throw new NullPointerException("比较时间参数d2不能为空");
		}
		return d1.getTime() - d2.getTime();
	}

	/**
	 * 
	 * 指定部分{@link Calendar}部分增减数目.
	 * 
	 * @param date
	 * @param type
	 * @param num
	 * @return
	 */
	public static Date set(Date date, int type, int num) {

		if (num < 1) {
			return date;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(type, num);
		return calendar.getTime();
	}

}