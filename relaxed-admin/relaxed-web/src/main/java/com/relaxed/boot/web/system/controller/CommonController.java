package com.relaxed.boot.web.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.relaxed.boot.common.system.utils.file.FileConfig;
import com.relaxed.boot.common.system.utils.file.FileMeta;
import com.relaxed.boot.common.system.utils.file.FileUtils;
import com.relaxed.boot.web.config.RelaxedConfig;
import com.relaxed.common.core.util.WebUtils;
import com.relaxed.common.model.result.R;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yakir
 * @Topic CommonController
 * @Description
 * @date 2022/11/27 12:35
 * @Version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/common")
@Tag(name = "通用服务")
public class CommonController {

    @PostMapping("/file/upload")
    public R<?> uploadFile(MultipartFile file) {
        // 上传文件路径
        FileMeta fileMeta = FileUtils.upload(RelaxedConfig.getProfile(),"profile/upload", file,
                FileConfig.create().splitDate(true));
        String fullFilePath = fileMeta.getRelativeFilePath();
        Map<String,String> data=new HashMap<>();
       String fullUrl= RelaxedConfig.getUrl()+fullFilePath;

        data.put("url",fullUrl);
        data.put("fileId",fileMeta.getFileId());
        data.put("filename",fileMeta.getFilename());
        return R.ok(data);

    }


    @PostMapping("/file/delete")
    public R<?> deleteFile(String url) {
        // 本地文件路径
        String localFilePath = RelaxedConfig.getProfile()+StrUtil.removePrefixIgnoreCase(url,RelaxedConfig.getUrl());
        FileUtil.del(localFilePath);
        return R.ok();

    }
}
