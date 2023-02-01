package com.relaxed.boot.common.system.utils.file;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.relaxed.boot.common.system.exception.FileNameLengthLimitExceededException;
import com.relaxed.boot.common.system.exception.FileSizeLimitExceededException;
import com.relaxed.common.model.result.SysResultCode;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Yakir
 * @Topic FileUtils
 * @Description
 * @date 2022/11/27 12:14
 * @Version 1.0
 */
@UtilityClass
public class FileUtils {

	/**
	 * 默认大小 50M
	 */
	public static final long DEFAULT_MAX_SIZE = 50 * 1024 * 1024;

	/**
	 * 默认的文件名最大长度 100
	 */
	public static final int DEFAULT_FILE_NAME_LENGTH = 100;

	public static final String[] DEFAULT_ALLOWED_EXTENSION = {
			// 图片
			"bmp", "gif", "jpg", "jpeg", "png",
			// word excel powerpoint
			"doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
			// 压缩文件
			"rar", "zip", "gz", "bz2",
			// 视频格式
			"mp4", "avi", "rmvb",
			// pdf
			"pdf" };

	/**
	 * 上传文件
	 * @author yakir
	 * @date 2022/11/27 15:21
	 * @param basePath 基础存储路径 eg： /mnt/profile
	 * @param relativePath 相对文件存储路径 upload
	 * @param file
	 * @param fileConfig
	 * @return FileMeta 上传文件相关信息
	 */
	@SneakyThrows
	public FileMeta upload(String basePath, String relativePath, MultipartFile file, FileConfig fileConfig) {
		String originalFilename = file.getOriginalFilename();
		int fileNameLength = originalFilename.length();
		if (fileNameLength > FileUtils.DEFAULT_FILE_NAME_LENGTH) {
			throw new FileNameLengthLimitExceededException(SysResultCode.BAD_REQUEST.getCode(),
					FileUtils.DEFAULT_FILE_NAME_LENGTH);
		}

		assertAllowed(file, fileConfig);
		// 转换后 文件名称
		String fileName = extractFileName(file);
		boolean splitDate = fileConfig.isSplitDate();
		String relativeFilePath;
		if (splitDate) {
			String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern(DatePattern.PURE_DATE_PATTERN));
			relativeFilePath = relativePath + "/" + dateStr;
		}
		else {
			relativeFilePath = relativePath;
		}
		String absolutePath = basePath + "/" + relativeFilePath;
		File desc = getAbsoluteFile(absolutePath, fileName);
		file.transferTo(desc);
		String fileId = IdUtil.getSnowflakeNextId() + "";
		FileMeta fileMeta = new FileMeta().setOriginalFilename(originalFilename).setFilename(fileName).setFileId(fileId).setBasePath(basePath)
				.setFilepath(relativeFilePath);
		return fileMeta;
	}

	private String getPathFileName(String dirPath, String filename) {
		return dirPath + "/" + filename;
	}

	private File getAbsoluteFile(String dirPath, String fileName) {
		File desc = new File(dirPath + File.separator + fileName);

		if (!desc.exists()) {
			if (!desc.getParentFile().exists()) {
				desc.getParentFile().mkdirs();
			}
		}
		return desc;
	}


	/**
	 * 编码文件名
	 */
	private String extractFileName(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String extName = FileNameUtil.getSuffix(originalFilename);
		String mainName = FileNameUtil.getPrefix(originalFilename);
		String filename =mainName+"_"+IdUtil.nanoId()+"."+extName;
		return filename;
	}

	/**
	 * 文件大小校验
	 * @param file 上传的文件
	 * @return
	 * @throws FileSizeLimitExceededException 如果超出最大大小
	 */
	private   void assertAllowed(MultipartFile file, FileConfig fileConfig)
			throws FileSizeLimitExceededException {
		long size = file.getSize();

		String fileName = file.getOriginalFilename();

	}

	public static File download(String basePath,String relativePath) {
		return new File(basePath, relativePath);
	}

	public static boolean delete(String basePath,String relativePath){
		return FileUtil.del(basePath+relativePath);
	}
}
