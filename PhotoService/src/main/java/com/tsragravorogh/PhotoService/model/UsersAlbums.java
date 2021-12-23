package com.tsragravorogh.PhotoService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users_albums")
@Setter
@Getter
public class UsersAlbums {

    @EmbeddedId
    UsersAlbumsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    User user;

    @ManyToOne
    @MapsId("albumId")
    @JoinColumn(name = "id_albums")
    Album album;
}
