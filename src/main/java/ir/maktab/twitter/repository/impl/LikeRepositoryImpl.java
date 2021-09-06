package ir.maktab.twitter.repository.impl;

import ir.maktab.twitter.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.twitter.domain.Like;
import ir.maktab.twitter.repository.LikeRepository;

import javax.persistence.EntityManager;

public class LikeRepositoryImpl extends BaseRepositoryImpl<Like, Long> implements LikeRepository {

    public LikeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Like> getEntityClass() {
        return Like.class;
    }
}
