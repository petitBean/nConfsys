package org.wxz.apigateway.config.AuthConfig;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.wxz.apigateway.filter.AuthFilter;

/**
 * @Author xingze Wang
 * @create 2020/4/17 2:21
 */
@Configuration
public class ZuulConfig {

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

    @Bean
    public FilterRegistrationBean corsFilter(){
        final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config=new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(18000L);
        source.registerCorsConfiguration("/**",config);
        org.springframework.web.filter.CorsFilter corsFilter=new CorsFilter(source);
        FilterRegistrationBean bean=new FilterRegistrationBean(corsFilter);
        return bean;
    }

}
