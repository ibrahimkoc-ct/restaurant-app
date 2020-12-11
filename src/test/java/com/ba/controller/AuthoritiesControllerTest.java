package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Temp.AuthoritiesController;
import Temp.AuthoritiesBuilder;
import Temp.AuthoritiesDTOBuilder;
import Temp.AuthoritiesDTO;
import Temp.Authorities;
import Temp.AuthoritiesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AuthoritiesControllerTest {

    @InjectMocks
    private AuthoritiesController controller;

    @Mock
    private AuthoritiesService service;

    private AuthoritiesDTOBuilder dtoBuilder = new AuthoritiesDTOBuilder();
    private AuthoritiesBuilder authoritiesBuilder = new AuthoritiesBuilder();
    AuthoritiesDTO dto=dtoBuilder.authority("ROLE_ADMIN").username("ibrahim").build();
    Authorities authorities=authoritiesBuilder.username("ibrahim").authority("ROLE_ADMIN").build();
   

    @Test
    public void deleteAuthControllerTest(){
        String username ="ibrahim";
        when(service.deleteAuth(username)).thenReturn("rol silindi");
        String result= controller.deleteAuth(username);
        assertEquals(result,"rol silindi");

    }
    @Test
    public void addAutControllerTest(){
        when(service.addAuth(dto)).thenReturn("rol eklendi");
        String result =controller.addAuth(dto);
        assertEquals(result,"rol eklendi");
    }
    @Test
    public void updateAuthControllerTest(){
        when(service.updateAuth(dto)).thenReturn(dto);
        AuthoritiesDTO authDto=controller.updateAuth(dto);
        assertEquals(authDto,dto);
    }
    @Test
    public void getAuhtByIdControllerTest(){
        String id="ibrahim";
        Mockito.when(service.getAuthById(id)).thenReturn(dto);
        AuthoritiesDTO authoritiesDTO=controller.AuthById(id);
        assertEquals(authoritiesDTO,dto);
    }
    @Test
    public void getAuthListControllerTest(){
        List<AuthoritiesDTO> authoritiesDTO = new ArrayList<>();
        authoritiesDTO.add(dto);
        when(service.authoritiesList()).thenReturn(authoritiesDTO);
        List<AuthoritiesDTO> result=controller.authoritiesList();
        assertEquals(result,authoritiesDTO);


    }



}