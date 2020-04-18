package org.wxz.userserver.auth.config;

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
@EnableResourceServer
public class AuthResourceServerConfig extends ResourceServerConfigurerAdapter {


    //对称密钥，要和授权服务相同
    private static final String SIGNING_KEY="key";

    //资源名称，要和授权系统中相同
    private static final String RESOURCE_ID="res1";

    @Autowired
    private TokenStore tokenStore;


    //private static final String TOKEN_ENDPOINT_URL="http://localhost:8085/oauth/check_token";

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
                .antMatchers("/**").access("#oauth2.hasScope('all')")//校验和客户端申请令牌时的scope是否一样，不一样不通过
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//不记录session
    }

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
