package com.karendiscord.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("artists")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:5500"})
public class ArtistController {
//    private final ArtistService artistService;
//
//    @Autowired
//    public ArtistController(ArtistService artistService) {
//        this.artistService = artistService;
//    }

//    @GetMapping("/name/{name}")
//    public Artist getArtistByName(@PathVariable String name){
//        return artistService.getArtistByName(name);
//    }
}
