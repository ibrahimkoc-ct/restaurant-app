package com.ba.service;

import com.ba.dto.CreditCardDTO;
import com.ba.dto.OrderWrapper;
import com.ba.dto.OrderandCreditDTO;
import com.ba.entity.*;
import com.ba.mapper.CreditCardMapper;
import com.ba.mapper.OrderItemMapper;
import com.ba.mapper.OrderMapper;
import com.ba.mapper.PaymentTypeMapper;
import com.ba.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    @Autowired
    private CreditCardMapper creditCardMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public OrderandCreditDTO addOrderandCreditCard(OrderandCreditDTO orderandCreditDTO){
        CreditCardDTO creditCardDTO=orderandCreditDTO.getCreditCardDTO();
        List<OrderWrapper> orderWrapperList=orderandCreditDTO.getOrderWrapperList();
        Long PaymentId =orderandCreditDTO.getPaymentId();
        List<OrderItem> orderItemList= new ArrayList<>();


        if(PaymentId==2){
            creditCardRepository.save(creditCardMapper.toEntity(creditCardDTO));
        }

        orderandCreditDTO.getOrderWrapperList().forEach(list->{
            OrderItem orderItem = new OrderItem();
            orderItem.setPiece(list.getPiece());
            orderItem.setProduct(productRepository.findById(list.getProductId()).get());
            orderItem.setSelectedtable(list.getSelectedtable());
            orderItem.setTotalPrice(list.getPrice()*list.getPiece());
            orderItemList.add(orderItem);
          });

        Order order = addOrder(orderWrapperList, PaymentId, orderItemList);
        orderItemList.forEach(orderItem -> {
                orderItem.setOrder(order);
                orderItemRepository.save(orderItem);
            });


        return orderandCreditDTO;
    }

    public Order addOrder(List<OrderWrapper> orderWrapperList, Long paymentId, List<OrderItem> orderItemList) {
        Order order = new Order();
        if(orderWrapperList.get(0).getCustomerId()!=null){
            Optional<Customer> optionalCustomer=customerRepository.findById(orderWrapperList.get(0).getCustomerId());
            if(optionalCustomer.isPresent()){
                order.setCustomer(optionalCustomer.get());
            }
        }
        if(orderWrapperList.get(0).getWaiterId()!=null){
            Optional<Waiter> optionalWaiter =waiterRepository.findById(orderWrapperList.get(0).getWaiterId());
            if(optionalWaiter.isPresent()){
                order.setWaiter(optionalWaiter.get());
            }
        }
        Optional<PaymentType> paymentType=paymentTypeRepository.findById(paymentId);
        if(paymentType.isPresent()){
            order.setType(paymentType.get());
        }


        int totalAmount = orderItemList.stream().mapToInt(OrderItem::getTotalPrice).sum();
        int totalCount = orderItemList.stream().mapToInt(OrderItem::getPiece).sum();
        order.setTotalCount(totalCount);
        order.setTotalAmount(totalAmount);
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

}
