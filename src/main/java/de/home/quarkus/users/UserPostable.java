package de.home.quarkus.users;

import de.home.quarkus.common.Postable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserPostable implements Postable<User> {

    @NotBlank(message = "username may not be blank")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,16}$")
    private String username;

    @NotBlank(message = "password may not be blank")
    @Size(min = 6, max = 32, message = "password must be between 6 and 32 characters long")
    private String password;

    protected UserPostable() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public User toEntity() {

        User entity = new User();
        entity.setUsername(this.username);
        entity.setPassword(this.password);

        return entity;
    }
}
