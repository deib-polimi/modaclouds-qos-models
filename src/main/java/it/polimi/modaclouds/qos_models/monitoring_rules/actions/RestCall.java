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
package it.polimi.modaclouds.qos_models.monitoring_rules.actions;

import it.polimi.modaclouds.qos_models.monitoring_rules.AbstractAction;
import it.polimi.modaclouds.qos_models.monitoring_rules.EnumErrorType;
import it.polimi.modaclouds.qos_models.monitoring_rules.Problem;
import it.polimi.modaclouds.qos_models.schema.MonitoringRule;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestCall extends AbstractAction {

	public static final String URL = "url";
	public static final String METHOD = "method";

	private static final String POST = "POST";
	private static final String PUT = "PUT";
	private static final String DELETE = "DELETE";
	private static final String GET = "GET";

	private final Set<String> requiredParameters = new HashSet<String>();

	public RestCall() {
		requiredParameters.add(URL);
		requiredParameters.add(METHOD);
	}

	@Override
	protected Set<String> getMyRequiredPars() {
		return requiredParameters;
	}

	@Override
	protected Collection<? extends Problem> validate(MonitoringRule rule,
			List<MonitoringRule> otherRules) {
		Set<Problem> problems = new HashSet<Problem>();
		Map<String, String> parameters = getParameters();
		String method = parameters.get(METHOD).toUpperCase();
		if (!(method.equals(DELETE) || method.equals(GET)
				|| method.equals(POST) || method.equals(PUT))) {
			problems.add(new Problem(rule.getId(),
					EnumErrorType.INVALID_ACTION, METHOD,
					"Method parameter must be one of DELETE, GET, POST or PUT"));
		}
		if (!new UrlValidator(UrlValidator.ALLOW_LOCAL_URLS).isValid(parameters
				.get(URL))) {
			problems.add(new Problem(rule.getId(),
					EnumErrorType.INVALID_ACTION, URL,
					"The specified URL is not a valid URL: "
							+ parameters.get(URL)));
		}
		return problems;
	}

	@Override
	public void execute(String resourceId, String value, String timestamp) {
		getLogger().info("Action requested. Input data: {}, {}, {}",
				resourceId, value, timestamp);
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			HttpRequestBase request;
			String url = getParameters().get(URL);
			String method = getParameters().get(METHOD).toUpperCase();
			switch (method) {
			case POST:
				request = new HttpPost(url);
				break;
			case PUT:
				request = new HttpPut(url);
				break;
			case DELETE:
				request = new HttpDelete(url);
				break;
			case GET:
				request = new HttpGet(url);
				break;

			default:
				getLogger().error("Unknown method {}", method);
				return;
			}

			CloseableHttpResponse response = client.execute(request);
			try {
				getLogger().info("Rest call executed. Response: {}",
						response.getEntity());
			} finally {
				response.close();
			}
		} catch (Exception e) {
			getLogger().error("Error executing rest call", e);
		}

	}
}
