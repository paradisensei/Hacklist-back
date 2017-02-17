package org.hacklist.controller.admin;

import org.hacklist.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Aidar Shaifutdinov.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CacheService cacheService;

    @Autowired
    public AdminController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @RequestMapping(value = "/flush", method = RequestMethod.POST)
    @ResponseBody
    public void flush() {
        cacheService.flush();
    }

}
