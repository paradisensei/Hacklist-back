package org.hacklist.util.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
public class AuthForm {

    private static final String BLANK_MESSAGE = "This field is mandatory";

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 1, max = 50, message = "From 1 to 50 symbols")
    @Email
    private String email;

    @NotBlank(message = BLANK_MESSAGE)
    @Size(min = 7, max = 20, message = "From 7 to 20 symbols")
    private String password;

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

}
