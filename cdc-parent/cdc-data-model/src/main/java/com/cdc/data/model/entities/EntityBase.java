package com.cdc.data.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class EntityBase<T extends EntityBase<T>> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    protected LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Entities compare by identity, not by attributes.
     *
     * @param that
     *            The other entity of the same type
     * @return true if the identities are the same, regardless of the other
     *         attributes.
     * @throws IllegalStateException
     *             one of the entities does not have the identity attribute set.
     */
    public boolean sameIdentityAs(final T that) {
        return this.equals(that);
    }

    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof EntityBase<?> that)) {
            return false;
        }
        _checkIdentity(this);
        _checkIdentity(that);
        return this.id.equals(that.getId());
    }

    /**
     * Checks the passed entity, if it has an identity. It gets an identity only by
     * saving.
     *
     * @param entity
     *            the entity to be checked
     * @throws IllegalStateException
     *             the passed entity does not have the identity attribute set.
     */
    private void _checkIdentity(final EntityBase<?> entity) {
        if (entity.getId() == null) {
            throw new IllegalStateException("Identity missing in entity: " + entity);
        }
    }
}
