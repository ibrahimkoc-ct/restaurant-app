package com.ba.service;

import com.ba.builder.MediaBuilder;
import com.ba.builder.MediaDTOBuilder;
import com.ba.dto.MediaDTO;
import com.ba.entity.Media;
import com.ba.mapper.MediaMapper;
import com.ba.repository.MediaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class MediaServiceTest {
    @InjectMocks
    private MediaService service;

    @Mock

    private MediaRepository repository;

    MediaBuilder mediaBuilder = new MediaBuilder();
    MediaDTOBuilder mediaDTOBuilder = new MediaDTOBuilder();
    byte[] b = {(byte) 0xe0, 0x4f, (byte) 0xd0,
            0x20, (byte) 0xea, 0x3a, 0x69, 0x10, (byte) 0xa2, (byte) 0xd8, 0x08, 0x00, 0x2b,
            0x30, 0x30, (byte) 0x9d};
    Media media = mediaBuilder.id(1L).name("image").fileContent(b).build();
    MediaDTO mediaDTO = mediaDTOBuilder.id(1L).name("image").fileContent(b).build();


    byte[] json = "{\"name\":\"yeeeah\"}".getBytes(StandardCharsets.UTF_8);
    MockMultipartFile jsonPart = new MockMultipartFile("json", "json", "application/json", json);

    @Test
    public void getAllMediaServiceTest() {

        List<Media> mediaList = new ArrayList<>();

        mediaList.add(media);
        Mockito.when(repository.findAll()).thenReturn(mediaList);
        List<MediaDTO> dto = MediaMapper.INSTANCE.toDTOList(mediaList);
        List<MediaDTO> result = service.getAllMedia();
        assertEquals(dto.get(0).getId(), result.get(0).getId());
    }

    @Test
    public void addMediaServiceTest() throws IOException {

        Mockito.when(repository.save(Mockito.any())).thenReturn(media);
        String result = service.addMedia(jsonPart, "indir (1).jpg");
        assertEquals(result, "Media eklendi");
    }

}