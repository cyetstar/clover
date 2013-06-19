package org.cyetstar.clover.rest.baidu;

import java.io.IOException;
import java.io.InputStream;

import org.cyetstar.utils.Cryptos;
import org.cyetstar.utils.Encodes;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class BcsRequestClient {

	private final static String BASE_URL = "http://bcs.duapp.com/";

	private final static String DELIMITER = ":";

	private final static String FLAG = "MBO";

	private String accessKey;

	private String secretKey;

	private RestTemplate restTemplate = new RestTemplate();

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getBaseUrl() {
		return BASE_URL;
	}

	public String listObject(String bucketName, String objectName) {
		String sign = generateSign("GET", bucketName, objectName);
		return restTemplate.getForObject(BASE_URL + "{bucketName}{objectName}?sign={sign}", String.class, bucketName,
				objectName, sign);
	}

	public InputStream getObject(String bucketName, String objectName) throws IOException {
		String sign = generateSign("GET", bucketName, objectName);
		Resource resource = restTemplate.getForObject(BASE_URL + "{bucketName}{objectName}?sign={sign}", Resource.class,
				bucketName, objectName, sign);
		return resource.getInputStream();
	}

	public void putObject(InputStream is, String bucketName, String objectName, HttpHeaders headers) {
		if (!objectName.startsWith("/")) {
			throw new IllegalArgumentException("objectName must start with '/'");
		}
		String sign = generateSign("PUT", bucketName, objectName);
		HttpEntity<Object> request = new HttpEntity<Object>(new InputStreamResource(is), headers);
		restTemplate.put(BASE_URL + "{bucketName}{objectName}?sign={sign}", request, bucketName, objectName, sign);
	}

	public void deleteObject(String bucketName, String objectName) {
		String sign = generateSign("DELETE", bucketName, objectName);
		restTemplate.delete(BASE_URL + "{bucketName}{objectName}?sign={sign}", bucketName, objectName, sign);
	}

	private String generateSign(String method, String bucket, String object) {
		StringBuffer sign = new StringBuffer();
		sign.append(FLAG).append(DELIMITER);
		sign.append(accessKey).append(DELIMITER);
		sign.append(generateSignture(method, bucket, object));
		return sign.toString();
	}

	private String generateSignture(String method, String bucket, String object) {
		StringBuffer content = new StringBuffer();
		content.append(FLAG).append("\n");
		content.append("Method=").append(method).append("\n");
		content.append("Bucket=").append(bucket).append("\n");
		content.append("Object=").append(object).append("\n");
		return Encodes.encodeBase64(Cryptos.hmacSha1(content.toString().getBytes(), secretKey.getBytes()));
	}
}
