package org.hacklist.service;

import org.hacklist.model.Hack;
import org.hacklist.repository.HackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
@Service
public class HackServiceImpl implements HackService {

    private final HackRepository hackRepository;

    @Autowired
    public HackServiceImpl(HackRepository hackRepository) {
        this.hackRepository = hackRepository;
    }

    @Override
    public List<Hack> getAll() {
        return hackRepository.findAll();
    }

}
