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

import org.hlc.quickdb.annotation.Sequence;
import org.hlc.quickdb.session.Session;

/**
 * 序列生成器接口，用于生成{@link Sequence}注解字段的值.
 * 
 * @author huanglicong
 * @since 1.0 2013年11月24日 下午1:05:11
 */
public interface SequenceGenerater {

	void generation(Object bean, Session session);
}
