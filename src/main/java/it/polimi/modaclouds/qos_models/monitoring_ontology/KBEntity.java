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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KBEntity {
	
	private Logger logger = LoggerFactory
			.getLogger(this.getClass().getName());
	
	private String shortURI;
	private String uri;
	
	public KBEntity() {
		String id = UUID.randomUUID().toString();
		this.shortURI = MO.prefix + ":" + id;
		this.uri = MO.URI + id;
	}
	
	public String getShortURI() {
		return shortURI;
	}
	
	public String getShortClassURI() {
		return MO.prefix + ":" + this.getClass().getSimpleName();
	}
	
	public String getClassURI() {
		return MO.URI + this.getClass().getSimpleName();
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
		try {
			URI theUri = new URI(uri);
			String[] segments = theUri.getPath().split("/");
			String id = segments[segments.length - 1];
			this.shortURI = MO.prefix + ":" + id;
		} catch (URISyntaxException e) {
			logger.error("Error while setting short uri", e);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((shortURI == null) ? 0 : shortURI.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KBEntity other = (KBEntity) obj;
		if (shortURI == null) {
			if (other.shortURI != null)
				return false;
		} else if (!shortURI.equals(other.shortURI))
			return false;
		if (uri == null) {
			if (other.uri != null)
				return false;
		} else if (!uri.equals(other.uri))
			return false;
		return true;
	}
	
//	public KBEntity copyPaste2Provider(String provider) {
//		KBEntity newEntity = null;
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
//		return newEntity;
//	}

	
}
