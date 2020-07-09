package com.liang.controller;

import com.liang.domain.Role;
import com.liang.domain.UserInfo;
import com.liang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * @author liang
 * @create 2020/2/27 14:49
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<UserInfo>  list = userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }

    //保存用户信息
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo){
        String uuid = UUID.randomUUID().toString().replace("-","");
        userInfo.setId(uuid);
        userService.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    //添加用户角色
    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv =new ModelAndView();
        //根据用户id查询用户信息
        UserInfo userInfo = userService.findById(userId);
        //根据用户id查询用户可以添加的角色
        List<Role> roleList=userService.findOtherRole(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true)String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findAll.do";
    }
}
