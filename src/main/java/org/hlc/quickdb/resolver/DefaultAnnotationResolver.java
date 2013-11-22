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
package org.hlc.quickdb.resolver;

import java.lang.reflect.Field;

import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;

// TODO: Auto-generated Javadoc
/**
 * 解析成适合Mybatis ORM框架的原数据.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月12日 下午3:39:25
 */
public class DefaultAnnotationResolver extends AbstractAnnotationResolver {

	/**
	 * Resolve.
	 * 
	 * @param classes the classes
	 * @return the table metadata
	 */
	@Override
	public TableMetadata resolve(Class<?> classes) {

		TableMetadata tableMetadata = super.resolveTableMetadata(classes);
		if (tableMetadata == null) {
			return null;
		}
		Field[] fields = classes.getDeclaredFields();
		ColumnMetadata columnMetadata = null;
		for (Field item : fields) {
			columnMetadata = super.resolveColumnMetadata(item);
			if (columnMetadata == null) {
				continue;
			}
			tableMetadata.add(columnMetadata);
		}
		return tableMetadata;
	}
}
