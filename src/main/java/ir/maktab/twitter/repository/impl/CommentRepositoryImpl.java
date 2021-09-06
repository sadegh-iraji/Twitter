package ir.maktab.twitter.repository.impl;

import ir.maktab.twitter.base.repository.impl.BaseRepositoryImpl;
import ir.maktab.twitter.domain.Comment;
import ir.maktab.twitter.repository.CommentRepository;

import javax.persistence.EntityManager;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment, Long> implements CommentRepository {

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }
}
