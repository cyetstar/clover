package org.cyetstar.clover.service.storage;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.MediaType;

/**
 * @author cyetstar
 * 
 * @see BaiduCloudStorage
 * @see ServletLocalStorage
 * 
 */
public interface Storage {

	public String getAccessPath();

	public void setRootDir(String rootDir);

	public InputStream readData(String fullname) throws IOException;

	public void writeData(InputStream is, String fullname, MediaType type, Long size) throws IOException;

}
