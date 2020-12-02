package de.home.quarkus.notes;

import de.home.quarkus.common.BaseService;
import de.home.quarkus.users.User;
import de.home.quarkus.users.User_;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class NoteService extends BaseService<Long, Note, NoteParam> {

    public NoteService() {
        super(Note.class);
    }

    public List<Note> findByAuthor(User author, NoteParam param) {
        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<Note> query = builder.createQuery(Note.class);
        Root<Note> root = query.from(Note.class);

        List<Predicate> restrictions = this.getRestrictions(builder, root, param);
        restrictions.add(builder.equal(root.get(Note_.author), author));
        query.where(restrictions.toArray(new Predicate[0]));

        TypedQuery<Note> typedQuery = super.entityManager.createQuery(query);

        return super.limitQuery(typedQuery, param).getResultList();
    }

    public long countByAuthor(User author, NoteParam param) {
        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<Note> root = query.from(Note.class);

        query.select(builder.count(root));
        List<Predicate> restrictions = this.getRestrictions(builder, root, param);
        restrictions.add(builder.equal(root.get(Note_.author), author));
        query.where(restrictions.toArray(new Predicate[0]));

        return super.entityManager.createQuery(query).getSingleResult();
    }

    @Override
    protected List<Predicate> getRestrictions(CriteriaBuilder builder, Root<Note> root, NoteParam param) {

        return new ArrayList<>();
    }
}
