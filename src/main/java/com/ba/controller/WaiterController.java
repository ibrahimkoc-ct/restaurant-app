package com.ba.controller;


import com.ba.dto.WaiterDTO;
import com.ba.service.WaiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService service;


    @PostMapping("/add")
    public WaiterDTO addWaiterDTO(@RequestBody WaiterDTO waiterDTO) {
        service.addWaiterDTO(waiterDTO);
        return waiterDTO;
    }

    @GetMapping("/list")
    public List<WaiterDTO> getAllWaiter() {
        return service.getAllWaiter();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWaiter(@PathVariable Long id) {
        service.deleteWaiter(id);
        return "garson silindi";

    }
    @PutMapping("/update")
    public WaiterDTO updateWaiter(@RequestBody WaiterDTO waiterDTO){
        service.updateWaiter(waiterDTO);
        return waiterDTO;
    }
    @GetMapping("/id/{id}")
    public WaiterDTO getWaiterById(@PathVariable Long id){
        return service.getWaiterById(id);
    }



}
