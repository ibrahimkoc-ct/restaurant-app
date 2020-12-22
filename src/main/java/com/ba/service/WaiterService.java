package com.ba.service;


import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.WaiterMapper;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository repository;

    @Autowired
    private MediaRepository mediaRepository;



    public List<WaiterDTO> getAllWaiter() {
        List<Waiter> waiterList = repository.findAll();
        return WaiterMapper.INSTANCE.toDTOList(waiterList);
    }

    public String deleteWaiter(Long id) {
        repository.deleteById(id);
        return "garson silindi";
    }

    public String addWaiterDTO(WaiterDTO waiterDTO) {


        mediaRepository.delete(MediaMapper.INSTANCE.toEntity(waiterDTO.getMediaDTO()));

        Waiter waiter =WaiterMapper.INSTANCE.toEntity(waiterDTO);
        Media media = MediaMapper.INSTANCE.toEntity(waiterDTO.getMediaDTO());
        media.setId(null);
        waiter.setMedia(media);
        repository.save(waiter);
        return "garson eklendi";
    }

    public WaiterDTO updateWaiter(WaiterDTO waiterDTO) {
        repository.saveAndFlush(WaiterMapper.INSTANCE.toEntity(waiterDTO));
        return waiterDTO;
    }

    public WaiterDTO getWaiterById(Long id) {
        Waiter waiter = repository.findById(id).get();
        return WaiterMapper.INSTANCE.toDTO(waiter);
    }
}
