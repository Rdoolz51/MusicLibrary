package com.karendiscord.repositories;

import com.karendiscord.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRatingRepository extends JpaRepository<SongRating, Integer> {
}