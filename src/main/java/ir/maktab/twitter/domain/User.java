package ir.maktab.twitter.domain;

import ir.maktab.twitter.base.domain.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = User.TABLE_NAME)
public class User extends BaseEntity<Long> {

    public static final String TABLE_NAME = "user";

    private String email;

    private String username;

    private String password;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String email, String username, String password, List<Twitt> twits
            , List<Comment> comments, List<Like> likes) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.twits = twits;
        this.comments = comments;
        this.likes = likes;
    }

    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    private List<Twitt> twits = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = TABLE_NAME)
    @Cascade(CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Twitt> getTwits() {
        return twits;
    }

    public void setTwits(List<Twitt> twitts) {
        this.twits = twitts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
