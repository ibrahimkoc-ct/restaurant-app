package com.ba.builder;

import com.ba.entity.Media;

public class MediaBuilder extends IdBuilder {
    private String name;
    private byte[] fileContent;


    public MediaBuilder name(String name){
        this.name=name;
        return this;
    }
    public MediaBuilder fileContent(byte[] fileContent){
        this.fileContent=fileContent;
        return this;
    }

    public Media build(){
        Media media = new Media();
        media.setFileContent(this.fileContent);
        media.setName(this.name);
        media.setId(this.getId());
        return media;
    }
    public MediaBuilder id(Long id){
         this.setId(id);
         return this;
    }

}
