package com.relaxed.boot.common.system.utils.file;

import lombok.Getter;

/**
 * @author Yakir
 * @Topic FileConfig
 * @Description
 * @date 2022/11/27 16:14
 * @Version 1.0
 */
@Getter
public class FileConfig {

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
	 * 允许的扩展名
	 */
	private String[] allowedExtension = DEFAULT_ALLOWED_EXTENSION;

	/**
	 * 默认的文件名最大长度 100
	 */
	private int maxFilenameLength = 100;

	/**
	 * 文件最大大小默认大小 50M
	 */
	private long maxSize = 50 * 1024 * 1024;

	/**
	 * 是否按日期切分 默认false
	 */
	private boolean splitDate = false;

	/**
	 *
	 */
	private FileNameConverter fileNameConverter;

	public static FileConfig create() {
		return new FileConfig();
	}

	public FileConfig splitDate(boolean splitDate) {
		this.splitDate = splitDate;
		return this;
	}

	public FileConfig maxSize(long maxSize) {
		this.maxSize = maxSize;
		return this;
	}

	public FileConfig maxFilenameLength(int maxFilenameLength) {
		this.maxFilenameLength = maxFilenameLength;
		return this;
	}

	public FileConfig allowedExtension(String[] allowedExtension) {
		this.allowedExtension = allowedExtension;
		return this;
	}

	public FileConfig fileNameConverter(FileNameConverter fileNameConverter) {
		this.fileNameConverter = fileNameConverter;
		return this;
	}

}
