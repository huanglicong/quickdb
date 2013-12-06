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
package org.hlc.quickdb.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.hlc.quickdb.executor.result.ResultHandler;

/**
 * 
 * Statement处理器，定义Statement的创建，参数装配，执行等方法，本质是对Statement相关操作进行封装.
 * 
 * @author huanglicong
 * @version V1.0
 */
public interface StatementHandler {

	Statement prepare(Connection connection) throws SQLException;

	void batch(Statement statement) throws SQLException;

	int update(Statement statement) throws SQLException;

	<E> List<E> query(Statement statement, ResultHandler<E> resultHandler) throws SQLException;

}
