package com.ba.service;

import com.ba.converter.MediaDtoConventer;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    MediaRepository repository;

    public List<MediaDTO> getAllMedia(){
        List<Media> mediaList=repository.findAll();
        return MediaDtoConventer.mediaListToMediaDTOList(mediaList);
    }
        public String addMedia(MultipartFile file,String imageName) throws IOException {
            Media media= MediaDtoConventer.addMedia(file,imageName);
            repository.save(media);

        return "Media eklendi";
    }
}
