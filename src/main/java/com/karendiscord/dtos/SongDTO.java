package com.karendiscord.dtos;

import com.karendiscord.models.Artist;
import com.karendiscord.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SongDTO {

    private String title;
    private String artist;
    private String genre;
}
