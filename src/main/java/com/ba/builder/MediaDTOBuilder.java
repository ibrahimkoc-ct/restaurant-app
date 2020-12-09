package com.ba.builder;

import com.ba.dto.MediaDTO;
import com.ba.entity.Media;

public class MediaDTOBuilder extends IdBuilder {
    private String name;
    private byte[] fileContent;

    public MediaDTOBuilder name(String name){
        this.name=name;
        return this;
    }
    public MediaDTOBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }
    public MediaDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public MediaDTO build(){
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setFileContent(this.fileContent);
        mediaDTO.setName(this.name);
        mediaDTO.setId(this.getId());
        return mediaDTO;
    }

}
