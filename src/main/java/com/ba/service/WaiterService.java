package com.ba.service;


import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.exception.BussinessRuleException;
import com.ba.exception.SystemException;
import com.ba.helper.UpdateHelper;
import com.ba.mapper.MediaMapper;
import com.ba.mapper.WaiterMapper;
import com.ba.repository.MediaRepository;
import com.ba.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository repository;

    public List<WaiterDTO> getAllWaiter() {
        List<Waiter> waiterList = repository.findAll();
        if (waiterList==null) {
            throw new SystemException("Waiter not found");
        }
        return WaiterMapper.INSTANCE.toDTOList(waiterList);
    }
    @Transactional
    public String deleteWaiter(Long id) {
            repository.deleteById(id);
            return "garson silindi";
    }
    @Transactional
    public String addWaiterDTO(WaiterDTO waiterDTO) {
        Waiter waiter = WaiterMapper.INSTANCE.toEntity(waiterDTO);
        Media media = MediaMapper.INSTANCE.toEntity(waiterDTO.getMediaDTO());
        media.setId(null);
        waiter.setMedia(media);
        repository.save(waiter);
        return "garson eklendi";
    }
    @Transactional
    public WaiterDTO updateWaiter(WaiterDTO waiterDTO) {
        Optional<Waiter> waiter = repository.findById(waiterDTO.getId());
        if (waiter==null) {
            throw new BussinessRuleException("Waiter not found in database");
        }
        UpdateHelper.updateWaiterHelper(waiterDTO, waiter);
        repository.saveAndFlush(waiter.get());
        return WaiterMapper.INSTANCE.toDTO(waiter.get());
    }

    public WaiterDTO getWaiterById(Long id) {
        Optional<Waiter> waiter = repository.findById(id);
        if (waiter==null) {
            throw new BussinessRuleException("Waiter not found in database");
        }
        return WaiterMapper.INSTANCE.toDTO(waiter.get());
    }
}
