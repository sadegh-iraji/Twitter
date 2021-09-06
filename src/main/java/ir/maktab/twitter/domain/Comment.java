package ir.maktab.twitter.domain;

import ir.maktab.twitter.base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Comment.TABLE_NAME)
public class Comment extends BaseEntity<Long> {

    public static final String TABLE_NAME = "comment";

    @Column(length = 50)
    private String content;

    @ManyToOne
    private User user;

    @ManyToOne
    private Twitt twitt;

    public Comment() {
    }

    public Comment(String content, User user, Twitt twitt) {
        this.content = content;
        this.user = user;
        this.twitt = twitt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
