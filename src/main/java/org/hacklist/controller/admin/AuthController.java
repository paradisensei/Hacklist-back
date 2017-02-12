package org.hacklist.controller.admin;

import org.hacklist.service.AuthService;
import org.hacklist.util.forms.AuthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

import static org.hacklist.util.transformers.AuthFormTransformer.toAdmin;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/admin")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/sign_up")
    public String getSignUpPage(Model model) {
        model.addAttribute("authForm", new AuthForm());
        return "auth/sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("authForm") @Valid AuthForm authForm,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "auth/sign_up";
        }

        authService.add(toAdmin(authForm));
        return "redirect:/admin/hacks";
    }

    @RequestMapping("/sign_in")
    public String getSignInPage(@RequestParam(value = "error", required = false) Boolean error,
                                Model model) {
        model.addAttribute("authForm", new AuthForm());

        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }

        return "auth/sign_in";
    }
}
