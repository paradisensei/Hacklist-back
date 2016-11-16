package org.hacklist.controller.api;

import org.hacklist.controller.ApiResponse;
import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController(value = "hackApiController")
@RequestMapping("/api/hacks")
public class HackController {

    private final HackService hackService;

    @Autowired
    public HackController(HackService hackService) {
        this.hackService = hackService;
    }

    @RequestMapping("")
    public ApiResponse<List<Hack>> getHackList() {
        return new ApiResponse<>(hackService.getAll());
    }
}
