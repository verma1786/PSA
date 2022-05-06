package com.state.fms.controller;

import java.io.IOException;
import java.util.Base64;

import com.state.fms.model.Photo;
import com.state.fms.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    /*
    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image", Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }*/

    @GetMapping("/photos/{id}")
    public String getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        return Base64.getEncoder().encodeToString(photo.getImage().getData());
    }

    @GetMapping("/photos/upload")
    public String uploadPhoto() {
        return "uploadPhoto";
    }

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("title") String title, @RequestParam("image") MultipartFile image) throws IOException {
        String id = photoService.addPhoto(title, image);
        return id;
    }
}