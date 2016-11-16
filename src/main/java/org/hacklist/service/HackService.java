package org.hacklist.service;

import org.hacklist.model.Hack;

import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
public interface HackService {

    List<Hack> getAll();

    Hack getOne(Long id);

    Hack add(Hack hack);

    void delete(Long id);
}
