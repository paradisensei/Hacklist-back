package org.hacklist.repository;

import org.hacklist.model.Hack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface HackRepository extends JpaRepository<Hack, Long> {
}
