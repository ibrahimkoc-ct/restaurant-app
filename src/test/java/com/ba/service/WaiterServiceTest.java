package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.builder.WaiterBuilder;
import com.ba.builder.WaiterDTOBuilder;
import com.ba.converter.WaiterDtoConverter;
import com.ba.dto.MediaDTO;
import com.ba.dto.WaiterDTO;
import com.ba.entity.Media;
import com.ba.entity.Waiter;
import com.ba.repository.MediaRepository;
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

    @Mock
    private MediaRepository mediaRepository;

    WaiterBuilder waiterBuilder=new WaiterBuilder();
    WaiterDTOBuilder waiterDTOBuilder= new WaiterDTOBuilder();
    MediaBuilder mediaBuilder=new MediaBuilder();
    MediaDTOBuilder mediaDTOBuilder= new MediaDTOBuilder();
    MediaDTO mediaDTO=mediaDTOBuilder.id(1L).name("ibrahim").build();
    Media media=mediaBuilder.id(1L).name("ibrahim").build();
    Waiter waiter= waiterBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).urlToImage("no image").phoneNumber("132456").id(1L).media(media).build();
    WaiterDTO waiterDTO= waiterDTOBuilder.address("istanbul").mail("ibrahim@hot").name("ibrahim").salary(1500L).urlToImage("no image").id(1L).phoneNumber("132456").mediaDTO(mediaDTO).build();

    List<Waiter> waiterList= new ArrayList<>();
    List<WaiterDTO> waiterDTOList = new ArrayList<>();


    @Test
    public void getAllWaiterServiceTest(){
        waiterList.add(waiter);
        Mockito.when(repository.findAll()).thenReturn(waiterList);
        List<WaiterDTO> result=service.getAllWaiter();
        assertEquals(waiterList.get(0).getId(),result.get(0).getId());
    }
    @Test
    public void addWaiterDTOServiceTest(){
        Mockito.when(mediaRepository.save(any())).thenReturn(media);
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