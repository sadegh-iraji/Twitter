package ir.maktab.twitter.domain;

import ir.maktab.twitter.base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Like.TABLE_NAME)
public class Like extends BaseEntity<Long> {

    public static final String TABLE_NAME = "likes";

    @ManyToOne
    private User user;

    @ManyToOne
    private Twitt twitt;

    public Like() {
    }

    public Like(User user, Twitt twitt) {
        this.user = user;
        this.twitt = twitt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Twitt getTwitt() {
        return twitt;
    }

    public void setTwitt(Twitt twitt) {
        this.twitt = twitt;
    }
}
