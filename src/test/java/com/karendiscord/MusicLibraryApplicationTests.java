package com.karendiscord;

import com.karendiscord.models.*;
import com.karendiscord.repositories.ArtistRepository;
import com.karendiscord.repositories.GenreRepository;
import com.karendiscord.repositories.SongRepository;
import com.karendiscord.repositories.UserRepository;
import com.karendiscord.services.SongService;
import com.karendiscord.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class MusicLibraryApplicationTests {
    @InjectMocks
    public SongService songService;
    @InjectMocks
    public UserService userService;
    @Mock
    public SongRepository songRepository;
    @Mock
    public UserRepository userRepository;
    @Mock
    public ArtistRepository artistRepository;
    @Mock
    public GenreRepository genreRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        songService = new SongService(songRepository, artistRepository, genreRepository);
        userService = new UserService(userRepository);

    }

    Artist artist = new Artist(1, "Snoop Dogg");
    Genre genre = new Genre(1, "Hip Hop");

    @Test
    public void testGetUserById() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setFirstName("Ryan");
        expectedUser.setLastName("Dooley");
        expectedUser.setUsername("Rdoolz51");
        expectedUser.setPassword("password");

        when(userRepository.findById(1)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getUserById(1);

        assertEquals(expectedUser, actualUser);

        verify(userRepository).findById(1);
    }

    @Test
    public void testGetByUsername() {
        User expectedUser = new User();
        expectedUser.setId(1);
        expectedUser.setFirstName("Ryan");
        expectedUser.setLastName("Dooley");
        expectedUser.setUsername("Rdoolz51");
        expectedUser.setPassword("password");

        when(userRepository.findByUsername("Rdoolz51")).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.getByUsername("Rdoolz51");
        assertNotNull(expectedUser);
        assertEquals(expectedUser, actualUser);

        verify(userRepository).findByUsername("Rdoolz51");
    }

    @Test
    public void testGetAllSongs() {
        Song testSong = new Song();
        testSong.setId(1);
        testSong.setTitle("Smoke Weed Everyday");
        testSong.setArtist(artist);
        testSong.setGenre(genre);
        List<Song> expectedSongs = new ArrayList<>();
        expectedSongs.add(testSong);

        when(songRepository.findAll()).thenReturn(expectedSongs);

        List<Song> actualSongs = songService.getAllSongs();
        Assertions.assertEquals(expectedSongs, actualSongs);
        verify(songRepository).findAll();
    }

    @Test
    public void testGetSongById() {
        Song testSong = new Song();
        testSong.setId(1);
        testSong.setTitle("Smoke Weed Everyday");
        testSong.setArtist(artist);
        testSong.setGenre(genre);

        when(songRepository.findById(1)).thenReturn(Optional.of(testSong));

        Song actualSong = songService.getSongById(1);

        assertEquals(testSong, actualSong);

        verify(songRepository).findById(1);
    }

    @Test
    public void testGetSongByArtist() {
        Song testSong = new Song();
        testSong.setId(1);
        testSong.setTitle("Smoke Weed Everyday");
        testSong.setArtist(artist);
        testSong.setGenre(genre);
        List<Song> expectedSongs = new ArrayList<>();
        expectedSongs.add(testSong);

        when(songRepository.findSongByArtist_Name("Snoop Dogg")).thenReturn(Optional.of(expectedSongs));

        List<Song> actualSongs = songService.getAllByArtist("Snoop Dogg");

        assertEquals(expectedSongs, actualSongs);
        verify(songRepository).findSongByArtist_Name("Snoop Dogg");
    }

    @Test
    public void testGetSongByGenre() {
        Song testSong = new Song();
        testSong.setId(1);
        testSong.setTitle("Smoke Weed Everyday");
        testSong.setArtist(artist);
        testSong.setGenre(genre);
        List<Song> expectedSongs = new ArrayList<>();
        expectedSongs.add(testSong);

        when(songRepository.findSongByGenre_Name("Hip Hop")).thenReturn(Optional.of(expectedSongs));

        List<Song> actualSongs = songService.getAllByGenre("Hip Hop");

        assertEquals(expectedSongs, actualSongs);
        verify(songRepository).findSongByGenre_Name("Hip Hop");
    }

}
