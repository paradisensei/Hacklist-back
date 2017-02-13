package org.hacklist.util.transformers;

import org.hacklist.model.Admin;
import org.hacklist.model.enums.AdminStatus;
import org.hacklist.util.forms.AuthForm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.function.Function;

/**
 * @author Neil Alishev
 */
public class AuthFormTransformer implements Function<AuthForm, Admin> {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public Admin apply(AuthForm authForm) {
        Admin admin = new Admin();
        admin.setEmail(authForm.getEmail());
        admin.setPassword(encoder.encode(authForm.getPassword()));
        admin.setStatus(AdminStatus.OFF);
        return admin;
    }

}
