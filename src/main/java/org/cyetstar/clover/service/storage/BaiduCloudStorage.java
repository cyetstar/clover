package org.cyetstar.clover.service.storage;

import java.io.IOException;
import java.io.InputStream;

import org.cyetstar.clover.rest.baidu.BcsRequestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class BaiduCloudStorage implements CloudStorage {

	private String rootDir;

	private String policy;

	private BcsRequestClient requestClient;

	@Override
	public void setObjectPolicy(String policy) {
		this.policy = policy;
	}

	public void setRequestClient(BcsRequestClient requestClient) {
		this.requestClient = requestClient;
	}

	@Override
	public String getAccessPath() {
		return requestClient.getBaseUrl() + rootDir;
	}

	@Override
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	public InputStream readData(String fullname) throws IOException {
		return requestClient.getObject(rootDir, fullname);
	}

	@Override
	public void writeData(InputStream is, String fullname, MediaType type, Long size) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(type);
		headers.setContentLength(size);
		headers.add("x-bs-acl", policy);
		requestClient.putObject(is, rootDir, fullname, headers);
	}

}
