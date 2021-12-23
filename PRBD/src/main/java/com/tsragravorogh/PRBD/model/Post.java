package com.tsragravorogh.PRBD.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="body")
    private String body;

    @Column(name="activity_id")
    private int activityId;

    @Column(name="user_id")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", activityId=" + activityId +
                ", user_id=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getId() == post.getId() &&
                getActivityId() == post.getActivityId() &&
                getUserId() == post.getUserId() &&
                Objects.equals(getTitle(), post.getTitle()) &&
                Objects.equals(getBody(), post.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getBody(), getActivityId(), getUserId());
    }

}
