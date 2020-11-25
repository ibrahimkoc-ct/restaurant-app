package com.ba.controller;

import com.ba.entity.Product;
import com.ba.entity.RestaurantTable;
import com.ba.service.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/restauranttable")
public class RestaurantTableController {
    @Autowired
    RestaurantTableService repository;


    @PostMapping("/add/{id}")
    public void addProduct(@RequestBody RestaurantTable table, @PathVariable Long id) {
         repository.addRestaurantId(table, id);
    }
    @GetMapping("/list")
    public List<RestaurantTable> getAllTable() {
        return repository.getAllRestaurantTable();
    }
    @DeleteMapping("/delete/{id}")
    public List<RestaurantTable> deleteTable(@PathVariable Long id) {
        return repository.deleteRestaurantTable(id);
    }
    @PutMapping("/update/{id}")
    public List<RestaurantTable> updateTable(@PathVariable long id, @RequestBody RestaurantTable table) {
        return repository.updateRestaurantTable(id, table);
    }
    @GetMapping("/id/{id}")
    public Optional<RestaurantTable> getTableById(@PathVariable Long id){
        return repository.getTableById(id);    }

}
