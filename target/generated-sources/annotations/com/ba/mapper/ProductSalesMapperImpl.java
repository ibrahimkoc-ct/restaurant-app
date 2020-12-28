package com.ba.mapper;

import com.ba.dto.ProductSalesDTO;
import com.ba.entity.ProductSales;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-29T02:09:09+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class ProductSalesMapperImpl implements ProductSalesMapper {

    @Override
    public ProductSalesDTO toDTO(ProductSales productSales) {
        if ( productSales == null ) {
            return null;
        }

        ProductSalesDTO productSalesDTO = new ProductSalesDTO();

        productSalesDTO.setProductId( productSales.getProductId() );
        productSalesDTO.setId( productSales.getId() );
        productSalesDTO.setPrice( productSales.getPrice() );
        productSalesDTO.setPiece( productSales.getPiece() );
        productSalesDTO.setTitle( productSales.getTitle() );
        productSalesDTO.setSelectedtable( productSales.getSelectedtable() );
        productSalesDTO.setCreateDate( productSales.getCreateDate() );
        productSalesDTO.setWaiterName( productSales.getWaiterName() );

        return productSalesDTO;
    }

    @Override
    public ProductSales toEntity(ProductSalesDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ProductSales productSales = new ProductSales();

        productSales.setId( dto.getId() );
        productSales.setProductId( dto.getProductId() );
        productSales.setPrice( dto.getPrice() );
        productSales.setPiece( dto.getPiece() );
        productSales.setTitle( dto.getTitle() );
        productSales.setSelectedtable( dto.getSelectedtable() );
        productSales.setWaiterName( dto.getWaiterName() );
        productSales.setCreateDate( dto.getCreateDate() );

        return productSales;
    }

    @Override
    public List<ProductSalesDTO> toDTOList(List<ProductSales> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductSalesDTO> list1 = new ArrayList<ProductSalesDTO>( list.size() );
        for ( ProductSales productSales : list ) {
            list1.add( toDTO( productSales ) );
        }

        return list1;
    }

    @Override
    public List<ProductSales> toEntityList(List<ProductSalesDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<ProductSales> list = new ArrayList<ProductSales>( dtoList.size() );
        for ( ProductSalesDTO productSalesDTO : dtoList ) {
            list.add( toEntity( productSalesDTO ) );
        }

        return list;
    }
}
