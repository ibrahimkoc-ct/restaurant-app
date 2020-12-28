package com.ba.builder;

import com.ba.entity.CategoryTable;
import com.ba.entity.Media;

public class CategoryTableBuilder extends IdBuilder{
    private String name;
    private String description;
    private int tableAmount;
    private Media media;

    public CategoryTableBuilder media(Media media) {
        this.media = media;
        return this;

    }

    public CategoryTableBuilder name(String name) {
        this.name = name;
        return this;

    }
    public CategoryTableBuilder id(Long id){
        this.setId(id);
        return this;
    }
    public CategoryTableBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CategoryTableBuilder tableAmount(int tableAmount){
        this.tableAmount=tableAmount;
        return this;

    }
    @Override
    public CategoryTable build(){
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setDescription(this.description);
        categoryTable.setTableAmount(this.tableAmount);
        categoryTable.setId(this.getId());
        categoryTable.setName(this.name);
        categoryTable.setMedia(media);
        return categoryTable;
    }

}
