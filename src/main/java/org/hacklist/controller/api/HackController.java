package org.hacklist.controller.api;

import com.jayway.awaitility.core.ConditionTimeoutException;
import org.hacklist.controller.ApiResponse;
import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.hacklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.pollinterval.IterativePollInterval.iterative;

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
    public ApiResponse<List<Hack>> getHackList(@RequestParam("token") String token)
            throws ConditionTimeoutException {

        await().with()
                .pollInterval(iterative(duration -> duration.plus(1000)))
                .atMost(2, TimeUnit.SECONDS)
                .until(() -> userService.get(token) != null);

        String location = userService.get(token).getLocation();
        List<Hack> hacks = location == null ? hackService.getAll()
                : hackService.getAll(location);

        return new ApiResponse<>(hacks);
    }

    @ExceptionHandler(ConditionTimeoutException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ApiResponse handleException(ConditionTimeoutException e) {
        return new ApiResponse("Auth failed!");
    }

}
