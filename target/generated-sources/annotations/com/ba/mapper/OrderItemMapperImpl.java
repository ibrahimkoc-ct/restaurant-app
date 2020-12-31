package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.OrderItemDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.OrderItem;
import com.ba.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T01:38:16+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItemDTO toDtO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId( orderItem.getId() );
        orderItemDTO.setPiece( orderItem.getPiece() );
        orderItemDTO.setTotalPrice( orderItem.getTotalPrice() );
        orderItemDTO.setSelectedtable( orderItem.getSelectedtable() );
        orderItemDTO.setProduct( productToProductDTO( orderItem.getProduct() ) );
        orderItemDTO.setOrder( orderItem.getOrder() );

        return orderItemDTO;
    }

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setId( orderItemDTO.getId() );
        orderItem.setPiece( orderItemDTO.getPiece() );
        orderItem.setTotalPrice( orderItemDTO.getTotalPrice() );
        orderItem.setSelectedtable( orderItemDTO.getSelectedtable() );
        orderItem.setProduct( productDTOToProduct( orderItemDTO.getProduct() ) );
        orderItem.setOrder( orderItemDTO.getOrder() );

        return orderItem;
    }

    @Override
    public List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDTO.size() );
        for ( OrderItemDTO orderItemDTO1 : orderItemDTO ) {
            list.add( toEntity( orderItemDTO1 ) );
        }

        return list;
    }

    @Override
    public List<OrderItemDTO> toDTOList(List<OrderItem> orderItems) {
        if ( orderItems == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orderItems.size() );
        for ( OrderItem orderItem : orderItems ) {
            list.add( toDtO( orderItem ) );
        }

        return list;
    }

    protected List<ProductDTO> productListToProductDTOList(List<Product> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDTO> list1 = new ArrayList<ProductDTO>( list.size() );
        for ( Product product : list ) {
            list1.add( productToProductDTO( product ) );
        }

        return list1;
    }

    protected CategoryDTO categoryToCategoryDTO(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId( category.getId() );
        categoryDTO.setName( category.getName() );
        categoryDTO.setDescription( category.getDescription() );
        categoryDTO.setProducts( productListToProductDTOList( category.getProducts() ) );

        return categoryDTO;
    }

    protected List<CategoryDTO> categoryListToCategoryDTOList(List<Category> list) {
        if ( list == null ) {
            return null;
        }

        List<CategoryDTO> list1 = new ArrayList<CategoryDTO>( list.size() );
        for ( Category category : list ) {
            list1.add( categoryToCategoryDTO( category ) );
        }

        return list1;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setTitle( product.getTitle() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setCategories( categoryListToCategoryDTOList( product.getCategories() ) );

        return productDTO;
    }

    protected List<Product> productDTOListToProductList(List<ProductDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Product> list1 = new ArrayList<Product>( list.size() );
        for ( ProductDTO productDTO : list ) {
            list1.add( productDTOToProduct( productDTO ) );
        }

        return list1;
    }

    protected Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        if ( categoryDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDTO.getId() );
        category.setName( categoryDTO.getName() );
        category.setDescription( categoryDTO.getDescription() );
        category.setProducts( productDTOListToProductList( categoryDTO.getProducts() ) );

        return category;
    }

    protected List<Category> categoryDTOListToCategoryList(List<CategoryDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Category> list1 = new ArrayList<Category>( list.size() );
        for ( CategoryDTO categoryDTO : list ) {
            list1.add( categoryDTOToCategory( categoryDTO ) );
        }

        return list1;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setTitle( productDTO.getTitle() );
        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );
        product.setCategories( categoryDTOListToCategoryList( productDTO.getCategories() ) );

        return product;
    }
}
