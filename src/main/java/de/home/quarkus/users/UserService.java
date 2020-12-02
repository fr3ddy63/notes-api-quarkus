package de.home.quarkus.users;

import de.home.quarkus.common.BaseService;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService extends BaseService<Long, User, UserParam> {

    public UserService() {
        super(User.class);
    }

    public Optional<User> findByName(String username) {
        CriteriaBuilder builder = super.entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);

        Expression<String> expression = builder.lower(root.get(User_.username));
        Predicate predicate = builder.equal(expression, username.toLowerCase());
        query.where(predicate);

        return super.entityManager.createQuery(query).getResultStream().findFirst();
    }

    @Override
    protected List<Predicate> getRestrictions(CriteriaBuilder builder, Root<User> root, UserParam param) {

        return new ArrayList<>();
    }
}
