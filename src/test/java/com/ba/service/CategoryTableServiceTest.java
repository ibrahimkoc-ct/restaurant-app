package com.ba.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


import com.ba.dto.CategoryTableDTO;
import com.ba.entity.CategoryTable;
import com.ba.repository.CategoryTableRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTableServiceTest {

    @InjectMocks
    private CategoryTableService service;

    @Mock
    private CategoryTableRepository repository;
    List<CategoryTable> list= new ArrayList<>();
    List<CategoryTableDTO> dtoList = new ArrayList<>();
    CategoryTable categoryTable = new CategoryTable();
    CategoryTableDTO categoryTableDTO = new CategoryTableDTO();

    @Before
    public void setUp() throws Exception {
        categoryTable.setId(1L);
        categoryTable.setName("masa");
        categoryTable.setTableAmount(123);
        categoryTable.setImageToUrl("no image");
        categoryTableDTO.setId(1L);
        categoryTableDTO.setName("masa");
        categoryTableDTO.setTableAmount(123);
        categoryTableDTO.setImageToUrl("no image");
    }

    @Test
    public void shouldAddNewCategoryTable(){
       Mockito.when(repository.save(any())).thenReturn(categoryTable);
       String result=service.addCategory(categoryTableDTO);
        assertEquals(result,"kisi eklendi");
    }
    @Test(expected =RuntimeException.class)
    public void shouldDeleteCategoryTableById(){
        Long id=1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result=service.deleteCategory(id);
        assertEquals(result,"kisi silindi");
        verify(repository,times(1)).deleteById(id);
        

    }

}