package springhibernatemysql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springhibernatemysql.domain.User;
import springhibernatemysql.service.UserService;
import springhibernatemysql.service.implementation.UserServiceImpl;


@Controller
@RestController
@RequestMapping(value = {
        "/user/**"
})
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {

        this.userService = userService;
    }

    //REST
    @RequestMapping(value = "/user/users")
    public ResponseEntity<Object> getRegularUsers() {
        return new ResponseEntity<>(userService.selectUsersByRole(), HttpStatus.OK);
    }

    @RequestMapping(value = "user/user")
    public ResponseEntity<Object> getRegularUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }


    @GetMapping(value = {"list"})
    public ModelAndView userHome() {
        ModelAndView modelView = new ModelAndView("/user-access.html");
        return modelView;

    }
}
