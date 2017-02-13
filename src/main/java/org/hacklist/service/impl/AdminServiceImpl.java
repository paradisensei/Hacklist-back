package org.hacklist.service.impl;

import org.hacklist.model.Admin;
import org.hacklist.repository.AdminRepository;
import org.hacklist.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neil Alishev
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public void add(Admin admin) {
        adminRepository.save(admin);
    }

}
