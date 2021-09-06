package ir.maktab.twitter.repository.impl;

import ir.maktab.twitter.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.twitter.domain.User;
import ir.maktab.twitter.repository.UserRepository;

import javax.persistence.EntityManager;

public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }
}
