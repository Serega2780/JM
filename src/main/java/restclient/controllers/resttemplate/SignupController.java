package restclient.controllers.resttemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restclient.domain.User;

import restclient.service.UserService;
import restclient.service.implementation.PopulateCountries;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = {
        "/new-user/**"
})
public class SignupController {

    private PopulateCountries populateCountries;
    private UserService userService;

    public SignupController(PopulateCountries populateCountries, UserService userService) {
        this.populateCountries = populateCountries;
        this.userService = userService;
    }

    //REST

    @RequestMapping(value = "/new-user/countries")
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @RequestMapping(value = "/new-user/insert", method = RequestMethod.POST)
    @ResponseBody
    public String addUpdateUser(@RequestBody User user) throws URISyntaxException {
        final String url = "http://localhost:8081/restapi/addupdateuser";
        URI uri = new URI(url);
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new GsonBuilder().create();
        restTemplate.put(uri, gson.toJson(user));
        return "redirect:/";
    }

}
