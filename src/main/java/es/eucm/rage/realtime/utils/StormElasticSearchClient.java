/**
 * Copyright (C) 2016 e-UCM (http://www.e-ucm.es/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.eucm.rage.realtime.utils;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Simple helper class for creating an ElasticSearch client on port 9300.
 */
public final class StormElasticSearchClient implements Serializable {

	private final EsConfig esConfig;

	public StormElasticSearchClient(EsConfig esConfig) {
		this.esConfig = esConfig;
	}

	/**
	 * Builds an ElasticSearch client on port 9300
	 * 
	 * @return
	 */
	public TransportClient construct() {
		TransportClient client = null;
		try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
					.addTransportAddress(new InetSocketTransportAddress(
							InetAddress.getByName(esConfig.getHost()), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return client;
	}
}
