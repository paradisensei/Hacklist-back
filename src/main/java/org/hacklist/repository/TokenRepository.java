package org.hacklist.repository;

import org.hacklist.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
