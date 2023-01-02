package com.relaxed.boot.common.system.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * @author Yakir
 * @Topic ByteMultipartFile
 * @Description
 * @date 2022/12/15 15:08
 * @Version 1.0
 */
@Slf4j
public class ByteArrayMultipartFile implements MultipartFile {

	private byte[] content;

	private String filename;

	@Nullable
	private String contentType;

	public ByteArrayMultipartFile(byte[] content, String filename) {
		this.content = content;
		this.filename = filename;
	}

	public ByteArrayMultipartFile(byte[] content, String filename, String contentType) {
		this.content = content;
		this.filename = filename;
		this.contentType = contentType;
	}

	@Override
	public String getName() {
		return filename;
	}

	@Override
	public String getOriginalFilename() {
		return filename;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return content == null || content.length == 0;
	}

	@Override
	public long getSize() {
		return content.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return content;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(content);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(dest);
			stream.write(content);
		}
		catch (IOException e) {
			log.error("transferTo错误", e);
		}
		finally {
			assert stream != null;
			stream.close();
		}
	}

}
