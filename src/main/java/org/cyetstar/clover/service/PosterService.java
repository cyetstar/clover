package org.cyetstar.clover.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.ImmutableMap;

@Service
@Transactional
public class PosterService {

	public static final String ORIGIN_PATH = "static/poster/origin/";

	public static final String SMALL_PATH = "static/poster/small/";

	public static final String SUFFIX = ".jpg";

	public static final Map<String, Integer> SMALL_SIZE = ImmutableMap.of("width", 120, "height", 160);

	public String upload(Long movieId, MultipartFile file, String rootPath) throws IOException {
		String posterFilename = genPosterFilename(movieId);
		file.transferTo(new File(rootPath + File.separator + getOriginPoster(posterFilename)));
		return posterFilename;
	}

	public String crop(Long movieId, double cropWidth, double cropHeight, double cropX, double cropY, String rootPath)
			throws IOException {
		String posterFilename = genPosterFilename(movieId);
		File originFile = new File(rootPath + File.separator + getOriginPoster(posterFilename));
		BufferedImage image = ImageIO.read(originFile);
		double ratio = image.getWidth() / 300;
		cropX = cropX * ratio;
		cropY = cropY * ratio;
		cropWidth = cropWidth * ratio;
		cropHeight = cropHeight * ratio;
		Thumbnails.of(originFile).sourceRegion((int) cropX, (int) cropY, (int) cropWidth, (int) cropHeight)
				.size(SMALL_SIZE.get("width"), SMALL_SIZE.get("height"))
				.toFile(rootPath + File.separator + getSmallPoster(posterFilename));
		return posterFilename;
	}

	private String genPosterFilename(Long movieId) {
		return String.valueOf(movieId) + SUFFIX;
	}

	public static String getOriginPoster(String posterFilename) {
		return ORIGIN_PATH + posterFilename;
	}

	public static String getSmallPoster(String posterFilename) {
		return SMALL_PATH + posterFilename;
	}

}
