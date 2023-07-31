package com.karendiscord.dtos;

import com.karendiscord.models.Rating;
import com.karendiscord.models.Song;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SongRatingDTO {
    private Song song;
    private Rating rating;
}
