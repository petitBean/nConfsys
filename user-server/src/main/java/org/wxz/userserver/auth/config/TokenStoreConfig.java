package org.wxz.userserver.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author xingze Wang
 * @create 2020/4/16 22:27
 */
@Configuration
public class TokenStoreConfig {

    //对称密钥，要和授权服务相同
    private static final String SIGNING_KEY="key";

    /**
     * 2.1 令牌存储
     * @return redis令牌存储对象
     */
    @Bean
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

}
