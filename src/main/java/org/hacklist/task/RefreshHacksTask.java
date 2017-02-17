package org.hacklist.task;

import org.hacklist.model.Hack;
import org.hacklist.service.CacheService;
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
    private final CacheService cacheService;

    @Autowired
    public RefreshHacksTask(HackService hackService, CacheService cacheService) {
        this.hackService = hackService;
        this.cacheService = cacheService;
    }

    // fixedRate = 1 hour
    @Scheduled(fixedRate = 3600000)
    public void refreshHacks() {
        List<Hack> hacks = hackService.getAll();
        boolean flushCache = false;
        for (Hack h : hacks) {
            long hackTime = h.getDate().getTime();
            long nowTime = System.currentTimeMillis();
            if (nowTime > hackTime) {
                hackService.delete(h.getId());
                flushCache = true;
            }
        }
        if (flushCache) {
            cacheService.flush();
        }
    }

}
