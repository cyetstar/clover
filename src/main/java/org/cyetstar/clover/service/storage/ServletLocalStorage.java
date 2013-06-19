package org.cyetstar.clover.service.storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.ServletContextAware;

public class ServletLocalStorage implements Storage, ServletContextAware {

	private String rootDir;

	private String accessPath;

	private String realPath;

	@Override
	public String getAccessPath() {
		return accessPath;
	}

	@Override
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	public InputStream readData(String fullname) throws IOException {
		return new FileInputStream(realPath + rootDir + fullname);
	}

	@Override
	public void writeData(InputStream is, String fullname, MediaType type, Long size) throws IOException {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(new FileOutputStream(realPath + rootDir + fullname));
			byte[] buffer = new byte[1024];
			IOUtils.copyLarge(bis, bos, buffer);
		} finally {
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(bos);
		}

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		realPath = servletContext.getRealPath("/");
		accessPath = servletContext.getContextPath() + rootDir;
	}

}
