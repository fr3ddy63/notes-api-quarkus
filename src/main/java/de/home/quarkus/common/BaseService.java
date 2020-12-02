package de.home.quarkus.common;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<I extends Serializable, E extends BaseEntity<I>, P extends BaseParam> {

    @Inject
    protected EntityManager entityManager;

    protected final Class<E> entityClass;

    protected BaseService(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional
    public void persist(E entity) {
        this.entityManager.persist(entity);
    }

    public Optional<E> find(I id) {
        return Optional.ofNullable(this.entityManager.find(this.entityClass, id));
    }

    public List<E> find(P param) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = builder.createQuery(this.entityClass);
        Root<E> root = query.from(this.entityClass);

        List<Predicate> restrictions = this.getRestrictions(builder, root, param);
        query.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<E> typedQuery = this.entityManager.createQuery(query);

        return this.limitQuery(typedQuery, param).getResultList();
    }

    public long count(P param) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<E> root = query.from(this.entityClass);

        query.select(builder.count(root));
        List<Predicate> restrictions = this.getRestrictions(builder, root, param);
        query.where(restrictions.toArray(new Predicate[0]));

        return this.entityManager.createQuery(query).getSingleResult();
    }

    @Transactional
    public E merge(E entity) {
        return this.entityManager.merge(entity);
    }

    @Transactional
    public void remove(E entity) {
        this.entityManager.remove(this.merge(entity));
    }

    protected abstract  List<Predicate> getRestrictions(CriteriaBuilder builder, Root<E> root, P param);

    protected <T> TypedQuery<T> limitQuery(TypedQuery<T> query, Pageable pageable) {
        return query.setFirstResult(pageable.getFirstResult()).setMaxResults(pageable.getRpp());
    }
}
