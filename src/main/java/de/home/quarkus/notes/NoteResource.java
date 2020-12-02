package de.home.quarkus.notes;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class NoteResource {

    @Inject
    NoteService service;

    private final Note note;

    public NoteResource(Note note) {

        this.note = note;
    }

    @GET
    public Response getNote() {

        return Response.ok(this.note).build();
    }

    @DELETE
    public Response deleteNote() {

        this.service.remove(this.note);
        return Response.ok().build();
    }

    @GET
    @Path("/subnotes")
    public Response getSubnotes() {

        return Response.ok(this.note.getSubnotes()).build();
    }
}
