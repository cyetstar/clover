package org.cyetstar.clover.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.cyetstar.clover.rest.baidu.BcsRequestClient;
import org.h2.util.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles("development")
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class BcsRequestClientTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	BcsRequestClient client;

	// @Test
	public void putObject() throws IOException {
		File file = new File("D:/1.jpg");
		InputStream is = new FileInputStream(file);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		headers.setContentLength(file.length());
		headers.add("x-bs-acl", "public-read");
		client.putObject(is, "picasso", "/poster/origin/1.jpg", headers);
	}

	// @Test
	public void deleteObject() {
		client.deleteObject("picasso", "/poster/origin/2.jpg");
	}

	// @Test
	public void listOjbect() {
		System.out.println(client.listObject("picasso", "/"));
	}

	@Test
	public void getOjbect() throws IOException {
		InputStream is = client.getObject("picasso", "/poster/origin/3.jpg");
		File file = new File("D:/777.jpg");
		FileOutputStream os = new FileOutputStream(file);
		IOUtils.copy(is, os);
		IOUtils.closeSilently(is);
		IOUtils.closeSilently(os);
	}
}
