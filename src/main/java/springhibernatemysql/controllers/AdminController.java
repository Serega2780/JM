package springhibernatemysql.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.Role;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.RoleService;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.PopulateCountries;


import javax.websocket.server.PathParam;
import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RestController
@RequestMapping(value = {
        "/admin/**"
})
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private PopulateCountries populateCountries;

    public AdminController(UserService userService, RoleService roleService,
                           PopulateCountries populateCountries) {

        this.roleService = roleService;
        this.userService = userService;
        this.populateCountries = populateCountries;
    }

    @GetMapping("/admin/list")
    public ModelAndView home() {
        ModelAndView modelView = new ModelAndView("/user-list.html");
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelView.addObject("currentUser", currentUser);
        return modelView;
    }

    //REST
    @RequestMapping(value = "/admin/users")
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit")
    public ResponseEntity<Object> getUser(@RequestParam("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/admin/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/countries")
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/roles")
    public ResponseEntity<Object> getRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role")
    public ResponseEntity<Object> getRole(@RequestParam("role") String roleName) {
        return new ResponseEntity<>(roleService.getSingleRoleByName(roleName), HttpStatus.OK);
    }

    @PutMapping("/admin/insert")
    @ResponseBody
    public void addUpdateUser(@RequestBody User user) {
        userService.createUser(user);
    }

}
