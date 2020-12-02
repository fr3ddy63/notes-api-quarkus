package de.home.quarkus.common;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

@StaticMetamodel(BaseEntity.class)
public class BaseEntity_ {

    public static volatile SingularAttribute<BaseEntity<?>, Instant> createdAt;
}
