package org.cyetstar.clover.rest;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import com.google.common.collect.Maps;

public class CustomHeaderHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	private Map<String, String> headerNameValue = Maps.newHashMap();

	public CustomHeaderHttpRequestInterceptor(Map<String, String> headerNameValue) {
		this.headerNameValue = headerNameValue;
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		HttpHeaders headers = request.getHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		if (headerNameValue != null) {
			for (String headerName : headerNameValue.keySet()) {
				headers.set(headerName, headerNameValue.get(headerName));
			}
		}
		return execution.execute(requestWrapper, body);
	}
}
