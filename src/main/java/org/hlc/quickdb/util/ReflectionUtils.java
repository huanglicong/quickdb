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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

// TODO: Auto-generated Javadoc
/**
 * The Class ReflectionUtils.
 * 
 * @author huanglicong
 * @since quickdb
 * @version V1.3
 */
public class ReflectionUtils {

	/**
	 * Find method.
	 * 
	 * @param clazz the clazz
	 * @param name the name
	 * @param paramTypes the param types
	 * @return the method
	 */
	public static Method findMethod(Class<?> clazz, String name, Class<?>... paramTypes) {

		Class<?> searchType = clazz;
		while (searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (Method method : methods) {
				if (name.equals(method.getName()) && (paramTypes == null || Arrays.equals(paramTypes, method.getParameterTypes()))) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * Find set method.
	 * 
	 * @param targetClass the target class
	 * @param field the field
	 * @return the method
	 */
	public static Method findSetMethod(Class<?> targetClass, Field field) {

		Class<?>[] paramTypes = new Class<?>[] { field.getType() };

		String name = field.getName();
		String setterMethodName = name;
		if (!name.startsWith("set")) {
			setterMethodName = "set" + StringUtils.capitalize(name);
		}
		return findMethod(targetClass, setterMethodName, paramTypes);
	}

	/**
	 * Find get method.
	 * 
	 * @param targetClass the target class
	 * @param field the field
	 * @return the method
	 */
	public static Method findGetMethod(Class<?> targetClass, Field field) {

		String name = field.getName();
		String getterMethodName = name;
		if (!name.startsWith("get")) {
			getterMethodName = "get" + StringUtils.capitalize(name);
		}
		return findMethod(targetClass, getterMethodName);
	}

	/**
	 * Make accessible.
	 * 
	 * @param method the method
	 */
	public static void makeAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) && !method.isAccessible()) {
			method.setAccessible(true);
		}
	}

	/**
	 * Invoke setter method.
	 * 
	 * @param target the target
	 * @param name the name
	 * @param value the value
	 * @param type the type
	 * @throws Exception the exception
	 */
	public static void invokeSetterMethod(Object target, String name, Object value, Class<?> type) throws Exception {

		Class<?>[] paramTypes = (type != null ? new Class<?>[] { type } : null);

		String setterMethodName = name;
		if (!name.startsWith("set")) {
			setterMethodName = "set" + StringUtils.capitalize(name);
		}
		Method method = findMethod(target.getClass(), setterMethodName, paramTypes);
		if (method == null && !setterMethodName.equals(name)) {
			setterMethodName = name;
			method = ReflectionUtils.findMethod(target.getClass(), setterMethodName, paramTypes);
		}
		if (method == null) {
			return;
		}
		makeAccessible(method);
		method.invoke(target, new Object[] { value });
	}

	/**
	 * Invoke getter method.
	 * 
	 * @param target the target
	 * @param name the name
	 * @return the object
	 * @throws Exception the exception
	 */
	public static Object invokeGetterMethod(Object target, String name) throws Exception {

		String getterMethodName = name;
		if (!name.startsWith("get")) {
			getterMethodName = "get" + StringUtils.capitalize(name);
		}
		Method method = ReflectionUtils.findMethod(target.getClass(), getterMethodName);
		if (method == null && !getterMethodName.equals(name)) {
			getterMethodName = name;
			method = ReflectionUtils.findMethod(target.getClass(), getterMethodName);
		}
		if (method == null) {
			return null;
		}
		makeAccessible(method);
		return method.invoke(target);
	}

	/**
	 * Checks if is gets the setter method.
	 * 
	 * @param field the field
	 * @param target the target
	 * @return true, if is gets the setter method
	 */
	public static boolean isGetSetterMethod(Field field, Object target) {
		boolean result = false;
		String name = field.getName();
		String setterMethodName = name;
		if (!name.startsWith("set")) {
			setterMethodName = "set" + StringUtils.capitalize(name);
		}
		Class<?>[] paramTypes = (field.getType() != null ? new Class<?>[] { field.getType() } : null);
		Method method = findMethod(target.getClass(), setterMethodName, paramTypes);
		if (method != null) {
			result = true;
		}

		String getterMethodName = name;
		if (!name.startsWith("get")) {
			getterMethodName = "get" + StringUtils.capitalize(name);
		}
		method = findMethod(target.getClass(), getterMethodName);
		if (method != null) {
			result = true;
		}
		return result;
	}

	/**
	 * Support class.
	 * 
	 * @param a the a
	 * @param b the b
	 * @return true, if successful
	 */
	@SuppressWarnings("rawtypes")
	public static boolean supportClass(Class a, Class b) {

		if (a == null || b == null) {
			return false;
		}
		if (a.equals(b)) {
			return true;
		}
		if (Object.class.equals(a)) {
			return false;
		}
		Class[] ifs = a.getInterfaces();
		if (ifs == null) {
			return false;
		}
		for (Class temp : ifs) {
			if (supportClass(temp, b)) {
				return true;
			}
		}
		return supportClass(a.getSuperclass(), b);
	}

}
