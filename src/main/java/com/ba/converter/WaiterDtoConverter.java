package com.ba.converter;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaiterDtoConverter {

    public static Long waiterDTOdeleteToWaiter(Long id){
        Waiter waiter= new Waiter();
        waiter.setId(id);
        return waiter.getId();
    }
    public static Waiter waiterDTOaddWaiter(WaiterDTO waiterDTO){
        Waiter waiter= new Waiter();
        waiter.setId(waiterDTO.getId());
        waiter.setAddress(waiterDTO.getAddress());
        waiter.setMail(waiterDTO.getMail());
        waiter.setName(waiterDTO.getName());
        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        waiter.setSalary(waiterDTO.getSalary());
        waiter.setUrlToImage(waiterDTO.getUrlToImage());
        return waiter;
    }
    public static WaiterDTO waiterDTOgetById(Optional<Waiter> optionalWaiter) {
        WaiterDTO waiterDTO = new WaiterDTO(
                optionalWaiter.get().getId(),
                optionalWaiter.get().getName()
                , optionalWaiter.get().getPhoneNumber()
                , optionalWaiter.get().getMail(),
                optionalWaiter.get().getAddress()
                , optionalWaiter.get().getUrlToImage()
                , optionalWaiter.get().getSalary());
        return waiterDTO;

    }
    public static List<WaiterDTO> waiterDTOListToWaiter(List<Waiter> waiterList){
        List<WaiterDTO> waiterDTOList = new ArrayList<>();
        for(Waiter waiter:waiterList ){
            WaiterDTO dto= new WaiterDTO(
                    waiter.getId(),
                    waiter.getName()
                    , waiter.getPhoneNumber()
                    , waiter.getMail(),
                    waiter.getAddress()
                    , waiter.getUrlToImage()
                    , waiter.getSalary()
            );
            waiterDTOList.add(dto);
        }
        return waiterDTOList;
    }

}
