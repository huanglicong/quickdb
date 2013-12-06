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
package org.hlc.quickdb.executor.sequence;

import java.util.List;

import org.hlc.quickdb.builder.BaseSqlBuilder;
import org.hlc.quickdb.metadata.ColumnMetadata;
import org.hlc.quickdb.metadata.TableMetadata;
import org.hlc.quickdb.session.Session;

/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 下午2:04:34
 */
public class OracleSequenceGenerater implements SequenceGenerater {

	private String sequenceName;

	public OracleSequenceGenerater(String sequenceName) {

		this.sequenceName = sequenceName;
	}

	@Override
	public void generation(Object bean, Session session) {

		TableMetadata metadata = session.getConfiguration().findTableMetadata(bean.getClass());
		if (metadata == null) {
			return;
		}
		List<ColumnMetadata> columns = metadata.getSequencekeys();
		if (columns == null || columns.isEmpty()) {
			return;
		}
		for (ColumnMetadata item : columns) {
			Long id = session.selectOne("SELECT " + sequenceName + ".NEXTVAL FROM DUAL", null, Long.class);
			if (id != null && item.getField().getType() == String.class) {
				BaseSqlBuilder.setValue(bean, id.toString(), item.getField());
			} else {
				BaseSqlBuilder.setValue(bean, id, item.getField());
			}
		}

	}

}
