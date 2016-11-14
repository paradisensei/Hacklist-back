package org.hacklist.task;

import org.hacklist.model.Hack;
import org.hacklist.service.HackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 *
 * This task is responsible for keeping 'hack' table up to date.
 *
 * note: obviously, it's better to use low-level sql-query,
 *       but i consider it to be a premature optimization
 */
@Component
public class RefreshHacksTask {

    private final HackService hackService;

    @Autowired
    public RefreshHacksTask(HackService hackService) {
        this.hackService = hackService;
    }

    // fixedRate = 1 hour
    @Scheduled(fixedRate = 3600000)
    public void refreshHacks() {
        List<Hack> hacks = hackService.getAll();
        hacks.stream()
                .filter(h -> {
                    long hackTime = h.getDate().getTime();
                    long nowTime = System.currentTimeMillis();
                    return nowTime > hackTime;
                })
                .forEach(h -> hackService.delete(h.getId()));
    }

}
