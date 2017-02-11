package org.hacklist.service.impl;

import org.hacklist.logging.LogAspect;
import org.hacklist.model.Hack;
import org.hacklist.repository.HackRepository;
import org.hacklist.service.HackService;
import org.hacklist.util.misc.HacksComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
@Transactional(readOnly = true)
public class HackServiceImpl implements HackService {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private final HackRepository hackRepository;

    @Autowired
    public HackServiceImpl(HackRepository hackRepository) {
        this.hackRepository = hackRepository;
    }

    @Override
    public List<Hack> getAll() {
        return getAll(HacksComparator.comparator());
    }

    @Override
    @Cacheable("hacks")
    public List<Hack> getAll(String location) {
        logger.info("filling cache with hacks in " + location);
        Comparator<Hack> comparator = location == null ? HacksComparator.comparator()
                : HacksComparator.comparator(location);
        return getAll(comparator);
    }

    private List<Hack> getAll(Comparator<Hack> comparator) {
        // this is probably acceptable due to small number of hacks in the db.
        List<Hack> list = hackRepository.findAll();
        Collections.sort(list, comparator);
        return list;
    }

    @Override
    public Hack getOne(Long id) {
        return hackRepository.findOne(id);
    }

    @Override
    @Transactional
    public Hack add(Hack hack) {
        return hackRepository.save(hack);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        hackRepository.delete(id);
    }

}
