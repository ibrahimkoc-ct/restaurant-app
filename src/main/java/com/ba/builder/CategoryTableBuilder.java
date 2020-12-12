package com.ba.builder;

import com.ba.entity.CategoryTable;

public class CategoryTableBuilder extends IdBuilder{
    private String name;
    private String description;
    private String imageToUrl;
    private int tableAmount;

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
    public CategoryTableBuilder imageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
        return this;
    }
    public CategoryTableBuilder tableAmount(int tableAmount){
        this.tableAmount=tableAmount;
        return this;

    }
    @Override
    public CategoryTable build(){
        CategoryTable categoryTable = new CategoryTable();
        categoryTable.setImageToUrl(this.imageToUrl);
        categoryTable.setTableAmount(this.tableAmount);
        categoryTable.setId(this.getId());
        categoryTable.setName(this.name);
        return categoryTable;
    }

}
