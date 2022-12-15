package com.relaxed.boot.web.config;

import com.relaxed.boot.framework.config.RelaxedConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Yakir
 * @Topic ResourcesConfig
 * @Description
 * @date 2022/11/27 16:32
 * @Version 1.0
 */
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        /** 本地文件上传路径 */
        //指定路径 为/profile 则访问此路径下面的路径 会被资源处理器提取出来 ,拼接在默认的profile本地路径后面
        registry.addResourceHandler( "/profile/**")
                .addResourceLocations("file:" + RelaxedConfig.getProfile() + "/profile/");

    }

}
