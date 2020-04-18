package org.wxz.apigateway.config.AuthConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author xingze Wang
 * @create 2020/4/16 18:29
 */
@Configuration
public class AuthResourceServerConfig {

    //资源名称，要和授权系统中相同
    private static final String RESOURCE_ID="res1";

    //对称密钥，要和授权服务相同
    private static final String SIGNING_KEY="key";

    /**
     * AntuServer 资源服务配置
     */
    @Configuration
    @EnableResourceServer
    public class AuthServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource){
            resource
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    //.tokenServices(tokenServices())//验证令牌的服务
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api-auth-service/**").permitAll() ;//对访问认证授权中心的所有访问放行
        }

    }

       /**
     * notice noticeServer 资源服务配置
     */
    @Configuration
    @EnableResourceServer
    public class NoticeServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource){
            resource
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    //.tokenServices(tokenServices())//验证令牌的服务
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api-notice-service/**").access("#oauth2.hasScope('all')")//校验和客户端申请令牌时的scope是否一样，不一样不通过
                    .antMatchers("/api-notice-service/notice-service/teacher")
                    .permitAll();

        }

    }


    /**
     * notice noticeServer 资源服务配置
     */
    @Configuration
    @EnableResourceServer
    public class UserServerServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource){
            resource
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    //.tokenServices(tokenServices())//验证令牌的服务
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api-user-service/**").access("#oauth2.hasScope('all')")//校验和客户端申请令牌时的scope是否一样，不一样不通过
                    .antMatchers("/api-user-service/user-service/teacher").permitAll();

        }

    }

    /**
     * notice noticeServer 资源服务配置
     */
    @Configuration
    @EnableResourceServer
    public class PaperServerServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource){
            resource
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    //.tokenServices(tokenServices())//验证令牌的服务
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api-paper-service/**").access("#oauth2.hasScope('all')")//校验和客户端申请令牌时的scope是否一样，不一样不通过
                    .antMatchers("/api-paper-service/user-service/teacher").permitAll();

        }

    }


    /**
     * notice noticeServer 资源服务配置
     */
    @Configuration
    @EnableResourceServer
    public class ConfServerServerConfig extends ResourceServerConfigurerAdapter{

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resource){
            resource
                    .resourceId(RESOURCE_ID)
                    .tokenStore(tokenStore)
                    //.tokenServices(tokenServices())//验证令牌的服务
                    .stateless(true);
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/api-conf-service/**").access("#oauth2.hasScope('all')")//校验和客户端申请令牌时的scope是否一样，不一样不通过
                    .antMatchers("/api-conf-service/user-service/teacher").permitAll();

        }

    }





    // private static final String TOKEN_ENDPOINT_URL="http://localhost:8085/oauth/check_token";

    /**
     * 令牌解析服务
     * @return
     */
    /*@Bean
    public ResourceServerTokenServices tokenServices(){
        RemoteTokenServices tokenServices=new RemoteTokenServices();//远程解析
        tokenServices.setCheckTokenEndpointUrl(TOKEN_ENDPOINT_URL);//授权服务的 check_token
        tokenServices.setClientId("c1");
        tokenServices.setClientSecret("secret");
        return tokenServices;
    }*/

}
