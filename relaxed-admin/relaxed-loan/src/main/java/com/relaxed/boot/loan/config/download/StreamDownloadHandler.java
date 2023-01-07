package com.relaxed.boot.loan.config.download;

import cn.hutool.extra.servlet.ServletUtil;
import com.relaxed.starter.download.annotation.ResponseDownload;
import com.relaxed.starter.download.domain.DownloadModel;
import com.relaxed.starter.download.enums.DownTypeEnum;
import com.relaxed.starter.download.handler.AbstractDownloadHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

/**
 * @author Yakir
 * @Topic StreamDownloadHandler
 * @Description
 * @date 2022/2/18 14:34
 * @Version 1.0
 */
@Slf4j
@Component
public class StreamDownloadHandler extends AbstractDownloadHandler {

	@Override
	public boolean support(DownloadModel downloadModel, ResponseDownload responseDownload) {
		return DownTypeEnum.OTHER.equals(responseDownload.channel());
	}

	@SneakyThrows
	@Override
	protected void write(DownloadModel downloadModel, HttpServletResponse response, ResponseDownload responseDownload) {

		StreamDownloadModel streamDownloadModel = (StreamDownloadModel) downloadModel;
		String fileName = downloadModel.getFileName();
		byte[] content = streamDownloadModel.getContent();
		try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content)) {
			ServletUtil.write(response, byteArrayInputStream);
		}
		catch (Exception e) {
			log.error("下载文件,渠道Stream ，名称{}异常", fileName, e);
			throw e;
		}
	}

}
