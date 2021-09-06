package ir.maktab.twitter.repository.impl;

import ir.maktab.twitter.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.twitter.domain.Twitt;
import ir.maktab.twitter.repository.TwittRepository;

import javax.persistence.EntityManager;

public class TwittRepositoryImpl extends BaseRepositoryImpl<Twitt, Long> implements TwittRepository {

    public TwittRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Twitt> getEntityClass() {
        return Twitt.class;
    }
}
