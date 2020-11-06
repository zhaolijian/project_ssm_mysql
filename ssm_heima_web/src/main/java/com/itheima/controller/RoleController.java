package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mv.addObject("roleList", roleList);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
//    根据roleId查询role，并查询出可以添加的权限信息
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String roleId) throws Exception {
        ModelAndView mv = new ModelAndView();
//        根据roleId查询role
        Role role = roleService.findById(roleId);
//        根据roleId查询可以添加的权限
        List<Permission> permissions = roleService.findOtherPermissions(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId, @RequestParam(name = "ids") String[] permissionIds) throws Exception{
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String roleId) throws Exception {
        Role role = roleService.findById(roleId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/deleteRole.do")
    public String deleteRole(@RequestParam(name="id") String roleId) throws Exception{
        roleService.deleteRoleById(roleId);
        return "redirect:findAll.do";
    }
}
