package com.karendiscord.controllers;

import com.karendiscord.dtos.SongDTO;
import com.karendiscord.models.Song;
import com.karendiscord.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("songs")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
public class SongController {

    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping()
    public List<Song> getAllSongsHandler() {
        return songService.getAllSongs();
    }

    @GetMapping("{id}")
    public Song getSongByIdHandler(@PathVariable int id) {
        return songService.getSongById(id);
    }
    @GetMapping("artist/{artistName}")
    public List<Song> getAllByArtistHandler(@PathVariable String artistName) {
        return songService.getAllByArtist(artistName);
    }
    @GetMapping("genre/{genreName}")
    public List<Song> getAllByGenreHandler(@PathVariable String genreName) {
        return songService.getAllByGenre(genreName);
    }
    @PostMapping
    public Song createSong(@RequestBody SongDTO songDTO){
        return songService.createSong(songDTO);
    }
}
