package com.karendiscord.repositories;

import com.karendiscord.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
}