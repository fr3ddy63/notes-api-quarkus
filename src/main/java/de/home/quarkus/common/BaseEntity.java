package de.home.quarkus.common;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> {

    @NotNull(message = "createdAt may not be null")
    protected Instant createdAt;

    public BaseEntity() {
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = Instant.now();
    }

    public abstract I getId();
    protected abstract void setId(I id);

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    protected void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
