package com.tsragravorogh.PhotoService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="albums")
@Setter
@Getter
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @OneToMany(mappedBy = "album")
    Set<UsersAlbums> albumsUsers;
}
