package de.home.quarkus.common;

public interface Pageable {

    int getPage();

    int getRpp();

    default int getFirstResult() {
        return this.getPage() * this.getRpp();
    }
}
