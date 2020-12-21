package com.ba.service;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class MediaService {
    @Autowired
    MediaRepository repository;

    private static final String JPG_EXTENSION=".jpg";
    private static final String PNG_EXTENSION=".png";
    private static final String BMP_EXTENSION=".bmp";
    private static final String BMP_CONTENT=".image/bmp";
    private static final String PNG_CONTENT=".image/png";


    @Value("C:/Users/ibrahim/IdeaProjects/rest-api/target/media/")
    private static String uploadDir;

    public List<MediaDTO> getAllMedia(){
        List<Media> mediaList=repository.findAll();
        return MediaMapper.INSTANCE.toDTOList(mediaList);
    }
        public String addMedia(MultipartFile file,String imageName) throws IOException {
            String path2="C:/Users/ibrahim/IdeaProjects/rest-api/target/media/";
            Files.createDirectories(Paths.get(path2));
            String filePath=generateFullFilePath(file);
            Path targetLocation= FileSystems.getDefault().getPath(filePath);
            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
            byte[] bytes=file.getBytes();
            Media media= new Media();
            media.setName(imageName);
            media.setFileContent(bytes);
            repository.save(media);

        return "Media eklendi";
    }
    private static String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }
    private static String generateFullFilePath(MultipartFile file){
        String path2="C:/Users/ibrahim/IdeaProjects/rest-api/target/media/";
        String extension=JPG_EXTENSION;
        if(BMP_CONTENT.equals(file.getContentType())){
            extension=BMP_EXTENSION;
        }
        else if(PNG_CONTENT.equals(file.getContentType())){
            extension=PNG_EXTENSION;
        }
        return path2+generateUUID()+extension;
    }

}
