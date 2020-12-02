package de.home.quarkus.notes;

import de.home.quarkus.common.Postable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NotePostable implements Postable<Note> {

    @NotBlank
    @Size(max = 128)
    private String title;

    @NotBlank
    @Size(max = 256)
    private String content;

    public NotePostable() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Note toEntity() {

        Note entity = new Note();
        entity.setTitle(this.title);
        entity.setContent(this.content);

        return entity;
    }
}
