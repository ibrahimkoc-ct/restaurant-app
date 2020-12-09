package com.ba.converter;

import com.ba.dto.ProductDTO;
import com.ba.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDtoConverter {

    public static List<ProductDTO> clientListToClienDTOList(List<Product> clientList) {
        List<ProductDTO> dto = new ArrayList<>();
        for (Product product : clientList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setCategory(product.getCategory());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setTitle(product.getTitle());
            productDTO.setUrlToImage(product.getUrlToImage());
            dto.add(productDTO);
        }
        return dto;
    }

    public static ProductDTO clientDTOgetById(Optional<Product> dtoList) {
        ProductDTO dto = new ProductDTO();
        dto.setPrice(dtoList.get().getPrice());
        dto.setTitle(dtoList.get().getTitle());
        dto.setUrlToImage(dtoList.get().getUrlToImage());
        dto.setDescription(dtoList.get().getDescription());
        dto.setId(dtoList.get().getId());
        dto.setCategory(dtoList.get().getCategory());
        dto.setCategories(dtoList.get().getCategories());

        return dto;
    }
    public static String clientDTOdeleteProduct(String categoryName){
        Product product= new Product();
        product.setCategory(categoryName);
        return product.getCategory();
    }
}
