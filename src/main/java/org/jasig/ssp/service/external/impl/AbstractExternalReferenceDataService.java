/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.ssp.service.external.impl;

import java.util.List;

import org.jasig.ssp.dao.external.AbstractExternalReferenceDataDao;
import org.jasig.ssp.model.external.AbstractExternalReferenceData;
import org.jasig.ssp.service.ObjectNotFoundException;
import org.jasig.ssp.service.external.ExternalDataService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class which provides a building block for creating an external data
 * service.
 * 
 * @param <T>
 *            Any external data model class.
 */
@Transactional
public abstract class AbstractExternalReferenceDataService<T extends AbstractExternalReferenceData> extends AbstractExternalDataService<T> implements
		ExternalDataService<T> {
 
	public T getByCode(String code) throws ObjectNotFoundException {
		return ((AbstractExternalReferenceDataDao<T>)getDao()).getByCode(code);
	}
	
	public List<T> getAll() {
		return ((AbstractExternalReferenceDataDao<T>)getDao()).getAll();
	}

}