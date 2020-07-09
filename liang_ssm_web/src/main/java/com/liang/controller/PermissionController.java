package com.liang.controller;

import com.liang.domain.Permission;
import com.liang.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * @author liang
 * @create 2020/2/29 16:07
 */
@RequestMapping("/permission")
@Controller
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permission.setId(UUID.randomUUID().toString().replace("-",""));
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }
}
