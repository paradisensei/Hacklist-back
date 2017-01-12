package org.hacklist.controller.api;

import org.hacklist.controller.ApiResponse;
import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.hacklist.service.UserService;
import org.hacklist.util.misc.CountdownLatchSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController(value = "hackApiController")
@RequestMapping("/api/hacks")
public class HackController {

    private final HackService hackService;
    private final UserService userService;

    @Autowired
    public HackController(HackService hackService, UserService userService) {
        this.hackService = hackService;
        this.userService = userService;
    }

    @RequestMapping("")
    public ApiResponse<List<Hack>> getHackList(@RequestParam("token") String token) {
        try {
            CountdownLatchSingleton.getCountDownLatch().await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(CountdownLatchSingleton.getCountDownLatch().getCount() == 0) {
            System.out.println("USER" + userService.getOneByClientToken(token.replace("\"", "")));
            return new ApiResponse<>(hackService.getAll());
        } else {
            // TODO: respond with a loading error...
            return null;
        }
    }

}
