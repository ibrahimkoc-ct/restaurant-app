package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;

import Temp.UsersController;
import Temp.UsersBuilder;
import Temp.UsersDTOBuilder;
import Temp.UsersDTO;
import Temp.Users;
import Temp.UsersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

    @InjectMocks
    private UsersController controller;

    @Mock
    private UsersService service;
    UsersBuilder usersBuilder= new UsersBuilder();
    UsersDTOBuilder usersDTOBuilder=new UsersDTOBuilder();
    private Users users = usersBuilder.enabled(true).password("12345").username("ibrahim").build();
    private UsersDTO usersDTO = usersDTOBuilder.enabled(true).password("12345").username("ibrahim").build();

    @Test
    public void deleteUserControllerTest(){
        String id="ibrahim";
        Mockito.when(service.deleteUsers(id)).thenReturn("kisi silindi");
        String result=controller.deleteUsers(id);
        assertEquals(result,"kisi silindi");
    }
    @Test
    public void addUsersControllerTest(){
        Mockito.when(service.addUsers(usersDTO)).thenReturn("kisi eklendi");
        String result=controller.addUsers(usersDTO);
        assertEquals(result,"kisi eklendi");
    }
    @Test
    public void updateUserControllerTest(){
        Mockito.when(service.updateUsers(usersDTO)).thenReturn(usersDTO);
        UsersDTO result =controller.updateUsers(usersDTO);
        assertEquals(result,usersDTO);
    }
    @Test
    public void getUsersByIdControllerlTest(){
        String id="ibrahim";
        Mockito.when(service.getUsersById(id)).thenReturn(usersDTO);
        UsersDTO result=controller.getUsersById(id);
        assertEquals(result,usersDTO);
    }
    @Test
    public void getUsersListControllerTest(){
        List<UsersDTO> usersDTOList =new ArrayList<>();
        usersDTOList.add(usersDTO);
        Mockito.when(service.listUsers()).thenReturn(usersDTOList);
        List<UsersDTO> result=controller.listUsers();
        assertEquals(result,usersDTOList);
    }

}