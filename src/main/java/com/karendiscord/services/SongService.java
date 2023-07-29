package com.karendiscord.services;

import com.karendiscord.dtos.SongDTO;
import com.karendiscord.models.Artist;
import com.karendiscord.models.Genre;
import com.karendiscord.models.Song;
import com.karendiscord.repositories.ArtistRepository;
import com.karendiscord.repositories.GenreRepository;
import com.karendiscord.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public SongService(SongRepository songRepository, ArtistRepository artistRepository, GenreRepository genreRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(int id) {
        return songRepository.findById(id).orElseThrow();
    }
    public List<Song> getAllByArtist(String artistName) {
        return songRepository.findByArtist_Name(artistName);
    }

    public List<Song> getAllByGenre(String genreName) {
        return songRepository.findByGenre_Name(genreName);
    }


    public Song createSong(SongDTO songDTO) {


        if (!artistRepository.existsByName(songDTO.getArtist())) {
            Artist artist = new Artist(songDTO.getArtist());
            artistRepository.save(artist);
        }
        if(!genreRepository.existsByName(songDTO.getGenre())){
            Genre genre = new Genre(songDTO.getGenre());
            genreRepository.save(genre);
        }
            Song song = new Song(
                    songDTO.getTitle(),
                    artistRepository.findByName(songDTO.getArtist()),
                    genreRepository.findByName(songDTO.getGenre())
            );
            songRepository.save(song);

            return song;

    }


}
