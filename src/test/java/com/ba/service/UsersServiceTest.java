package com.ba.service;

import com.ba.builder.UsersBuilder;
import com.ba.builder.UsersDTOBuilder;
import com.ba.dto.AuthoritiesDTO;
import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.repository.UsersRepository;

import org.h2.engine.User;
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
import java.util.function.Supplier;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;
    UsersBuilder usersBuilder= new UsersBuilder();
    UsersDTOBuilder usersDTOBuilder=new UsersDTOBuilder();
    private Users users = usersBuilder.enabled(true).password("12345").username("ibrahim").build();
    private UsersDTO usersDTO = usersDTOBuilder.enabled(true).password("12345").username("ibrahim").build();



    @Test
    public void shouldAddNewUsers() {
        Mockito.when(usersRepository.save(any())).thenReturn(users);
        String res = usersService.addUsers(usersDTO);
        assertNotNull(res);
        assertEquals(res,"kisi eklendi");
    }

    @Test(expected =RuntimeException.class)
    public void shouldDeleteUsersById() {
        String id="ibrahim";
        doThrow(new RuntimeException("Cant delete here")).when(usersRepository).deleteById(id);
        String result=usersService.deleteUsers(id);
        assertNotNull(result);
        assertEquals(result,"kisi silindi");
        verify(usersRepository,times(1)).deleteById(id);
    }
    @Test
    public void shouldUpdatUsersBy(){
       Mockito.when(usersRepository.saveAndFlush(users)).thenReturn(users);
       UsersDTO result=usersService.updateUsers(usersDTO);
       assertNotNull(result);
       assertEquals(result, usersDTO);

    }
    @Test
    public void shouldGetUserById(){
        String id="ibrahim";
        Optional<Users> dtolist1 =Optional.of(users);
        Mockito.when(usersRepository.findById(id)).thenReturn(dtolist1);
        UsersDTO result =usersService.getUsersById(id);
        assertEquals(result.getUsername(),id);
        assertNotNull(result);
    }
    @Test
    public void shouldUsersList(){
        List<Users> users1 = new ArrayList<>();
        List<UsersDTO> user2= new ArrayList<>();
        users1.add(users);
        Mockito.when(usersRepository.findAll()).thenReturn(users1);
        List<UsersDTO> list= usersService.listUsers();
        assertNotNull(list);
        assertNotNull(users1);
    }


}