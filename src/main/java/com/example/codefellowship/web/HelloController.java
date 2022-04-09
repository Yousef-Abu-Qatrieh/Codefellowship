package com.example.codefellowship.web;

import com.example.codefellowship.domain.Album;
import com.example.codefellowship.domain.Song;
import com.example.codefellowship.infrastructure.AlbumRepository;
import com.example.codefellowship.infrastructure.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class HelloController {

    @GetMapping("/hello")
    String hello() {
        return "hello";
    }

    @GetMapping("/capitalize/{name}")
    String capitalize(@PathVariable(name = "name", required = false) String name, Model model) {
        name = name.toUpperCase(Locale.ROOT);
        model.addAttribute("name", name);
        return "capitalize";
    }

    @ResponseBody
    @GetMapping("/albums-Review")
    List<Album> albums() {
        List<Album> albums = new ArrayList<>();
        albums.add(new Album("Sale el Sol", "Shakira", 2, 193, "https://www.bing.com/images/blob?bcid=qAbo8lEwJPoDyQKP0509tDMFQlEA.....1E"));
        albums.add(new Album("Goodbye Lullaby", " Avril Lavigne", 4, 226, "https://upload.wikimedia.org/wikipedia/en/thumb/9/94/Goodbye_Lullaby.png/220px-Goodbye_Lullaby.png"));
        return albums;
    }

    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    SongRepository songRepository;



    @GetMapping("/albums")
    public String getAlbum(Model model) {
        model.addAttribute("albumsList", albumRepository.findAll());
        return "album";
    }

    @PostMapping("/addAlbum")
    public RedirectView addNewAlbum(@ModelAttribute Album album) {
        albumRepository.save(album);
        return new RedirectView("album");
    }
    @ResponseBody
    @PostMapping("/album/{id}")
    Album createNewAlbum(@RequestBody Album album){
        return  albumRepository.save(album);
    }
    @ResponseBody
    @GetMapping("/album")
    List <Album> getAllAlbums(){
        return  albumRepository.findAll();
    }

    @ResponseBody
    @GetMapping("/album/{id}")
    Album getAlbum(@PathVariable Long id ){
        Album album = albumRepository.findById(id).get();
        return album;
    }

    @GetMapping("/songs")
    String getSongs(Model model){
        model.addAttribute("mySongList" , songRepository.findAll());
        return "song";
    }

    @PostMapping("/addSong")
    RedirectView addNewSong(@ModelAttribute Song song) {
        songRepository.save(song);
        return new RedirectView("/songs");
    }
    @ResponseBody
    @GetMapping("/songsList")
    List <Song> getAllSongs(){
        return  songRepository.findAll();
    }

}
