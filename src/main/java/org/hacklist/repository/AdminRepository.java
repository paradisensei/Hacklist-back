package org.hacklist.repository;

import org.hacklist.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neil Alishev
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findOneByEmail(String email);
}
