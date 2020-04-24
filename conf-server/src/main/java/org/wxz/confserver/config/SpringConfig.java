package org.wxz.confserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.wxz.nconfsyscommon.constrants.FileConstrant;

/**
 * @Author xingze Wang
 * @create 2020/4/23 4:58
 */
@Configuration
public class SpringConfig implements WebMvcConfigurer {


    //跨越
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/*").allowedOrigins("*");
    }
    /**
     * 虚拟路径配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:/"+FileConstrant.BASE_LOCATION+"/img/");
        registry.addResourceHandler("/doc/**").addResourceLocations("file:/"+FileConstrant.BASE_LOCATION+"/doc/");
        registry.addResourceHandler("/zip/**").addResourceLocations("file:/"+FileConstrant.BASE_LOCATION+"/zip/");
        registry.addResourceHandler("/excl/**").addResourceLocations("file:/"+FileConstrant.BASE_LOCATION+"/excl/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
