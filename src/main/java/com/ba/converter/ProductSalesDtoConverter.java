package com.ba.converter;

import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;

import java.util.ArrayList;
import java.util.List;

public class ProductSalesDtoConverter {
    public static List<ProductSalesDTO> orderListTOOrderDTOList(List<ProductSales> productlist){
        List<ProductSalesDTO> orderListDTO = new ArrayList<>();

        for (ProductSales productSalesListItem : productlist) {
            ProductSalesDTO salesDTO = new ProductSalesDTO();
            salesDTO.setId(productSalesListItem.getId());
            salesDTO.setTitle(productSalesListItem.getTitle());
            salesDTO.setCreateDate(productSalesListItem.getCreateDate());
            salesDTO.setOrderId(productSalesListItem.getOrderId());
            salesDTO.setPiece(productSalesListItem.getPiece());
            salesDTO.setPrice(productSalesListItem.getPrice());
            salesDTO.setSelectedtable(productSalesListItem.getSelectedtable());
            orderListDTO.add(salesDTO);
        }

        return orderListDTO;
    }
    public static  List<ProductSales> addProductSalesDTOlist(List<ProductSalesDTO> productSalesDTO){
        List<ProductSales> dtoList = new ArrayList<>();
        for (int i = 0; i < productSalesDTO.size(); i++) {
            ProductSales product = new ProductSales();
            product.setId(productSalesDTO.get(i).getId());
            product.setTitle(productSalesDTO.get(i).getTitle());
            product.setCreateDate(productSalesDTO.get(i).getCreateDate());
            product.setOrderId(productSalesDTO.get(i).getOrderId());
            product.setPiece(productSalesDTO.get(i).getPiece());
            product.setPrice(productSalesDTO.get(i).getPrice());
            product.setSelectedtable(productSalesDTO.get(i).getSelectedtable());
            dtoList.add(product);

        }
        return dtoList;
    }
    
}
