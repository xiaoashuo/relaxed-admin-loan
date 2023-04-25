package com.relaxed.boot.common.system.utils.file;

import cn.hutool.core.io.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * @author Yakir
 * @Topic FileMultipartFile
 * @Description
 * @date 2023/1/30 13:35
 * @Version 1.0
 */

public class FileMultipartFile implements MultipartFile {

	private final String name;

	private final String originalFilename;

	private final String contentType;

	private final File file;

	public FileMultipartFile(String name, File file) {

		this(name, file.getName(), new MimetypesFileTypeMap().getContentType(file), file);
	}

	public FileMultipartFile(String name, String contentType, File file) {
		this(name, file.getName(), contentType, file);
	}

	public FileMultipartFile(String name, String originalFilename, String contentType, File file) {
		this.name = name;
		this.originalFilename = originalFilename;
		this.contentType = contentType;
		this.file = file;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getOriginalFilename() {
		return this.originalFilename;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return file.exists() && file.isFile() && file.length() == 0;
	}

	@Override
	public long getSize() {
		return file.length();
	}

	@Override
	public byte[] getBytes() throws IOException {
		return FileCopyUtils.copyToByteArray(this.file);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return Files.newInputStream(this.file.toPath());
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		FileCopyUtils.copy(this.getInputStream(), Files.newOutputStream(dest.toPath()));
	}

}
