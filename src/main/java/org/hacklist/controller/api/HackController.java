package org.hacklist.controller.api;

import com.jayway.awaitility.core.ConditionTimeoutException;
import org.hacklist.controller.ApiResponse;
import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.hacklist.service.UserService;
import org.hacklist.util.misc.ErrorObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static com.jayway.awaitility.pollinterval.IterativePollInterval.iterative;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Aidar Shaifutdinov.
 */
@RestController(value = "hackApiController")
@RequestMapping("/api/hacks")
public class HackController {

    private final static int SECONDS_TO_WAIT = 10;

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
        String actualToken = token.replace("\"", "");

        await()
                .with().pollInterval(iterative(duration -> duration.plus(1000)))
                .atMost(SECONDS_TO_WAIT, SECONDS)
                .until(() -> userCreated(actualToken));

        System.out.println("USER" + userService.getOneByClientToken(token.replace("\"", "")));

        return new ApiResponse<>(hackService.getAll());
    }

    private boolean userCreated(String token) {
        return userService.getOneByClientToken(token) != null;
    }

    @ExceptionHandler(ConditionTimeoutException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorObject handleException(ConditionTimeoutException e) {
        return new ErrorObject("10 seconds are out!");
    }
}
