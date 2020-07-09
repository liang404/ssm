package com.liang.controller;

import com.liang.domain.Permission;
import com.liang.domain.Role;
import com.liang.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

/**
 * @author liang
 * @create 2020/2/29 15:31
 */
@RequestMapping("/role")
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList",roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role, HttpServletRequest request){
        String uuid = UUID.randomUUID().toString().replace("-", "");
        role.setId(uuid);
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv = new ModelAndView();
        //根据角色ID查询角色
        Role role=roleService.findById(roleId);
        //根据角色id查询角色可以添加的权限
        List<Permission> permissionList = roleService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:/role/findAll.do";
    }
}
