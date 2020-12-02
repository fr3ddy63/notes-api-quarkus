package de.home.quarkus.notes;

import de.home.quarkus.common.BaseEntity;
import de.home.quarkus.users.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "note")
public class Note extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    @NotBlank
    @Size(max = 128)
    private String title;

    @NotBlank
    @Size(max = 256)
    private String content;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "notes_subnotes",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "subnote_id")
    )
    private List<Note> subnotes;

    public Note() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Note> getSubnotes() {
        return subnotes;
    }

    public void setSubnotes(List<Note> subnotes) {
        this.subnotes = subnotes;
    }
}
