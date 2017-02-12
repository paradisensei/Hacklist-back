package org.hacklist.model;

import org.hacklist.model.enums.Role;

import javax.persistence.*;

/**
 * @author Neil Alishev
 */
@Entity
@Table(name = "admin")
@SequenceGenerator(name = "admin_gen", sequenceName = "admin_seq", allocationSize = 1)
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_gen")
    private Long id;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
