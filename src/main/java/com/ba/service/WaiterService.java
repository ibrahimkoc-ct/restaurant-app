package com.ba.service;


import com.ba.converter.MediaDtoConventer;
import com.ba.converter.WaiterDtoConverter;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    WaiterRepository repository;

    @Autowired
    MediaRepository mediaRepository;

    public List<WaiterDTO> getAllWaiter(){
        List<Waiter> waiterList= repository.findAll();
        return WaiterDtoConverter.waiterDTOListToWaiter(waiterList);
    }
    public String deleteWaiter(Long id){
        repository.deleteById(WaiterDtoConverter.waiterDTOdeleteToWaiter(id));
        return "garson silindi";
    }
    public String addWaiterDTO(WaiterDTO waiterDTO){
        mediaRepository.delete(MediaDtoConventer.mediaDTOtoMedia(waiterDTO.getMediaDTO()));
        repository.save(WaiterDtoConverter.waiterDTOaddWaiter(waiterDTO));
        return "garson eklendi";
    }
    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        repository.saveAndFlush(WaiterDtoConverter.waiterDTOupdateWaiter(waiterDTO));
        return waiterDTO;
    }
    public WaiterDTO getWaiterById(Long id){
        Optional<Waiter> waiter= repository.findById(id);
        return WaiterDtoConverter.waiterDTOgetById(waiter);
    }
}
