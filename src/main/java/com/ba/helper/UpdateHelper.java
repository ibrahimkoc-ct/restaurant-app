package com.ba.helper;

import com.ba.dto.*;
import com.ba.entity.*;
import com.ba.mapper.MediaMapper;

import java.util.Optional;

public class UpdateHelper {
    public static Category categoryUpdateHelper(Category category, CategoryDTO categoryDTO) {
        if (!category.getDescription().equals(categoryDTO.getDescription())) {
            category.setDescription(categoryDTO.getDescription());
        }
        if (!category.getName().equals(categoryDTO.getName())) {
            category.setName(categoryDTO.getName());
        }
        Media media = MediaMapper.INSTANCE.toEntity(categoryDTO.getMediaDTO());
        if (!category.getMedia().equals(media)) {
            category.setMedia(media);
        }
        return category;
    }

    public static void updateCustomerHelper(CustomerDTO dto, Optional<Customer> optionalCustomer) {
        if (!optionalCustomer.get().getAddress().equals(dto.getAddress())) {
            optionalCustomer.get().setAddress(dto.getAddress());
        }
        if (!optionalCustomer.get().getName().equals(dto.getName())) {
            optionalCustomer.get().setName(dto.getName());
        }
        if (!optionalCustomer.get().getSurname().equals(dto.getSurname())) {
            optionalCustomer.get().setSurname(dto.getSurname());
        }
        if (!optionalCustomer.get().getPhoneNumber().equals(dto.getPhoneNumber())) {
            optionalCustomer.get().setPhoneNumber(dto.getPhoneNumber());
        }
        if(!optionalCustomer.get().getMedia().equals(MediaMapper.INSTANCE.toEntity(dto.getMediaDTO()))){
            optionalCustomer.get().setMedia(MediaMapper.INSTANCE.toEntity(dto.getMediaDTO()));
        }
    }
    public static void updateCategoryTableHelper(CategoryTableDTO category, Optional<CategoryTable> categoryTableOptional) {
        if(!categoryTableOptional.get().getName().equals(category.getName())){
            categoryTableOptional.get().setName(category.getName());
        }
        if(!categoryTableOptional.get().getDescription().equals(category.getDescription())){
            categoryTableOptional.get().setDescription(category.getDescription());
        }
        if(categoryTableOptional.get().getTableAmount()!= category.getTableAmount()){
            categoryTableOptional.get().setTableAmount(category.getTableAmount());
        }
        if(!categoryTableOptional.get().getMedia().equals(MediaMapper.INSTANCE.toEntity(category.getMediaDTO()))){
            categoryTableOptional.get().setMedia(MediaMapper.INSTANCE.toEntity(category.getMediaDTO()));
        }
    }
    public static void updateWaiterHelper(WaiterDTO waiterDTO, Optional<Waiter> waiter) {
        if(!waiter.get().getAddress().equals(waiterDTO.getAddress())){
            waiter.get().setAddress(waiterDTO.getAddress());
        }
        if(!waiter.get().getMail().equals(waiterDTO.getMail())){
            waiter.get().setSalary(waiterDTO.getSalary());
        }
        if (!waiter.get().getPhoneNumber().equals(waiterDTO.getPhoneNumber())){
            waiter.get().setPhoneNumber(waiterDTO.getPhoneNumber());
        }
        if(!waiter.get().getMedia().equals(MediaMapper.INSTANCE.toEntity(waiterDTO.getMediaDTO()))){
            waiter.get().setMedia(MediaMapper.INSTANCE.toEntity(waiterDTO.getMediaDTO()));
        }
        if(!waiter.get().getName().equals(waiterDTO.getName())){
            waiter.get().setName(waiterDTO.getName());
        }
    }
    public static void updateRoleHelper(RoleDTO role, Optional<Role> optionalRole) {
        if(!optionalRole.get().getName().equals(role.getName())){
            optionalRole.get().setName(role.getName());
        }
    }

}
