package ir.maktab.twitter.service.impl;

import ir.maktab.twitter.base.service.impl.BaseServiceImpl;
import ir.maktab.twitter.domain.Like;
import ir.maktab.twitter.repository.LikeRepository;
import ir.maktab.twitter.service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<Like, Long, LikeRepository>
        implements LikeService {

    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }
}
