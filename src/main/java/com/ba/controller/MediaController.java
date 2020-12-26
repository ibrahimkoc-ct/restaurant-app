package com.ba.controller;

import com.ba.dto.MediaDTO;
import com.ba.exception.BussinessRuleException;
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

    @GetMapping()
    public List<MediaDTO> getAllMedia(){
        return service.getAllMedia();
    }
    @PostMapping()
    public String addFile(@RequestParam MultipartFile file,@RequestParam String imageName) throws IOException {
        if(file==null){
            throw new BussinessRuleException("File cannot be empty");
        }
        service.addMedia(file,imageName);
        return "Media eklendi";
    }
    @DeleteMapping("/{id}")
    public String deleteMedia(@PathVariable Long id){
        if(id==null){
            throw new BussinessRuleException("Id cannot be empty");
        }
        return service.deleteMedia(id);
    }


}