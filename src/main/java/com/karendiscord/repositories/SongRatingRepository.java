package com.karendiscord.repositories;

import com.karendiscord.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRatingRepository extends JpaRepository<SongRating, Integer> {
    List<SongRating> findSongRatingByRating_Owner_IdAndSong_Id(int ownerId, int songId);
}