package restserver.restcontroller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restclient.domain.Role;
import restclient.domain.User;
import restclient.service.RoleService;
import restclient.service.UserService;


@RestController
@RequestMapping(value = {
        "/restapi/**"
})
public class RestApi {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    public RestApi(UserService userService) {

        this.userService = userService;

    }

    @RequestMapping(value = "/allusers", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getAllUsersList() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getuser/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<User> getUsersById(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUsersById(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addupdateuser", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<Void> addUpdateUsers(@RequestBody String strUser) {
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(strUser, User.class);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/allroles", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRolesList() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getrole/{rolename}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Role> getRoleById(@PathVariable("rolename") String roleName) {
        return new ResponseEntity<>(roleService.getSingleRoleByName(roleName), HttpStatus.OK);
    }

    @RequestMapping(value = "/allregulars", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<Object> getAllRegularsList() {
        return new ResponseEntity<>(userService.selectUsersByRole(), HttpStatus.OK);
    }

}
