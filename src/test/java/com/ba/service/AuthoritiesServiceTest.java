package com.ba.service;


import com.ba.dto.AuthoritiesDTO;
import com.ba.entity.Authorities;
import com.ba.repository.AuthoritiesRepository;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.doThrow;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class AuthoritiesServiceTest {

    @InjectMocks
    private AuthoritiesService authoritiesService;

    @Mock
    private AuthoritiesRepository authoritiesRepository;
    private Authorities authorities = new Authorities();
    private AuthoritiesDTO dto = new AuthoritiesDTO();

    @Before
    public void setUp() throws Exception {
        authorities.setUsername("ibrahim");
        authorities.setAuthority("ROLE_USER");
        dto.setAuthority("ROLE_USER");
        dto.setUsername("ibrahim");

    }

    @Test
    public void shouldAddNewAuthorities(){
        Mockito.when(authoritiesRepository.save(any())).thenReturn(authorities);
        String result=authoritiesService.addAuth(dto);
        assertNotNull(result);
        assertEquals(result,"Ekleme başarı ile tamamlandı");

    }

    @Test(expected =RuntimeException.class)
    public void shouldDeleteAuthoritiesById(){
       String id="ibrahim";
       doThrow(new RuntimeException("Cant delete here")).when(authoritiesRepository).deleteById(id);
       String result = authoritiesService.deleteAuth(id);
       assertNotNull(result);
       assertEquals(result,"kisi silindi");
       verify(authoritiesRepository,times(1)).deleteById(id);

    }

    @Test
    public void shouldUpdateAuthoritiesByAuth(){
        Mockito.when(authoritiesRepository.saveAndFlush(authorities)).thenReturn(authorities);
        AuthoritiesDTO result=authoritiesService.updateAuth(dto);
        assertNotNull(result);
        assertEquals(result,dto);
    }
    @Test
    public void shouldGetAuthById(){
        String id="ibrahim";
        Optional<Authorities> dtoList1 = Optional.of(authorities);
        Mockito.when(authoritiesRepository.findById(id)).thenReturn(dtoList1);
        AuthoritiesDTO result=authoritiesService.getAuthById(id);
        assertNotNull(result);
        assertEquals(result.getUsername(),id);


    }

    @Test
    public void shouldauthoritiesList(){
        List<Authorities> authorities1 = new ArrayList<>();
        authorities1.add(authorities);
        Mockito.when(authoritiesRepository.findAll()).thenReturn(authorities1);
        List<AuthoritiesDTO> list=authoritiesService.authoritiesList();
        assertNotNull(list);
        assertNotNull(authorities1);
    }
}