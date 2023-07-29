package com.karendiscord.repositories;

import com.karendiscord.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Integer> {

     List<Song> findByArtist_Name(String artistName);

    List<Song> findByGenre_Name(String genreName);

    Optional <Song> findByTitle(String title);
}