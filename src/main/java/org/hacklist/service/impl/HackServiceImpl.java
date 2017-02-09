package org.hacklist.service.impl;

import org.hacklist.model.Hack;
import org.hacklist.repository.HackRepository;
import org.hacklist.service.HackService;
import org.hacklist.util.misc.HacksComparator;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Hack> getAll(String location) {
        return getAll(HacksComparator.comparator(location));
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
