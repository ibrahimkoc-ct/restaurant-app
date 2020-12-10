package com.ba.converter;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;



public class MediaDtoConventer {
    private static final String JPG_EXTENSION=".jpg";
    private static final String PNG_EXTENSION=".png";
    private static final String BMP_EXTENSION=".bmp";

    private static final String BMP_CONTENT=".image/bmp";
    private static final String PNG_CONTENT=".image/png";


    @Value("C:/Users/ibrahim/IdeaProjects/rest-api/target/media/")
    private static String uploadDir;
    public static Media mediaDTOtoMedia(MediaDTO mediaDTO){
        Media media= new Media();
        media.setId(mediaDTO.getId());
        media.setName(media.getName());
        media.setFileContent(media.getFileContent());
        return media;
    }

    public static MediaDTO meidaTMediaDTO(Media media){
        MediaDTO mediaDTO= new MediaDTO();
        mediaDTO.setName(media.getName());
        mediaDTO.setId(media.getId());
        mediaDTO.setFileContent(media.getFileContent());
        return mediaDTO;
    }


    public static List<MediaDTO> mediaListToMediaDTOList(List<Media> mediaList){
        List<MediaDTO> mediaDTOList= new ArrayList<>();
        for(Media media:mediaList){
            MediaDTO dto= new MediaDTO();
            dto.setFileContent(media.getFileContent());
            dto.setId(media.getId());
            dto.setName(media.getName());
            mediaDTOList.add(dto);
        }
        return mediaDTOList;
    }
    public static Media addMedia(MultipartFile file,String imageName) throws IOException {
        String path2="C:/Users/ibrahim/IdeaProjects/rest-api/target/media/";
        Files.createDirectories(Paths.get(path2));
        String filePath=generateFullFilePath(file);
        Path targetLocation= FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(),targetLocation,StandardCopyOption.REPLACE_EXISTING);
        byte[] bytes=file.getBytes();
        Media media = new Media();
        media.setFileContent(bytes);
        media.setName(imageName);
        return media;
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
