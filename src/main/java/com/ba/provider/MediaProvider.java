package com.ba.provider;

import com.ba.entity.Media;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

public class MediaProvider {

    private static final String JPG_EXTENSION=".jpg";
    private static final String PNG_EXTENSION=".png";
    private static final String BMP_EXTENSION=".bmp";
    private static final String BMP_CONTENT=".image/bmp";
    private static final String PNG_CONTENT=".image/png";


    @Value("C:/Users/ibrahim/IdeaProjects/rest-api/target/media/")
    private static String uploadDir;
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
    public static Media addProvider(MultipartFile file, String imageName) throws IOException {
        String path2="C:/Users/ibrahim/IdeaProjects/rest-api/target/media/";
        Files.createDirectories(Paths.get(path2));
        String filePath=generateFullFilePath(file);
        Path targetLocation= FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);
        byte[] bytes=file.getBytes();
        Media media= new Media();
        media.setName(imageName);
        media.setFileContent(bytes);
        return media;
    }

}
