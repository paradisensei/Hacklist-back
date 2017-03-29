package org.hacklist.task;

import org.hacklist.parser.Parser;
import org.hacklist.service.HackService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Aidar Shaifutdinov.
 *
 * This task is responsible for parsing hacks from 'http://it-events.com'.
 *
 */
@Component
public class ParseHacksTask {

    private final HackService hackService;
    private final Parser parser;

    public ParseHacksTask(HackService hackService, Parser parser) {
        this.hackService = hackService;
        this.parser = parser;
    }

    // fixedRate = 1 day
    @Scheduled(fixedRate = 86400000)
    public void parseHacks() {
        try {
            hackService.addAll(parser.parse());
        } catch (IOException ignored) {
        }
    }

}
