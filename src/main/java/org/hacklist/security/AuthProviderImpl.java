package org.hacklist.security;

import org.hacklist.model.Admin;
import org.hacklist.model.enums.Role;
import org.hacklist.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final AdminRepository adminRepository;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthProviderImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Admin admin = adminRepository.findOneByEmail(email);

        if(admin == null || admin.getRole() == Role.ROLE_DISABLED) {
            throw new UsernameNotFoundException("User not found.");
        }

        if (!encoder.matches(password, admin.getPassword()))
            throw new BadCredentialsException("Bad credentials.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(admin.getRole().toString()));
        return new UsernamePasswordAuthenticationToken(admin, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
