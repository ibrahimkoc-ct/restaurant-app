package com.ba.builder;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;

public class WaiterDTOBuilder extends IdBuilder {
    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private String urlToImage;
    private Long salary;

    public WaiterDTOBuilder id(Long id){
        this.setId(id);
        return this;
    }

    public WaiterDTOBuilder name(String name) {
        this.name = name;
        return this;
    }
    public WaiterDTOBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public WaiterDTOBuilder mail(String mail) {
        this.mail = mail;
        return this;
    }
    public WaiterDTOBuilder address(String address) {
        this.address = address;
        return this;
    }
    public WaiterDTOBuilder urlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
        return this;
    }
    public WaiterDTOBuilder salary(Long salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public WaiterDTO build(){
        WaiterDTO waiterDTO= new WaiterDTO();
        waiterDTO.setUrlToImage(this.urlToImage);
        waiterDTO.setSalary(this.salary);
        waiterDTO.setPhoneNumber(this.phoneNumber);
        waiterDTO.setId(this.getId());
        waiterDTO.setName(this.name);
        waiterDTO.setAddress(this.address);
        waiterDTO.setMail(this.mail);
        return waiterDTO;

    }
}
