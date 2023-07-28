package com.karendiscord.services;

import com.karendiscord.models.Song;
import com.karendiscord.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(int id) {
        return songRepository.findById(id).orElseThrow();
    }
    public List<Song> getAllByArtist(String artistName) {
        return songRepository.findSongByArtist_Name(artistName).orElseThrow();
    }

    public List<Song> getAllByGenre(String genreName) {
        return songRepository.findSongByGenre_Name(genreName).orElseThrow();
    }


}
