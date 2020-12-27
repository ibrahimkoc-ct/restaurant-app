package com.ba.service;

import com.ba.builder.UserBuilder;
import com.ba.builder.UserDTOBuilder;
import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;
    UserBuilder userBuilder = new UserBuilder();
    User user = userBuilder.id(1L).email("ibrahimkoc@hotmail.com").enabled(true).password("12345").username("admin").build();
    UserDTOBuilder userDTOBuilder = new UserDTOBuilder();
    UserDTO dto = userDTOBuilder.id(1L).email("ibrahimkoc@hotmail.com").enabled(true).password("12345").username("admin").build();

    @Test
    public void addUserTest() {
        Mockito.when(repository.save(Mockito.any())).thenReturn("kisi eklendi");
        String result = service.addUser(dto);
        assertEquals(result, "kisi eklendi");
    }

//    @Test(expected = RuntimeException.class)
//    public void deleteUserTest() {
//        Long id = 1L;
//        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
//        String result = service.deleteUser(id);
//        assertEquals(result, "kisi silindi");
//    }

    @Test
    public void updateUserTest() {
        Mockito.when(repository.saveAndFlush(user)).thenReturn(user);
        UserDTO result = service.updateUser(dto);
        assertEquals(result, dto);
    }

    @Test
    public void getUserByIdTest() {
        Long id = 1L;
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(repository.findById(id)).thenReturn(optionalUser);
        UserDTO result = service.getUserById(id);
        assertEquals(result.getId(), id);
    }

    @Test
    public void getAllUserTest() {
        List<UserDTO> userList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userList.add(dto);
        users.add(user);
        Mockito.when(repository.findAll()).thenReturn(users);
        List<UserDTO> result = service.getAllUser();
        assertEquals(result.get(0).getId(), users.get(0).getId());
    }

}