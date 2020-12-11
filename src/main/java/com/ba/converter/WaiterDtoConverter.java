package com.ba.converter;

import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
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
        Media media = new Media();
        media.setFileContent(waiterDTO.getMediaDTO().getFileContent());
        media.setName(waiterDTO.getMediaDTO().getName());
        waiter.setMedia(media);
        return waiter;
    }
    public static Waiter waiterDTOupdateWaiter(WaiterDTO waiterDTO){
        Waiter waiter= new Waiter();
        waiter.setId(waiterDTO.getId());
        waiter.setAddress(waiterDTO.getAddress());
        waiter.setMail(waiterDTO.getMail());
        waiter.setName(waiterDTO.getName());
        waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        waiter.setSalary(waiterDTO.getSalary());
        waiter.setUrlToImage(waiterDTO.getUrlToImage());
        Media media = new Media();
        media.setId(waiterDTO.getMediaDTO().getId());
        media.setFileContent(waiterDTO.getMediaDTO().getFileContent());
        media.setName(waiterDTO.getMediaDTO().getName());
        waiter.setMedia(media);
        return waiter;
    }
    public static WaiterDTO waiterDTOgetById(Optional<Waiter> optionalWaiter) {
        WaiterDTO waiterDTO = new WaiterDTO();
        waiterDTO.setAddress(optionalWaiter.get().getAddress());
        waiterDTO.setId(optionalWaiter.get().getId());
        waiterDTO.setMail(optionalWaiter.get().getMail());
        waiterDTO.setName(optionalWaiter.get().getName());
        waiterDTO.setPhoneNumber(optionalWaiter.get().getPhoneNumber());
        waiterDTO.setUrlToImage(optionalWaiter.get().getUrlToImage());
        waiterDTO.setSalary(optionalWaiter.get().getSalary());
        waiterDTO.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(optionalWaiter.get().getMedia()));


        return waiterDTO;

    }
    public static List<WaiterDTO> waiterDTOListToWaiter(List<Waiter> waiterList){
        List<WaiterDTO> waiterDTOList = new ArrayList<>();
        for(Waiter waiter:waiterList ){
            WaiterDTO dto= new WaiterDTO();
            dto.setSalary(waiter.getSalary());
            dto.setMail(waiter.getMail());
            dto.setId(waiter.getId());
            dto.setPhoneNumber(waiter.getPhoneNumber());
            dto.setAddress(waiter.getAddress());
            dto.setUrlToImage(waiter.getUrlToImage());
           dto.setName(waiter.getName());
           dto.setMediaDTO(MediaDtoConventer.meidaTMediaDTO(waiter.getMedia()));



            waiterDTOList.add(dto);
        }
        return waiterDTOList;
    }

}
