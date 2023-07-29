package com.karendiscord.dtos;

import com.karendiscord.models.Artist;
import com.karendiscord.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {

    private String title;
    private String artist;
    private String genre;

}
