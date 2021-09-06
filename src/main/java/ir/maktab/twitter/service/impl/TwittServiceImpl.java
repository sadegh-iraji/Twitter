package ir.maktab.twitter.service.impl;

import ir.maktab.twitter.base.service.impl.BaseServiceImpl;
import ir.maktab.twitter.domain.Twitt;
import ir.maktab.twitter.repository.TwittRepository;
import ir.maktab.twitter.service.TwittService;

public class TwittServiceImpl extends BaseServiceImpl<Twitt, Long, TwittRepository>
        implements TwittService {

    public TwittServiceImpl(TwittRepository repository) {
        super(repository);
    }
}
