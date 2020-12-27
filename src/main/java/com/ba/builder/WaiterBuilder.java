package com.ba.builder;

import com.ba.entity.Media;
import com.ba.entity.Waiter;

public class WaiterBuilder extends IdBuilder {
    private String name;
    private String phoneNumber;
    private String mail;
    private String address;
    private Long salary;
    private Media media;

    public WaiterBuilder media(Media media) {
        this.media = media;
        return this;
    }
    public WaiterBuilder name(String name) {
        this.name = name;
        return this;
    }
    public WaiterBuilder phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public WaiterBuilder mail(String mail) {
        this.mail = mail;
        return this;
    }
    public WaiterBuilder address(String address) {
        this.address = address;
        return this;
    }

    public WaiterBuilder salary(Long salary) {
        this.salary = salary;
        return this;
    }
    public WaiterBuilder id(Long id){
        this.setId(id);
        return this;
    }
    @Override
    public Waiter build(){
        Waiter waiter= new Waiter();
        waiter.setSalary(this.salary);
        waiter.setPhoneNumber(this.phoneNumber);
        waiter.setId(this.getId());
        waiter.setName(this.name);
        waiter.setAddress(this.address);
        waiter.setMail(this.mail);
        waiter.setMedia(this.media);
        return waiter;

    }
}
