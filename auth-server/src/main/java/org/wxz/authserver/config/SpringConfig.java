package org.wxz.authserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author xingze Wang
 * @create 2020/4/23 4:58
 */
@Configuration
public class SpringConfig implements WebMvcConfigurer {

    /**
     * 虚拟路径配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:/D:/img/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
