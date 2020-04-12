package org.wxz.confsysuser.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author xingze Wang
 * @create 2020/4/12 23:33
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * key前缀
     */
    private static final String SHIRO_SESSIONID_KEY_PRIFIX="CONF_SESSIONID_PRIFIX";

    /**
     * 过期时间
     */
    private static final Integer SESSION_EXPIRE_TIME=60*60;

    /**
     * 创建session
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        //生成
        Serializable sessionId=SHIRO_SESSIONID_KEY_PRIFIX+ UUID.randomUUID().toString();
        //给sessionid 重新赋值
        assignSessionId(session,sessionId);
        redisTemplate.opsForValue().set(sessionId,session);
        redisTemplate.expire(sessionId,SESSION_EXPIRE_TIME, TimeUnit.SECONDS);
        System.out.println("seesionId:"+sessionId);
        return sessionId;
    }

    /**
     * 从redis 获取session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session=null;
        try {
            session=(Session) redisTemplate.opsForValue().get(sessionId);
        }catch (Exception e){
            return null;
        }
        if (session!=null){
            session.setTimeout(SESSION_EXPIRE_TIME*1000);
        }
        System.out.println("seesion:"+session.toString());
        return session;
    }

    /**
     * 更新session
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
         if (session!=null && session.getId()!=null){
             redisTemplate.opsForValue().set(session.getId(),session);
             session.setTimeout(SESSION_EXPIRE_TIME*1000);
             redisTemplate.expire(session.getId(), SESSION_EXPIRE_TIME, TimeUnit.SECONDS);
             System.out.println("upDateseesion:"+session);
         }
    }

    @Override
    public void delete(Session session) {
      redisTemplate.opsForValue().getOperations().delete(session.getId());
      System.out.println("delete");
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Serializable> keys=redisTemplate.keys(SHIRO_SESSIONID_KEY_PRIFIX);
        if (keys==null||keys.size()==0){
            return Collections.emptySet();
        }
        List<Session> sessionList=redisTemplate.opsForValue().multiGet(keys);
        return Collections.unmodifiableCollection(sessionList);
    }
}
