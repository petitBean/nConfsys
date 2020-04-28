package org.wxz.authserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wxz.authserver.service.impl.RoleServiceImpl;
import org.wxz.confsysdomain.nconfsysuser.Role;

import java.util.List;

/**
 * @Author xingze Wang
 * @create 2020/4/26 23:47
 */
@RestController
@RequestMapping(value = "/role_api")
public class RoleApi {

    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/find_list_by_rolename")
    public List<Role> findListByRoleNameIn(@RequestBody List<String> roleNameList){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return roleService.findListByRoleNameIn(roleNameList);
    }

    @GetMapping("/test")
    public String test(){
        return "heeeeeee";
    }

}
