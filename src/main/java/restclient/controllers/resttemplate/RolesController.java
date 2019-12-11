package restclient.controllers.resttemplate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import restclient.domain.Role;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RolesController {

    @RequestMapping(value = "/admin/roles", method = RequestMethod.GET)
    public ResponseEntity<Object> getRoles() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:8081/restapi/allroles";
        URI uri = new URI(baseUrl);
        List<Role> result = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role")
    public ResponseEntity<Role> getRole(@RequestParam("role") String roleName) {
        final String uri = "http://localhost:8081/restapi/getrole/{rolename}";
        Map<String, String> params = new HashMap<>();
        params.put("rolename", roleName);

        RestTemplate restTemplate = new RestTemplate();

        Role role = restTemplate.getForObject(uri, Role.class, params);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
