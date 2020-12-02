package de.home.quarkus.common;

public interface Postable<E extends BaseEntity<?>> {

    E toEntity();
}
