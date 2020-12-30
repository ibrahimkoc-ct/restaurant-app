package com.ba.service;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.exception.BussinessRuleException;
import com.ba.provider.MediaProvider;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    MediaRepository repository;

    public List<MediaDTO> getAllMedia() {
        List<Media> mediaList = repository.findAll();
        if(mediaList==null){
            throw new BussinessRuleException("Media not found");
        }
        return MediaMapper.INSTANCE.toDTOList(mediaList);
    }
    @Transactional
    public String addMedia(MultipartFile file, String imageName) throws IOException {
        if(file==null){
            throw new BussinessRuleException("Media not found");
        }
        repository.save(MediaProvider.addProvider(file, imageName));
        return "Media eklendi";
    }
    @Transactional
    public String deleteMedia(Long id){
        repository.deleteById(id);
        return "media silindi";
    }

}
