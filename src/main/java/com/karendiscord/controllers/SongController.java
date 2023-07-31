package com.karendiscord.controllers;

import com.karendiscord.dtos.RatingDTO;
import com.karendiscord.dtos.SongDTO;
import com.karendiscord.dtos.SongRatingDTO;
import com.karendiscord.models.Song;
import com.karendiscord.models.SongRating;
import com.karendiscord.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
@CrossOrigin
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getAllSongsHandler() {
        return songService.getAllSongs();
    }

    @GetMapping("{id}")
    public Song getSongByIdHandler(@PathVariable int id) {
        return songService.getSongById(id);
    }
    @GetMapping("artist/{name}")
    public List<Song> getAllByArtistHandler(@PathVariable String name) {
        return songService.getAllByArtist(name);
    }
    @GetMapping("genre/{name}")
    public List<Song> getAllByGenreHandler(@PathVariable String name) {
        return songService.getAllByGenre(name);
    }
    @PostMapping
    public Song createSong(@RequestBody SongDTO songDTO){
        return songService.createSong(songDTO);
    }

    @PutMapping("/{id}")
    public Song updateSongHandler(@PathVariable int id, @RequestBody SongDTO songDTO) {
        return songService.updateSong(id, songDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSongHandler(@PathVariable int id) {
        String deletedSong = songService.getSongById(id).getTitle();
        songService.deleteSong(id);
        return new ResponseEntity<>( "Song " + deletedSong + " successfully deleted.",
                HttpStatus.OK);
    }

    @PostMapping("/{id}/rating")
    public SongRating createSongRatingHandler(@PathVariable int id, @RequestBody RatingDTO ratingDTO, SongRatingDTO songRatingDTO){
        return songService.createSongRating(id, ratingDTO, songRatingDTO);
    }

    @GetMapping("/{id}/{userId}")
    public List<SongRating> getAllSongRatingsHandler(@PathVariable int id, @PathVariable int userId) {
        return songService.getAllSongRatings(id, userId);
    }
}