package ir.maktab.twitter.domain;

import ir.maktab.twitter.base.domain.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Twitt.TABLE_NAME)
public class Twitt extends BaseEntity<Long> {

    public static final String TABLE_NAME = "twitt";

    @Column(length = 280)
    private String content;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = TABLE_NAME)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = TABLE_NAME)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public Twitt() {
    }

    public Twitt(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Twitt(String content, User user, List<Comment> comments, List<Like> likes) {
        this.content = content;
        this.user = user;
        this.comments = comments;
        this.likes = likes;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
