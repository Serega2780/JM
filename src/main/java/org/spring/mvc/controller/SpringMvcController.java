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

    @RequestMapping(value = {"/users", "/list"}, method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("listUser", userService.selectAllUsers());
        return "user-list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addForm(Model model) {
        model.addAttribute("countries", userService.selectCountries());
        model.addAttribute("roles", roleService.selectAllRoles());
        return "user-form";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute User user) {
        userService.createUser(user);
        return new ModelAndView("redirect:/users");

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userService.selectUser(id);
        model.addAttribute("user", user);
        model.addAttribute("countries", userService.selectCountries());
        model.addAttribute("roles", roleService.selectAllRoles());
        return "user-form";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ModelAndView editUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");

    }
}
