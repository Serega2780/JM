package org.spring.mvc.controller;

import org.spring.mvc.domain.User;
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

    @Resource(name = "userService")
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/users", "/list", "/login"}, method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("listUser", userService.selectAllUsers());
        return "user-list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addForm() {
        return "user-form";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView addUser(@RequestParam("name") String name, @RequestParam("password") String password,
                                @RequestParam("role") String role, @RequestParam("email") String email,
                                @RequestParam("country") String country) {


        User user = new User(name, password, role, email, country);
        userService.createUser(user);
        return new ModelAndView("redirect:/users");

    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String showEditForm(@RequestParam("id") int id, Model model) {
        User user = userService.selectUser(id);
        model.addAttribute("user", user);
        return "user-form";

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView editUser(@RequestParam("id") int id, @RequestParam("name") String name,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role, @RequestParam("email") String email,
                                 @RequestParam("country") String country) {
        User user = new User(id, name, password, role, email, country);
        userService.updateUser(user);
        return new ModelAndView("redirect:/users");

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");

    }
}
