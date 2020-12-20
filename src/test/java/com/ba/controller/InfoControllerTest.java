package com.ba.controller;

import com.ba.entity.ServerInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class InfoControllerTest {


    @InjectMocks
    private ServerInfo info;
    List<ServerInfo> server_info = new ArrayList<>();

    @Mock
    private InfoController controller;


    @Before
    public void setUp() throws Exception {
        ServerInfo info = new ServerInfo("123","456");
        server_info.add(info);

    }


    @Test
    public void getInfoInfoControllerTest(){
        Mockito.when(controller.getInfo()).thenReturn(server_info);
        List<ServerInfo> result=controller.getInfo();
        assertEquals(result,server_info);
    }

}