package org.wxz.confserver.auth.filter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.wxz.confsysdomain.nconfsysuser.UserDto;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author xingze Wang
 * @create 2020/4/17 2:39
 */
@Component
public class AuthorizationTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token=httpServletRequest.getHeader("json-token");
        if (token!=null){
           JSONObject jsonObject= JSON.parseObject(token);
           //拿身份信息
            String principal=jsonObject.getString("principal");
            //把用户信息保存
            UserDto userDto=new UserDto();
            userDto.setUserName(principal);
            //权限
            //TODO 用户信息放进userDto 在controller强转出来
            JSONArray authoritiesArray=jsonObject.getJSONArray("authorities");
            String[] authorities=authoritiesArray.toArray(new String[authoritiesArray.size()]);
            //填充信息
            UsernamePasswordAuthenticationToken authenticationToken=
                    new UsernamePasswordAuthenticationToken(userDto,null, AuthorityUtils.createAuthorityList(authorities));
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            //将authenticationToken填充到上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
