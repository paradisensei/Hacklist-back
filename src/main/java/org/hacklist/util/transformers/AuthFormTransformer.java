package org.hacklist.util.transformers;

import org.hacklist.model.Admin;
import org.hacklist.model.enums.Role;
import org.hacklist.util.forms.AuthForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Neil Alishev
 */
public class AuthFormTransformer {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static Admin toAdmin(AuthForm authForm) {
        Admin admin = new Admin();
        admin.setEmail(authForm.getEmail());
        admin.setPassword(encoder.encode(authForm.getPassword()));
        admin.setRole(Role.ROLE_DISABLED);

        return admin;
    }
}
