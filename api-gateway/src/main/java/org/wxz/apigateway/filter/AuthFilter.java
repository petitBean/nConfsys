package org.wxz.apigateway.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xingze Wang
 * @create 2020/4/17 1:34
 */
public class AuthFilter extends ZuulFilter {

    /**
     * 请求之前进行拦截
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 最优先
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 必须执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //请求上下文用于转发，由网关提供
        RequestContext ctx=RequestContext.getCurrentContext();
        //获取当前用户身份信息
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)){
            //如果不是jwt令牌
            return null;
        }
        OAuth2Authentication oAuth2Authentication=(OAuth2Authentication)authentication;
        Authentication userAuthentication=oAuth2Authentication.getUserAuthentication();
        //取出身份信息
        String principal=userAuthentication.getName();
        //获取当前用户的权限信息
        List<String> authorities=new ArrayList<>();
        userAuthentication.getAuthorities().stream().forEach(e->authorities.add(((GrantedAuthority)e).getAuthority()));
        //拿到请求的数据,避免用户会用到
        OAuth2Request request=oAuth2Authentication.getOAuth2Request();
        //参数列表
        Map<String,String> requestParameters=request.getRequestParameters();
        Map<String,Object> jsonToken=new HashMap<>(requestParameters);
        if (userAuthentication!=null){
            jsonToken.put("principal",principal);
            jsonToken.put("authorities",authorities);
        }
        //把信息转成json格式，放入http的header中 TODO 加密
        ctx.addZuulRequestHeader("json-token", JSON.toJSONString(jsonToken));
        return null;
    }
}
