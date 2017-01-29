package app.controller;

import app.dbService.model.User;
import app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

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
        if (accountService.isEnter(RequestContextHolder.currentRequestAttributes().getSessionId())){
            return "redirect:/home";
        }
        model.addAttribute("online",accountService.getOnlineUser(ONLINE_USER_LIMIT));
        return "signin";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignUpPage(Model model){
        model.addAttribute(new User());
        return "signup";
    }
    @RequestMapping(value = "/signup/success", method = RequestMethod.GET)
    public String registrationSuccessful(Model model){
        return "complete";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addNewUser(@Valid User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "signup";
        }
        if (accountService.getUserByLogin(user.getLogin())!=null){
            bindingResult.rejectValue("login","err_qna_not_blank","Login is already taken");
            return "signup";
        }
        if (accountService.getUserByEmail(user.getEmail())!=null){
            bindingResult.rejectValue("email","err_qna_not_blank","Email is already taken");
            return "signup";
        }
        accountService.addNewUser(user);
        return "redirect:/signup/success";
    }
}
