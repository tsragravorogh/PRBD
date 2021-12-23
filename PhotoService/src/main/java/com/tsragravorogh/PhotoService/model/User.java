package com.tsragravorogh.PhotoService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="users")
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private int id;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "surname", nullable = false, updatable = false)
    private String surname;


    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    Set<UsersAlbums> usersAlbums;
}
