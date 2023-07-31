package com.karendiscord.services;

import com.karendiscord.dtos.RatingDTO;
import com.karendiscord.dtos.SongDTO;
import com.karendiscord.dtos.SongRatingDTO;
import com.karendiscord.models.*;
import com.karendiscord.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final SongRatingRepository songRatingRepository;
    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;

    @Autowired
    public SongService(SongRepository songRepository, ArtistRepository artistRepository, GenreRepository genreRepository, SongRatingRepository songRatingRepository, RatingRepository ratingRepository, UserRepository userRepository) {
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.songRatingRepository = songRatingRepository;
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
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
        if (!genreRepository.existsByName(songDTO.getGenre())) {
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

    public Song updateSong(int id, SongDTO songDTO) {
        Song song = songRepository.findById(id).orElseThrow();

        if (!artistRepository.existsByName(songDTO.getArtist())) {
            Artist artist = new Artist(songDTO.getArtist());
            artistRepository.save(artist);
        }
        if (!genreRepository.existsByName(songDTO.getGenre())) {
            Genre genre = new Genre(songDTO.getGenre());
            genreRepository.save(genre);
        }

        song.setTitle(songDTO.getTitle());
        song.setArtist(artistRepository.findByName(songDTO.getArtist()));
        song.setGenre(genreRepository.findByName(songDTO.getGenre()));

        songRepository.save(song);

        return song;
    }

    public void deleteSong(int id) {
        Song song = songRepository.findById(id).orElseThrow();
        songRepository.delete(song);
    }

    public SongRating createSongRating(int songId, RatingDTO ratingDTO, SongRatingDTO songRatingDTO) {
        Rating rating = new Rating(ratingDTO.getStars(), userRepository.findById(ratingDTO.getOwnerId()).orElseThrow());
        ratingRepository.save(rating);
        Song song = songRepository.findById(songId).orElseThrow();
        songRatingDTO.setSong(song);
        songRatingDTO.setRating(rating);
        SongRating songRating = new SongRating(songRatingDTO.getSong(), songRatingDTO.getRating());

        songRatingRepository.save(songRating);

        return songRating;

    }
    public List<SongRating> getAllSongRatings(int userId, int songId) {
        return songRatingRepository.findSongRatingByRating_Owner_IdAndSong_Id(userId, songId);
    }

}
