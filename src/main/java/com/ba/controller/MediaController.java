package com.ba.controller;

import com.ba.dto.MediaDTO;
import com.ba.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/file")
public class MediaController {


    @Autowired
    private MediaService service;

    @GetMapping("/list")
    public List<MediaDTO> getAllMedia(){
        return service.getAllMedia();
    }
    @PostMapping("/add")
    public String addFile(@RequestParam MultipartFile file,@RequestParam String imageName) throws IOException {
        service.addMedia(file,imageName);
        return "Media eklendi";
    }


}