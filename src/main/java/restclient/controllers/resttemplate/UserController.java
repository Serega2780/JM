package restclient.controllers.resttemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import restclient.domain.User;
import restclient.service.UserService;
import restclient.service.implementation.UserServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController

public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {

        this.userService = userService;
    }

    //REST
    @RequestMapping(value = "/user/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getRegularUsers() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8081/restapi/allregulars";
        URI uri = new URI(baseUrl);
        List<User> result = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/user", method = RequestMethod.GET)
    public ResponseEntity<Object> getRegularUser() {

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

}
