package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by alexa on 26.01.2017.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/{login}",method = RequestMethod.GET)
    public String showHomePage(@PathVariable(value = "login") String login, Model model){
        model.addAttribute("login",login);
        return "home";
    }
}
