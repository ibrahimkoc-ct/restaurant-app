package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.ba.builder.WaiterBuilder;
import com.ba.builder.WaiterDTOBuilder;
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
    WaiterBuilder waiterBuilder=new WaiterBuilder();
    WaiterDTOBuilder waiterDTOBuilder= new WaiterDTOBuilder();
    Waiter waiter= waiterBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).urlToImage("no image").phoneNumber("132456").build();
    WaiterDTO waiterDTO= waiterDTOBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).urlToImage("no image").phoneNumber("132456").build();
    List<Waiter> waiterList= new ArrayList<>();
    List<WaiterDTO> waiterDTOList = new ArrayList<>();

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