package de.home.quarkus.notes;

import de.home.quarkus.common.BaseEntity_;
import de.home.quarkus.users.User;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.List;

@StaticMetamodel(Note.class)
public class Note_ extends BaseEntity_ {

    public static volatile SingularAttribute<Note, Long> id;
    public static volatile SingularAttribute<Note, String> title;
    public static volatile SingularAttribute<Note, String> content;
    public static volatile SingularAttribute<Note, User> author;
    public static volatile ListAttribute<Note, List<Note>> subnotes;
}
