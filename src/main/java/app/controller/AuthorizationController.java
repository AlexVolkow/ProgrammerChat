package app.controller;

import app.dbService.model.User;
import app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;

/**
 * Created by alexa on 27.01.2017.
 */
@Controller
public class AuthorizationController {
    public static final int ONLINE_USER_LIMIT = 30;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showSignInPage(Model model){
        model.addAttribute("online",accountService.getOnlineUser(ONLINE_USER_LIMIT));
        return "signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage(Model model){
        model.addAttribute(new User());
        return "signup";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "signup";
        }
        accountService.addNewUser(user);
        return "redirect:/home/"+user.getLogin();
    }
}
