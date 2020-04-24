package org.wxz.confserver.auth.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * 安全配置
 * @ EnableWebSecurity 启用web安全配置
 * @ EnableGlobalMethodSecurity 启用全局方法安全注解，就可以在方法上使用注解来对请求进行过滤
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * http安全配置
     * @param http http安全对象
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("r/r1").hasAnyAuthority("p1")
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/resource/**").permitAll()
                .antMatchers("/resource").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/teacher").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
        ;
    }

}
