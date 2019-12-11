package springhibernatemysql.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.PopulateCountries;


@Controller
@RestController
@RequestMapping(value = {
        "/new-user/**"
})
public class SignupController {

    private PopulateCountries populateCountries;
    private RoleService roleService;
    private UserService userService;

    public SignupController(PopulateCountries populateCountries, RoleService roleService,
                            UserService userService) {
        this.populateCountries = populateCountries;
        this.roleService = roleService;
        this.userService = userService;
    }

    //REST

    @RequestMapping(value = "/new-user/countries")
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @PostMapping("/new-user/insert")
    @ResponseBody
    public String addUpdateUser(@RequestBody User user) {
        userService.createUser(user);
        return "redirect:/";
    }
    @GetMapping("/new-user")
    public ModelAndView showNewUserForm() {
        ModelAndView modelView = new ModelAndView("/new-user.html");
        return modelView;
    }

}
