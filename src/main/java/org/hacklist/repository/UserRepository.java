package org.hacklist.repository;

import org.hacklist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Aidar Shaifutdinov.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByClientToken(String clientToken);
}
