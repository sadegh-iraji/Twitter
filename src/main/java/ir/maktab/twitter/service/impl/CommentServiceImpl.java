package ir.maktab.twitter.service.impl;

import ir.maktab.twitter.base.service.impl.BaseServiceImpl;
import ir.maktab.twitter.domain.Comment;
import ir.maktab.twitter.repository.CommentRepository;
import ir.maktab.twitter.service.CommentService;

public class CommentServiceImpl extends BaseServiceImpl<Comment, Long, CommentRepository>
        implements CommentService {

    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }
}
