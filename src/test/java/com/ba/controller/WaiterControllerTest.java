package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;
import com.ba.converter.WaiterDtoConverter;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.repository.WaiterRepository;
import com.ba.service.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {
    @InjectMocks
    private WaiterController controller;

    @Mock
    private WaiterService service;
    Waiter waiter= new Waiter();
    WaiterDTO waiterDTO= new WaiterDTO(1L,"ibrahim","1234"
            ,"ibrahim@hot","istanbul","no image",2500L);
    List<Waiter> waiterList= new ArrayList<>();
    List<WaiterDTO> waiterDTOList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        waiter.setId(1L);
        waiter.setSalary(2500L);
        waiter.setUrlToImage("no image");
        waiter.setPhoneNumber("1234");
        waiter.setAddress("istanbul");
        waiter.setMail("ibrahim@hot");
        waiter.setName("ibrahim");
    }
    @Test
    public void addWaiterDTOControllerTest(){
        when(service.addWaiterDTO(waiterDTO)).thenReturn("kisi eklendi");
        WaiterDTO result=controller.addWaiterDTO(waiterDTO);
        assertEquals(result,waiterDTO);
    }
    @Test
    public void getAllWaiterControllerTest(){
        when(service.getAllWaiter()).thenReturn(waiterDTOList);
        List<WaiterDTO> result= controller.getAllWaiter();
        assertEquals(result,waiterDTOList);
    }
    @Test
    public void deleteWaiterControllerTest(){
        Long id=1L;
        when(service.deleteWaiter(id)).thenReturn("garson silindi");
        String result=controller.deleteWaiter(id);
        assertEquals(result,"garson silindi");
    }
    @Test
    public void updateWaiterControllerTest(){
        when(service.updateWaiter(waiterDTO)).thenReturn(waiterDTO);
        WaiterDTO result=controller.updateWaiter(waiterDTO);
        assertEquals(result,waiterDTO);
    }
    @Test
    public void getWaiterById(){
        Long id=1L;
        when(service.getWaiterById(id)).thenReturn(waiterDTO);
        WaiterDTO result=controller.getWaiterById(id);
        assertEquals(result,waiterDTO);
    }


}