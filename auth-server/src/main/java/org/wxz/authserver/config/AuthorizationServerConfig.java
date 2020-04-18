package org.wxz.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.wxz.authserver.service.impl.UserDetailServiceImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 授权服务器配置
 * @ EnableAuthorizationServer 启用授权服务
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //对称密钥，要和资源服务相同
    private static final String SIGNING_KEY="key";

    @Resource
    private DataSource dataSource;

    //2.3.4
    @Autowired
    private AuthenticationManager authenticationManager;    // 认证管理器,密码模式

   /* @Autowired
    private ClientDetailsService detailsService;*/

    //2.3.2
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;  // redis连接工厂

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 2.1 令牌存储
     * @return redis令牌存储对象
     */
    public TokenStore tokenStore() {
        //return new InMemoryTokenStore(); //基于内存
        return new JwtTokenStore(jwtAccessTokenConverter());  //jwt
    }


    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter  converter=new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称密钥，资源服务使用该密钥解密
        return converter;
    }


    public ClientDetailsService clientDetailsService(){
        ClientDetailsService clientDetailsService=new JdbcClientDetailsService(dataSource);
        ((JdbcClientDetailsService)clientDetailsService).setPasswordEncoder(passwordEncoder);
        return clientDetailsService;
    }

    /**
     * 1.1 客户端详情服务 存储到数据库
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
          clients.withClientDetails(clientDetailsService());
        /*clients.inMemory()
                .withClient("c1")  //客户端id
                .secret(new BCryptPasswordEncoder().encode("secret")) //客户端密钥
                .resourceIds("res1")//可以访问的资源列表
                .authorizedGrantTypes("authorization_code", "implicit","password","client_credentials")
                .scopes("all")// 授权范围
                .autoApprove(false)// 跳转到授权页面
                .redirectUris("http://www.baidu.com"); //回调url*/

    }


    /**
     * 2.2
     * 令牌管理服务
     * @return
     */
    @Bean
    public AuthorizationServerTokenServices tokenServices(){
        DefaultTokenServices services=new DefaultTokenServices();
        services.setClientDetailsService(clientDetailsService());//客户端详情服务
        services.setSupportRefreshToken(true);//是否产生刷新令牌
        services.setTokenStore(tokenStore());//存储策略
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain=new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        services.setTokenEnhancer(tokenEnhancerChain);
        services.setAccessTokenValiditySeconds(7200);//令牌默认有效两小时
        services.setRefreshTokenValiditySeconds(259200);//刷新令牌默认有效3天
        return services;
    }

    /**
     * 2.3.1配置授权码模式
     * @return
     */
   /* @Bean
    public AuthorizationCodeServices accessTokenConverter(){
        return new InMemoryAuthorizationCodeServices();  //暂时内存方式
    }*/

   @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);  //存在数据库
    }

    //2.3.5 令牌服务端点
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer){
        endpointsConfigurer
                .authenticationManager(authenticationManager)//认证管理其
                .authorizationCodeServices(authorizationCodeServices)//授权码服务
                .tokenServices(tokenServices())//令牌管理服务
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    //2.4 安全服务约束
    @Override
    public void configure(AuthorizationServerSecurityConfigurer serverSecurity){
        serverSecurity
                 .tokenKeyAccess("permitAll") // auth//token_key 公开
                 .checkTokenAccess("permitAll")  // auth//check_token公开
                .allowFormAuthenticationForClients()//允许表单认证申请令牌
                ;
    }


}
