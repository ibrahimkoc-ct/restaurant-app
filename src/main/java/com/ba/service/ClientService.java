package com.ba.service;

import com.ba.dto.ProductDTO;
import com.ba.dto.ProductSliceWrapperDTO;
import com.ba.entity.Product;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ProductRepository repository;
    public List<ProductDTO> getAllProduct(){
        List<Product> clientList = repository.findAll();
        return ProductMapper.INSTANCE.toDTOList(clientList);
    }

    public ProductDTO getProductById(Long id){
        Product dto=repository.findById(id).get();
        return ProductMapper.INSTANCE.toDTO(dto);
    }

    public List<ProductDTO> listSelectedCategory(String categoryName){
       repository.findCategoryByName(categoryName);
       return getAllProduct();
    }
    public ProductSliceWrapperDTO loadMoreProduct(Long id, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Slice<Product> productSlice=repository.findProductByCategoriesId(id,pageable);
        List<Product> productList=productSlice.toList();
        List<ProductDTO> productDTOList = ProductMapper.INSTANCE.toDTOList(productList);
//        for (int i = 0; i < productList.size(); i++) {
//            productDTOList.get(i).setCategories(CategoryMapper.INSTANCE.toDTOList(productList.get(i).getCategories()));
//        }
        ProductSliceWrapperDTO sliceWrapperDTO= new ProductSliceWrapperDTO();
        sliceWrapperDTO.setListProductDto(productDTOList);
        sliceWrapperDTO.setHasNext(productSlice.hasNext());
        return sliceWrapperDTO;

    }


}
