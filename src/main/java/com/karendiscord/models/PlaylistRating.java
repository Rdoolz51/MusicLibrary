package com.karendiscord.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "playlist_ratings")
public class PlaylistRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_rating_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;
    @ManyToOne
    @JoinColumn(name = "rating_id")
    private Rating rating;
}
