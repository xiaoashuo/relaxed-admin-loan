package com.relaxed.boot.loan.config.download;

import com.relaxed.starter.download.domain.DownloadModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Yakir
 * @Topic StreamDownloadModel
 * @Description
 * @date 2022/2/25 14:38
 * @Version 1.0
 */
@Schema(title = "下载文件")
@Data
public class StreamDownloadModel extends DownloadModel {

	/**
	 * bsae64 后的字节数组
	 */
	@Schema(title = "base64编码文件字符串")
	private byte[] content;

}
