package org.hacklist.controller.admin;

import org.hacklist.model.Admin;
import org.hacklist.service.AdminService;
import org.hacklist.util.forms.AuthForm;
import org.hacklist.util.transformers.AuthFormTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/admin")
public class AuthController {

    private final AdminService adminService;
    private final Function<AuthForm, Admin> transformer;

    @Autowired
    public AuthController(AdminService adminService) {
        this.adminService = adminService;
        transformer = new AuthFormTransformer();
    }

    @RequestMapping("/sign_in")
    public String getSignIn(@RequestParam(value = "error", required = false) Boolean error,
                            Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", error);
        }
        model.addAttribute("authForm", new AuthForm());
        return "admin/sign_in";
    }

    @RequestMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("authForm", new AuthForm());
        return "admin/sign_up";
    }

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("authForm") @Valid AuthForm authForm,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "admin/sign_up";
        }
        adminService.add(transformer.apply(authForm));
        return "redirect:/admin/sign_in";
    }

}
