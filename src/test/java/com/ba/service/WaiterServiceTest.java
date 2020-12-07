package com.ba.service;

import com.ba.converter.WaiterDtoConverter;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.repository.WaiterRepository;
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

@RunWith(MockitoJUnitRunner.class)
public class WaiterServiceTest {
    @InjectMocks
    private WaiterService service;

    @Mock
    private WaiterRepository repository;

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
    public void getAllWaiterServiceTest(){
        waiterList.add(waiter);
        Mockito.when(repository.findAll()).thenReturn(waiterList);
        List<WaiterDTO> dto= WaiterDtoConverter.waiterDTOListToWaiter(waiterList);
        List<WaiterDTO> result=service.getAllWaiter();
        assertEquals(dto.get(0).getId(),result.get(0).getId());
    }
    @Test
    public void addWaiterDTOServiceTest(){
        Mockito.when(repository.save(any())).thenReturn(waiter);
        String result=service.addWaiterDTO(waiterDTO);
        assertEquals(result,"garson eklendi");
    }
    @Test
    public void updateWaiterDTOServiceTest(){
        Mockito.when(repository.saveAndFlush(waiter)).thenReturn(waiter);
        WaiterDTO result=service.updateWaiter(waiterDTO);
        assertEquals (result,waiterDTO);
    }
    @Test(expected =RuntimeException.class)
    public void deleteWaiterDTOServiceTest(){
        Long id=1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteWaiter(id);
        assertEquals(result,"garson silindi");
    }
    @Test
    public void getWaiterByIdServiceTest(){
        Long id=1L;
        Optional<Waiter> optionalWaiter=Optional.of(waiter);
        Mockito.when(repository.findById(id)).thenReturn(optionalWaiter);
        WaiterDTO result=service.getWaiterById(id);
        assertEquals(result.getId(),id);
    }
}