package com.karendiscord.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int id;

    @Column(name = "song_title", nullable = false, columnDefinition = "text")
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Song(String title, Artist artist, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }
}
