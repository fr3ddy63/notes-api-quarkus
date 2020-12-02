package de.home.quarkus.users;

import de.home.quarkus.common.BaseEntity;
import de.home.quarkus.notes.Note;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "user_account")
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotBlank(message = "username may not be blank")
    @Pattern(regexp = "^[0-9a-zA-Z]{1,16}$")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "password may not be blank")
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Note> notes;

    public User() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    protected void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
