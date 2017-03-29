package org.hacklist.service;

import org.hacklist.model.Hack;

import java.util.Collection;
import java.util.List;

/**
 * @author Aidar Shaifutdinov.
 */
public interface HackService {

    List<Hack> getAll();

    List<Hack> getAll(String location);

    Hack getOne(Long id);

    Hack add(Hack hack);

    void addAll(Collection<Hack> hacks);

    void delete(Long id);
}
