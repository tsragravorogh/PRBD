package com.tsragravorogh.PhotoService.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UsersAlbumsKey implements Serializable {
    @Column(name = "id_user")
    private int userId;

    @Column(name = "id_albums")
    private int albumId;

    public UsersAlbumsKey(int userId, int albumId) {
        this.userId = userId;
        this.albumId = albumId;
    }

    public UsersAlbumsKey() {
    }
}
