package org.wxz.authserver.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.wxz.authserver.service.impl.UserDetailServiceImpl;

/**
 * 安全配置
 * @ EnableWebSecurity 启用web安全配置
 * @ EnableGlobalMethodSecurity 启用全局方法安全注解，就可以在方法上使用注解来对请求进行过滤
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    /**
     * 编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    /**
     * 全局用户信息
     * @param auth 认证管理
     * @throws Exception 用户认证异常信息
     */
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    /**
     * 认证管理
     * @return 认证管理对象
     * @throws Exception 认证异常信息
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
                .antMatchers("/upload").permitAll()
                .antMatchers("/img").permitAll()
                .antMatchers("/img/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                ;
    }

}
