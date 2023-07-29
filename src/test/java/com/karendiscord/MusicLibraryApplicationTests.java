package com.karendiscord;

import com.karendiscord.dtos.SongDTO;
import com.karendiscord.models.*;
import com.karendiscord.repositories.*;
import com.karendiscord.services.SongService;
import com.karendiscord.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@SpringBootTest
class MusicLibraryApplicationTests {
    static ArtistRepository artistRepository;
    static GenreRepository genreRepository;
    static PlaylistRepository playlistRepository;
    static RatingRepository ratingRepository;
    static SongRepository songRepository;
    static UserRepository userRepository;

    static SongService songService;
    static UserService userService;

    static List<Song> expectedSongs;
    static List<Artist> expectedArtists;
    static List<Genre> expectedGenres;
    static List<User> expectedUsers;

    @BeforeAll
    public static void initialize() {
        artistRepository = mock(ArtistRepository.class);
        genreRepository = mock(GenreRepository.class);
        playlistRepository = mock(PlaylistRepository.class);
        ratingRepository = mock(RatingRepository.class);
        songRepository = mock(SongRepository.class);
        userRepository = mock(UserRepository.class);

        songService = new SongService(songRepository, artistRepository, genreRepository);
        userService = new UserService(userRepository);

        expectedArtists = new ArrayList<>(2);
        expectedArtists.add(new Artist(1, "Eminem"));
        expectedArtists.add(new Artist(2, "Shinedown"));

        expectedGenres = new ArrayList<>(2);
        expectedGenres.add(new Genre(1, "Rap"));
        expectedGenres.add(new Genre(2, "Rock"));

        expectedSongs = new ArrayList<>(2);
        expectedSongs.add(new Song(1, "Rap God", expectedArtists.get(0), expectedGenres.get(0)));
        expectedSongs.add(new Song(2, "Simple Man", expectedArtists.get(1), expectedGenres.get(1)));

        expectedUsers = new ArrayList<>(2);
        expectedUsers.add(new User(1, "Ryan", "Dooley", "Rdoolz51", "password1234"));
        expectedUsers.add(new User(2, "John", "Smith", "Jsmith22", "password1234"));


        when(artistRepository.findAll()).thenReturn(expectedArtists);
        when(artistRepository.findById(1)).thenReturn(Optional.of(expectedArtists.get(0)));
        when(artistRepository.findByName("Shinedown")).thenReturn(expectedArtists.get(1));
        when(artistRepository.findByName("Eminem")).thenReturn(expectedArtists.get(0));
        when(artistRepository.existsByName("Hannah Montana")).thenReturn(false);

        when(genreRepository.findAll()).thenReturn(expectedGenres);
        when(genreRepository.findById(2)).thenReturn(Optional.of(expectedGenres.get(1)));
        when(genreRepository.findByName("Rock")).thenReturn(expectedGenres.get(1));
        when(genreRepository.findByName("Rap")).thenReturn(expectedGenres.get(0));
        when(genreRepository.existsByName("Rap")).thenReturn(true);
        when(genreRepository.existsByName("Rock")).thenReturn(true);

        when(songRepository.findAll()).thenReturn(expectedSongs);
        when(songRepository.findById(1)).thenReturn(Optional.of(expectedSongs.get(0)));
        when(songRepository.findByArtist_Name("Eminem")).thenReturn(expectedSongs.subList(0, 1));
        when(songRepository.findByGenre_Name("Rock")).thenReturn(expectedSongs.subList(1, 2));
        when(songRepository.findByTitle("Simple Man")).thenReturn(Optional.of(expectedSongs.get(1)));
        when(songRepository.save(any(Song.class))).thenAnswer(invocation -> invocation.getArgument(0));


        when(userRepository.findById(1)).thenReturn(Optional.of(expectedUsers.get(0)));
        when(userRepository.findByUsername("Rdoolz51")).thenReturn(Optional.of(expectedUsers.get(0)));

    }


    @Test
    public void testGetUserById() {
        User expectedUser = expectedUsers.get(0);
        User returnedUser = userService.getUserById(1);

        Assertions.assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void testGetByUsername() {
        User expectedUser = expectedUsers.get(0);
        User returnedUser = userService.getByUsername("Rdoolz51");

        Assertions.assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void testGetAllSongs() {
        List<Song> returnedSongs = songService.getAllSongs();
        Assertions.assertEquals(expectedSongs, returnedSongs);
    }

    @Test
    public void testGetSongById() {
        Song expectedSong = expectedSongs.get(0);
        Song returnedSong = songService.getSongById(1);

        Assertions.assertEquals(expectedSong, returnedSong);
    }

    @Test
    public void testGetSongByArtist() {
        List<Song> expectedSongList = new ArrayList<>();
        expectedSongList.add(expectedSongs.get(0));

        List<Song> returnedSongList = songService.getAllByArtist("Eminem");

        Assertions.assertEquals(expectedSongList, returnedSongList);
    }

    @Test
    public void testGetSongByGenre() {
        List<Song> expectedSongList = new ArrayList<>();
        expectedSongList.add(expectedSongs.get(1));

        List<Song> returnedSongList = songService.getAllByGenre("Rock");

        Assertions.assertEquals(expectedSongList, returnedSongList);

    }

    @Test
    public void testCreateSong() {
        SongDTO songDTO = new SongDTO();
        songDTO.setTitle(expectedSongs.get(0).getTitle());
        songDTO.setArtist(expectedArtists.get(0).getName());
        songDTO.setGenre(expectedGenres.get(0).getName());

        Song expectedSong = new Song();
        expectedSong.setTitle(expectedSongs.get(0).getTitle());
        expectedSong.setArtist(expectedSongs.get(0).getArtist());
        expectedSong.setGenre(expectedSongs.get(0).getGenre());

        Song returnedSong = songService.createSong(songDTO);

        Assertions.assertEquals(expectedSong, returnedSong);

    }

}
