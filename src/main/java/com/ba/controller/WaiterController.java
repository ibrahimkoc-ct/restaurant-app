package com.ba.controller;


import com.ba.dto.WaiterDTO;
import com.ba.exception.BussinessRuleException;
import com.ba.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService service;


    @PostMapping("/add")
    public WaiterDTO addWaiterDTO(@Valid @RequestBody WaiterDTO waiterDTO) {
        if ( waiterDTO.getId() != null) {
            throw new BussinessRuleException("id cannot be empty");
        }
        service.addWaiterDTO(waiterDTO);
        return waiterDTO;
    }

    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter() {
        return service.getAllWaiter();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWaiter(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("id cannot be empty");
        }
        service.deleteWaiter(id);
        return "garson silindi";

    }

    @PutMapping("/update")
    public WaiterDTO updateWaiter(@Valid @RequestBody WaiterDTO waiterDTO) {
        if (waiterDTO.getId() == null) {
            throw new BussinessRuleException("Waiter cannot be empty!");
        }
        service.updateWaiter(waiterDTO);
        return waiterDTO;
    }

    @GetMapping("/id/{id}")
    public WaiterDTO getWaiterById(@PathVariable Long id) {
        if (id == null || id < 0) {
            throw new BussinessRuleException("Waiter cannot be empty!");
        }
        return service.getWaiterById(id);
    }

}
