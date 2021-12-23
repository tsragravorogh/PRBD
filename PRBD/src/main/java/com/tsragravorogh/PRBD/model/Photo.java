package com.tsragravorogh.PRBD.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "link")
    private String link;

    @Column(name = "album_id")
    private int albumId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return getId() == photo.getId() &&
                getAlbumId() == photo.getAlbumId() &&
                Objects.equals(getLink(), photo.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLink(), getAlbumId());
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }
}
