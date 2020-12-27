package com.ba.builder;

import com.ba.dto.CustomerDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;

public class CustomerBuilder extends IdBuilder {
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private Media media;

    public CustomerBuilder id(Long id) {
        this.setId(id);
        return this;
    }

    public CustomerBuilder name(String name) {
        this.name = name;
        return this;
    }
    public CustomerBuilder media(Media media) {
        this.media = media;
        return this;
    }

    public CustomerBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public CustomerBuilder address(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public Customer build() {
        Customer customer = new Customer(name,surname,phoneNumber,address,media);
        return customer;
    }
}
