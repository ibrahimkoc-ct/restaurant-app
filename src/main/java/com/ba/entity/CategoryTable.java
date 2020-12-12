package com.ba.entity;


import javax.persistence.*;
@Entity

public class CategoryTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageToUrl;
    private int tableAmount;

    public CategoryTable(String name, String description, String imageToUrl,int tableAmount ) {
        this.name = name;
        this.description = description;
        this.imageToUrl = imageToUrl;
        this.tableAmount=tableAmount;
    }

    public int getTableAmount() {
        return tableAmount;
    }

    public void setTableAmount(int tableAmount) {
        this.tableAmount = tableAmount;
    }

    public CategoryTable() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageToUrl() {
        return imageToUrl;
    }

    public void setImageToUrl(String imageToUrl) {
        this.imageToUrl = imageToUrl;
    }


}
