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

import java.sql.Connection;

import javax.sql.DataSource;

// TODO: Auto-generated Javadoc
/**
 * TODO.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月21日 下午9:41:21
 */
public interface TransactionFactory {

	/**
	 * New transaction.
	 * 
	 * @param conn the conn
	 * @return the transaction
	 */
	Transaction newTransaction(Connection conn);

	/**
	 * New transaction.
	 * 
	 * @param dataSource the data source
	 * @return the transaction
	 */
	Transaction newTransaction(DataSource dataSource);

}
