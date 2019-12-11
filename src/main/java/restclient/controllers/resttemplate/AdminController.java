package restclient.controllers.resttemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import restclient.domain.User;

import restclient.service.implementation.PopulateCountries;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class AdminController {

    private PopulateCountries populateCountries;

    public AdminController(PopulateCountries populateCountries) {
        this.populateCountries = populateCountries;
    }

    //REST
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8081/restapi/allusers";
        URI uri = new URI(baseUrl);
        List<User> result = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@RequestParam("id") int id) {

        final String uri = "http://localhost:8081/restapi/getuser/{id}";

        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);

        RestTemplate restTemplate = new RestTemplate();

        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(restTemplate.getForObject(uri, String.class, params), User.class);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@RequestParam("id") int id) {
        final String uri = "http://localhost:8081/restapi/deleteuser/{id}";
        Map<String, Integer> params = new HashMap<>();
        params.put("id", id);

        new RestTemplate().delete(uri, params);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/countries", method = RequestMethod.GET)
    public ResponseEntity<Object> getCountries() {
        return new ResponseEntity<>(populateCountries.populateCountries(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/insert", method = RequestMethod.PUT)
    @ResponseBody
    public void addUpdateUser(@RequestBody User user) throws URISyntaxException {
        final String url = "http://localhost:8081/restapi/addupdateuser";
        URI uri = new URI(url);
        RestTemplate restTemplate = new RestTemplate();
        Gson gson = new GsonBuilder().create();
        restTemplate.put(uri, gson.toJson(user));
    }

}
