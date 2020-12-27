package com.ba.controller;

import com.ba.builder.UserBuilder;
import com.ba.builder.UserDTOBuilder;
import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserContollerTest {
    @InjectMocks
    private UserContoller contoller;

    @Mock
    private UserService service;
    UserBuilder userBuilder = new UserBuilder();
    User user = userBuilder.id(1L).email("ibrahimkoc@hotmail.com").enabled(true).password("12345").username("admin").build();
    UserDTOBuilder userDTOBuilder = new UserDTOBuilder();
    UserDTO dto = userDTOBuilder.id(1L).email("ibrahimkoc@hotmail.com").enabled(true).password("12345").username("admin").build();
    List<UserDTO> userDTOList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @Test
    public void addUserDTOControllerTest() {
        Mockito.when(service.addUser(dto)).thenReturn("kisi eklendi");
        String result = contoller.addUser(dto);
        assertEquals(result, "kisi eklendi");
    }

//    @Test
//    public void deleteDTOControllerTest() {
//        Long id = 1L;
//        Mockito.when(service.deleteUser(id)).thenReturn("kisi silindi");
//        String result = contoller.deleteUser(id);
//        assertEquals(result, "kisi silindi");
//    }

    @Test
    public void getAllUserControllerTest() {
        userDTOList.add(dto);
        userList.add(user);
        Mockito.when(service.getAllUser()).thenReturn(userDTOList);
        List<UserDTO> result = contoller.getAllUser();
        assertEquals(result.get(0).getId(), userDTOList.get(0).getId());
    }

    @Test
    public void updateUserControllerTest() {
        Mockito.when(service.updateUser(dto)).thenReturn(dto);
        UserDTO result = contoller.updateUser(dto);
        assertEquals(result, dto);
    }

    @Test
    public void getUserByIdControllerTest() {
        Long id = 1L;
        Mockito.when(service.getUserById(id)).thenReturn(dto);
        UserDTO result = contoller.getUserById(id);
        assertEquals(result, dto);
    }

    @Test
    public void loadAdminCheckControllerTest() {
        String result = contoller.loginAdminCheck();
        assertEquals(result, "giris basarılı");
    }

    @Test
    public void loadUserCheckControllerTest() {
        String result = contoller.loginUserCheck();
        assertEquals(result, "giris basarılı");
    }


}