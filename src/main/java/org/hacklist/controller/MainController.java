package org.hacklist.controller;

import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@Controller
public class MainController {

    private final HackService hackService;

    @Autowired
    public MainController(HackService hackService) {
        this.hackService = hackService;
    }

    @RequestMapping("/hack/list")
    @ResponseBody
    public List<Hack> getHackList() {
        return hackService.getAll();
    }

}
