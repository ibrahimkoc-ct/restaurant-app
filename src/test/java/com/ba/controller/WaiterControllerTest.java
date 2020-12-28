package com.ba.controller;

import com.ba.builder.WaiterBuilder;
import com.ba.builder.WaiterDTOBuilder;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Waiter;
import com.ba.exception.BussinessRuleException;
import com.ba.service.WaiterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {
    @InjectMocks
    private WaiterController controller;

    @Mock
    private WaiterService service;
    WaiterBuilder waiterBuilder = new WaiterBuilder();
    WaiterDTOBuilder waiterDTOBuilder = new WaiterDTOBuilder();
    Waiter waiter = waiterBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).phoneNumber("132456").build();
    WaiterDTO waiterDTO = waiterDTOBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).phoneNumber("132456").build();
    List<Waiter> waiterList = new ArrayList<>();
    List<WaiterDTO> waiterDTOList = new ArrayList<>();

    @Test
    public void addWaiterDTOControllerTest() {
        when(service.addWaiterDTO(waiterDTO)).thenReturn("kisi eklendi");
        WaiterDTO result = controller.addWaiterDTO(waiterDTO);
        assertEquals(result, waiterDTO);
    }

    @Test
    public void getAllWaiterControllerTest() {
        when(service.getAllWaiter()).thenReturn(waiterDTOList);
        List<WaiterDTO> result = controller.getAllWaiter();
        assertEquals(result, waiterDTOList);
    }

    @Test
    public void deleteWaiterControllerTest() {
        Long id = 1L;
        when(service.deleteWaiter(id)).thenReturn("garson silindi");
        String result = controller.deleteWaiter(id);
        assertEquals(result, "garson silindi");
    }

    @Test
    public void updateWaiterControllerTest() {
        waiterDTO.setId(1L);
        when(service.updateWaiter(waiterDTO)).thenReturn(waiterDTO);
        WaiterDTO result = controller.updateWaiter(waiterDTO);
        assertEquals(result, waiterDTO);
    }

    @Test
    public void getWaiterById() {
        Long id = 1L;
        when(service.getWaiterById(id)).thenReturn(waiterDTO);
        WaiterDTO result = controller.getWaiterById(id);
        assertEquals(result, waiterDTO);
    }
    @Test(expected = BussinessRuleException.class)
    public void addWaiterIdNullTest() {
        waiterDTO.setId(1L);
        controller.addWaiterDTO(waiterDTO);
    }

    @Test(expected = BussinessRuleException.class)
    public void addWaiterNullTest() {
        controller.addWaiterDTO(null);
    }
    @Test(expected = BussinessRuleException.class)
    public void deleteWaiterIdNullTest() {
        controller.deleteWaiter(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteWaiterIdTest() {
        controller.deleteWaiter(-1L);
    }
    @Test(expected = BussinessRuleException.class)
    public void getWaiterIdNullTest() {
        controller.getWaiterById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getWaiterIdTest() {
        controller.getWaiterById(-1L);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateWaiterNullTest() {
        controller.updateWaiter(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateWaiterIdNullTest() {
        waiterDTO.setId(null);
        controller.updateWaiter(waiterDTO);
    }
}