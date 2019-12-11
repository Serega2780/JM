package restclient.controllers.regularcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserListController {
    @GetMapping("/user/list")
    public ModelAndView userHome() {
        ModelAndView modelView = new ModelAndView("/user-access.html");
        return modelView;

    }
}
