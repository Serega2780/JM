package org.spring.mvc.controller;

import org.spring.mvc.domain.User;
import org.spring.mvc.service.RoleService;
import org.spring.mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/")
public class SpringMvcController {

    private UserService userService;
    private RoleService roleService;

    @Resource(name = "userService")
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @Resource(name = "roleService")
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(value = {"/users", "admin/list", "/list"}, method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("listUser", userService.selectAllUsers());
        return "user-list";
    }

    @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("countries", userService.selectCountries());
        model.addAttribute("roles", roleService.selectAllRoles());
        return "user-form";
    }

    @RequestMapping(value = "/admin/insert", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user, @RequestParam("role") Integer[] roleIds) {
        for (Integer id : roleIds) {
            user.setAuthority(roleService.selectRoleById(id));
        }
        userService.createUser(user);
        return new ModelAndView("redirect:/users");

    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userService.selectUser(id);
        model.addAttribute("user", user);
        model.addAttribute("countries", userService.selectCountries());
        model.addAttribute("roles", roleService.selectAllRoles());
        return "user-form";
    }

    @RequestMapping(value = "/admin/update", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user, @RequestParam("role") Integer[] roleIds) {
        for (Integer id : roleIds) {
            user.setAuthority(roleService.selectRoleById(id));
        }
        userService.updateUser(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");

    }
}
