package restclient.controllers.regularcontroller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import restclient.domain.User;

@Controller
public class AdminListController {

    @GetMapping("/admin/list")
    public ModelAndView home() {
        ModelAndView modelView = new ModelAndView("/user-list.html");
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        modelView.addObject("currentUser", currentUser);
        return modelView;
    }
}
