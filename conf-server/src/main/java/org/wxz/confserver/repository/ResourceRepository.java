package org.wxz.confserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.wxz.confsysdomain.nconfsysconf.Resource;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/29 2:06
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource,String> {

    List<Resource> findAllByConfIdAndType(String confId,int type);

    Resource findByOriginName(String originName);

    @Query("select resource from Resource resource  where resource.originName like %?1%")
    List<Resource> findAllByOriginNameLike(String key);

}
