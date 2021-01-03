package com.ba.mapper;

import com.ba.dto.CategoryDTO;
import com.ba.dto.CustomerDTO;
import com.ba.dto.OrderDTO;
import com.ba.dto.OrderItemDTO;
import com.ba.dto.PaymentTypeDTO;
import com.ba.dto.ProductDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Category;
import com.ba.entity.Customer;
import com.ba.entity.Order;
import com.ba.entity.OrderItem;
import com.ba.entity.PaymentType;
import com.ba.entity.Product;
import com.ba.entity.Waiter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-01-03T20:10:48+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderItem toEntity(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setId( orderItemDTO.getId() );
        if ( orderItemDTO.getPiece() != null ) {
            orderItem.setPiece( orderItemDTO.getPiece().intValue() );
        }
        if ( orderItemDTO.getTotalPrice() != null ) {
            orderItem.setTotalPrice( orderItemDTO.getTotalPrice().intValue() );
        }
        orderItem.setSelectedtable( orderItemDTO.getSelectedtable() );
        orderItem.setProduct( productDTOToProduct( orderItemDTO.getProduct() ) );
        orderItem.setOrder( orderDTOToOrder( orderItemDTO.getOrder() ) );

        return orderItem;
    }

    @Override
    public OrderItemDTO toDTO(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        OrderItemDTO orderItemDTO = new OrderItemDTO();

        orderItemDTO.setId( orderItem.getId() );
        orderItemDTO.setPiece( (long) orderItem.getPiece() );
        orderItemDTO.setTotalPrice( (long) orderItem.getTotalPrice() );
        orderItemDTO.setSelectedtable( orderItem.getSelectedtable() );
        orderItemDTO.setProduct( productToProductDTO( orderItem.getProduct() ) );
        orderItemDTO.setOrder( orderToOrderDTO( orderItem.getOrder() ) );

        return orderItemDTO;
    }

    @Override
    public List<OrderItem> toEntityList(List<OrderItemDTO> orderItemDTOList) {
        if ( orderItemDTOList == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( orderItemDTOList.size() );
        for ( OrderItemDTO orderItemDTO : orderItemDTOList ) {
            list.add( toEntity( orderItemDTO ) );
        }

        return list;
    }

    @Override
    public List<OrderItemDTO> toDTOList(List<OrderItem> orderItemList) {
        if ( orderItemList == null ) {
            return null;
        }

        List<OrderItemDTO> list = new ArrayList<OrderItemDTO>( orderItemList.size() );
        for ( OrderItem orderItem : orderItemList ) {
            list.add( toDTO( orderItem ) );
        }

        return list;
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

    protected Customer customerDTOToCustomer(CustomerDTO customerDTO) {
        if ( customerDTO == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDTO.getId() );
        customer.setName( customerDTO.getName() );
        customer.setSurname( customerDTO.getSurname() );
        customer.setPhoneNumber( customerDTO.getPhoneNumber() );
        customer.setAddress( customerDTO.getAddress() );

        return customer;
    }

    protected Waiter waiterDTOToWaiter(WaiterDTO waiterDTO) {
        if ( waiterDTO == null ) {
            return null;
        }

        Waiter waiter = new Waiter();

        waiter.setId( waiterDTO.getId() );
        waiter.setName( waiterDTO.getName() );
        waiter.setPhoneNumber( waiterDTO.getPhoneNumber() );
        waiter.setMail( waiterDTO.getMail() );
        waiter.setAddress( waiterDTO.getAddress() );
        waiter.setSalary( waiterDTO.getSalary() );

        return waiter;
    }

    protected PaymentType paymentTypeDTOToPaymentType(PaymentTypeDTO paymentTypeDTO) {
        if ( paymentTypeDTO == null ) {
            return null;
        }

        PaymentType paymentType = new PaymentType();

        paymentType.setId( paymentTypeDTO.getId() );
        paymentType.setType( paymentTypeDTO.getType() );

        return paymentType;
    }

    protected Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setCustomer( customerDTOToCustomer( orderDTO.getCustomer() ) );
        order.setWaiter( waiterDTOToWaiter( orderDTO.getWaiter() ) );
        order.setType( paymentTypeDTOToPaymentType( orderDTO.getType() ) );
        order.setTotalAmount( orderDTO.getTotalAmount() );
        order.setTotalCount( orderDTO.getTotalCount() );
        order.setDate( orderDTO.getDate() );

        return order;
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

    protected PaymentTypeDTO paymentTypeToPaymentTypeDTO(PaymentType paymentType) {
        if ( paymentType == null ) {
            return null;
        }

        PaymentTypeDTO paymentTypeDTO = new PaymentTypeDTO();

        paymentTypeDTO.setId( paymentType.getId() );
        paymentTypeDTO.setType( paymentType.getType() );

        return paymentTypeDTO;
    }

    protected WaiterDTO waiterToWaiterDTO(Waiter waiter) {
        if ( waiter == null ) {
            return null;
        }

        WaiterDTO waiterDTO = new WaiterDTO();

        waiterDTO.setId( waiter.getId() );
        waiterDTO.setName( waiter.getName() );
        waiterDTO.setPhoneNumber( waiter.getPhoneNumber() );
        waiterDTO.setMail( waiter.getMail() );
        waiterDTO.setAddress( waiter.getAddress() );
        waiterDTO.setSalary( waiter.getSalary() );

        return waiterDTO;
    }

    protected CustomerDTO customerToCustomerDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setSurname( customer.getSurname() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setAddress( customer.getAddress() );

        return customerDTO;
    }

    protected OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setTotalAmount( order.getTotalAmount() );
        orderDTO.setTotalCount( order.getTotalCount() );
        orderDTO.setDate( order.getDate() );
        orderDTO.setType( paymentTypeToPaymentTypeDTO( order.getType() ) );
        orderDTO.setWaiter( waiterToWaiterDTO( order.getWaiter() ) );
        orderDTO.setCustomer( customerToCustomerDTO( order.getCustomer() ) );

        return orderDTO;
    }
}
