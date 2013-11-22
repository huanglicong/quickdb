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
package org.hlc.quickdb.metadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.hlc.quickdb.util.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月12日 下午3:26:07
 */
public class TableMetadata {

	/** 表名. */
	private String tableName;

	/** 主键字段列表. */
	private final Set<ColumnMetadata> primarykeys = new LinkedHashSet<ColumnMetadata>();

	/** 序列字段列表. */
	private final Set<ColumnMetadata> sequencekeys = new LinkedHashSet<ColumnMetadata>();

	/** 所有字段. */
	private final Set<ColumnMetadata> columns = new LinkedHashSet<ColumnMetadata>();

	/** The classes. */
	private Class<?> classes;

	/**
	 * Instantiates a new table metadata.
	 */
	public TableMetadata() {
		super();
	}

	/**
	 * Instantiates a new table metadata.
	 * 
	 * @param tableName the table name
	 * @param classes the classes
	 */
	public TableMetadata(String tableName, Class<?> classes) {
		super();
		this.tableName = tableName;
		this.classes = classes;
	}

	/**
	 * Adds the.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean add(ColumnMetadata arg0) {

		if (arg0.primaryKey) {
			primarykeys.add(arg0);
		}
		if (StringUtils.isEmpty(arg0.getSequence())) {
			sequencekeys.add(arg0);
		}
		return columns.add(arg0);
	}

	/**
	 * Adds the all.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean addAll(Collection<? extends ColumnMetadata> arg0) {

		for (ColumnMetadata item : arg0) {
			if (item.primaryKey) {
				primarykeys.add(item);
			}
			if (StringUtils.isEmpty(item.getSequence())) {
				sequencekeys.add(item);
			}
		}
		return columns.addAll(arg0);
	}

	/**
	 * Clear.
	 */
	public void clear() {

		primarykeys.clear();
		columns.clear();
	}

	/**
	 * Contains.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean contains(Object arg0) {
		return columns.contains(arg0);
	}

	/**
	 * Contains all.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean containsAll(Collection<?> arg0) {
		return columns.containsAll(arg0);
	}

	/**
	 * Equals.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean equals(Object arg0) {
		return columns.equals(arg0);
	}

	/**
	 * Hash code.
	 * 
	 * @return the int
	 */
	public int hashCode() {
		return columns.hashCode();
	}

	/**
	 * Checks if is empty.
	 * 
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return columns.isEmpty();
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public Iterator<ColumnMetadata> iterator() {
		return columns.iterator();
	}

	/**
	 * Removes the.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean remove(Object arg0) {
		return columns.remove(arg0);
	}

	/**
	 * Removes the all.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean removeAll(Collection<?> arg0) {
		return columns.removeAll(arg0);
	}

	/**
	 * Retain all.
	 * 
	 * @param arg0 the arg0
	 * @return true, if successful
	 */
	public boolean retainAll(Collection<?> arg0) {
		return columns.retainAll(arg0);
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public int size() {
		return columns.size();
	}

	/**
	 * To array.
	 * 
	 * @return the object[]
	 */
	public Object[] toArray() {
		return columns.toArray();
	}

	/**
	 * Gets the single primarykey.
	 * 
	 * @return the single primarykey
	 */
	public ColumnMetadata getSinglePrimarykey() {

		return new ArrayList<ColumnMetadata>(primarykeys).get(0);
	}

	/**
	 * Checks if is single primarykey.
	 * 
	 * @return true, if is single primarykey
	 */
	public boolean isSinglePrimarykey() {

		return (primarykeys.size() == 1);
	}

	/**
	 * To array.
	 * 
	 * @param <T> the generic type
	 * @param arg0 the arg0
	 * @return the t[]
	 */
	public <T> T[] toArray(T[] arg0) {
		return columns.toArray(arg0);
	}

	/**
	 * Gets the table name.
	 * 
	 * @return the table name
	 */
	public String getTableName() {

		return tableName;
	}

	/**
	 * Sets the table name.
	 * 
	 * @param tableName the new table name
	 */
	public void setTableName(String tableName) {

		this.tableName = tableName;
	}

	/**
	 * Gets the primarykeys.
	 * 
	 * @return the primarykeys
	 */
	public Set<ColumnMetadata> getPrimarykeys() {

		return primarykeys;
	}

	/**
	 * Gets the not primarykeys.
	 * 
	 * @return the not primarykeys
	 */
	public Set<ColumnMetadata> getNotPrimarykeys() {

		Set<ColumnMetadata> columns = new LinkedHashSet<ColumnMetadata>(this.columns);
		columns.removeAll(primarykeys);
		return columns;
	}

	/**
	 * Gets the columns.
	 * 
	 * @return the columns
	 */
	public Set<ColumnMetadata> getColumns() {

		return columns;
	}

	/**
	 * Gets the classes.
	 * 
	 * @return the classes
	 */
	public Class<?> getClasses() {

		return classes;
	}

	/**
	 * Sets the classes.
	 * 
	 * @param classes the new classes
	 */
	public void setClasses(Class<?> classes) {

		this.classes = classes;
	}

	/**
	 * Gets the sequencekeys.
	 * 
	 * @return the sequencekeys
	 */
	public List<ColumnMetadata> getSequencekeys() {

		return new ArrayList<ColumnMetadata>(sequencekeys);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TableMetadata [tableName=");
		builder.append(tableName);
		builder.append(", primarykeys=");
		builder.append(primarykeys);
		builder.append(", sequencekeys=");
		builder.append(sequencekeys);
		builder.append(", columns=");
		builder.append(columns);
		builder.append(", classes=");
		builder.append(classes);
		builder.append("]");
		return builder.toString();
	}

}
