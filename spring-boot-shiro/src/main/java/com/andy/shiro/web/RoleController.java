package com.andy.shiro.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @author Leone
 * @since 2018-05-27 21:31
 **/
@Slf4j
@Controller
public class RoleController {

    /**
     * 添加用户
     */
    @RequestMapping("/admin/add")//符合admin:view和admin:add权限要求
    @RequiresRoles(value={"admin","manager"},logical=Logical.AND)
    public String userInfoAdd(){
        return "userAdd";
    }

    /**
     * 查询用户
     */
    @RequestMapping("/admin/list")
    @RequiresRoles(value={"leader","admin","manager"},logical=Logical.OR)
    public String user(){
        return "user";
    }

    /**
     * 删除用户
     */
    @RequestMapping("/admin/delete")
    @RequiresRoles(value={"admin"})
    public String userDel(){
        return "userDel";
    }

    /**
     * 修改用户
     */
    @RequestMapping("/admin/update")
    @RequiresRoles(value={"admin","manager"},logical=Logical.OR)
    public String userUpdate(){
        return "userUpdate";
    }
    
}
