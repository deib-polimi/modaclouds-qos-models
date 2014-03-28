/**
 * Copyright 2014 deib-polimi
 * Contact: deib-polimi <marco.miglierina@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package it.polimi.modaclouds.qos_models.monitoring_ontology;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KBEntity {
	
	private Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());
	
	private String id;
	
	public KBEntity() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public KBEntity copyPaste2Provider(String provider) {
		KBEntity newEntity = null;
//		try {
//			Class<? extends KBEntity> clazz = this.getClass();
//			newEntity = clazz.newInstance();
//			Map<String,Object> properties = PropertyUtils.describe(this);
//			for (String p: properties.keySet()) {
//				if (p.equals("")) {
//					
//				}
//			}
//		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//			logger.error("Error copy-pasting KBEntity to provider",e);
//		}
		return newEntity;
	}
}
