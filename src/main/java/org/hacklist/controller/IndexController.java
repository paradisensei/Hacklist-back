package org.hacklist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aidar Shaifutdinov.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/admin/hacks";
    }

}
