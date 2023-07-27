package com.karendiscord.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private int id;

    @Column(name = "playlist_name", nullable = false, columnDefinition = "text")
    private String name;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "playlist_owner")
    private User owner;

}
