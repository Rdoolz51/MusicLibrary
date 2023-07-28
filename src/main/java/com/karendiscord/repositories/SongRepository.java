package com.karendiscord.repositories;

import com.karendiscord.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Integer> {

    Optional <List<Song>> findSongByArtist_Name(String artistName);

    Optional <List<Song>> findSongByGenre_Name(String genreName);

}