package com.ba.controller;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.service.MediaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MediaControllerTest {
    @InjectMocks
    private MediaController controller;

    @Mock
    private MediaService service;
    MediaBuilder mediaBuilder = new MediaBuilder();
    MediaDTOBuilder mediaDTOBuilder = new MediaDTOBuilder();
    byte[] b = {(byte) 0xe0, 0x4f, (byte) 0xd0,
            0x20, (byte) 0xea, 0x3a, 0x69, 0x10, (byte) 0xa2, (byte) 0xd8, 0x08, 0x00, 0x2b,
            0x30, 0x30, (byte) 0x9d};
    Media media = mediaBuilder.id(1L).name("image").fileContent(b).build();
    MediaDTO mediaDTO = mediaDTOBuilder.id(1L).name("image").fileContent(b).build();

    @Test
    public void getMediaListControllerTest() {
        List<MediaDTO> mediaDTOList = new ArrayList<>();
        mediaDTOList.add(mediaDTO);
        Mockito.when(service.getAllMedia()).thenReturn(mediaDTOList);
        List<MediaDTO> result = controller.getAllMedia();
        assertEquals(result, mediaDTOList);
    }

    @Test
    public void addMediaListControllerTest() throws IOException {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };

        Mockito.when(service.addMedia(file, "image")).thenReturn("Media eklendi");
        String result = controller.addFile(file, "image");
        assertEquals(result, "Media eklendi");


    }

}





