//package com.ba.service;
//
//import com.ba.entity.Category;
//import com.ba.entity.CategoryTable;
//import com.ba.entity.Product;
//import com.ba.entity.RestaurantTable;
//import com.ba.repository.CategoryTableRepository;
//import com.ba.repository.RestaurantTableRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class RestaurantTableService {
//    @Autowired
//    RestaurantTableRepository repository;
//
//    @Autowired
//    CategoryTableRepository categoryTablerepository;
//
//
//    public List<RestaurantTable> getAllRestaurantTable() {
//        repository.findAll();
//        return repository.findAll();
//    }
//    public List<RestaurantTable> deleteRestaurantTable(Long id) {
//        repository.deleteById(id);
//        return repository.findAll();
//    }
//
//    public List<RestaurantTable> updateRestaurantTable(long id, RestaurantTable table) {
//        Optional<RestaurantTable> optionalArticle = repository.findAll().stream().filter(news1 -> news1.getId() == id).findAny();
//        if (optionalArticle == null) {
//            System.out.println("girilen ID ile haber bulunamadı!");
//            return repository.findAll();
//        }
//        optionalArticle.get().setDescription(table.getDescription());
//        optionalArticle.get().setTitle(table.getTitle());
//        repository.saveAndFlush(optionalArticle.get());
//        return repository.findAll();
//    }
//    public void addRestaurantId(RestaurantTable table,Long id){
//        Optional<CategoryTable> category =categoryTablerepository.findById(id);
//        category.get().getRestaurantTables().add(table);
//        categoryTablerepository.save(category.get());
//    }
//    public Optional<RestaurantTable> getTableById(Long id) {
//
//        return repository.findById(id);
//    }
//}
