package org.cyetstar.clover.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

import org.cyetstar.clover.service.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;

@Service
@Transactional
public class PosterService {

	private static final String ORIGIN_PATH = "/poster/origin";

	private static final String SMALL_PATH = "/poster/small";

	private static final String SUFFIX = ".jpg";

	@Autowired
	private Storage storage;

	public static final Map<String, Integer> SMALL_SIZE = ImmutableMap.of("width", 120, "height", 160);

	public String upload(Long movieId, MultipartFile file) throws IOException {
		String poster = getPosterFilename(movieId);
		storage.writeData(file.getInputStream(), ORIGIN_PATH + "/" + poster, MediaType.IMAGE_JPEG, file.getSize());
		return poster;
	}

	public String crop(Long movieId, double cropWidth, double cropHeight, double cropX, double cropY) throws IOException {
		String poster = getPosterFilename(movieId);
		InputStream is = storage.readData(ORIGIN_PATH + "/" + poster);
		BufferedImage bufferedImage = ImageIO.read(is);
		int width = bufferedImage.getWidth();
		double ratio = (double) width / 300;
		cropX = cropX * ratio;
		cropY = cropY * ratio;
		cropWidth = cropWidth * ratio;
		cropHeight = cropHeight * ratio;
		Builder<BufferedImage> builder = Thumbnails.of(bufferedImage)
				.sourceRegion((int) cropX, (int) cropY, (int) cropWidth, (int) cropHeight)
				.size(SMALL_SIZE.get("width"), SMALL_SIZE.get("height"));
		if (width > bufferedImage.getHeight()) {
			builder.rotate(90);
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		builder.outputFormat("jpg").toOutputStream(baos);
		// FIXME buff read
		storage
				.writeData(new ByteArrayInputStream(baos.toByteArray()), SMALL_PATH + "/" + poster, MediaType.IMAGE_JPEG, 0L);
		return poster;
	}

	public void remove(Long movieId, String rootPath) {
		// String posterFilename = genPosterFilename(movieId);
		// File originFile = new File(rootPath + File.separator + getOriginPoster(posterFilename));
		// File smallFile = new File(rootPath + File.separator + getSmallPoster(posterFilename));
		// originFile.delete();
		// smallFile.delete();
	}

	public String getOriginAccessPath() {
		return storage.getAccessPath() + ORIGIN_PATH;
	}

	public String getSmallAccessPath() {
		return storage.getAccessPath() + SMALL_PATH;
	}

	private String getPosterFilename(Long movieId) {
		return String.valueOf(movieId) + SUFFIX;
	}
}
