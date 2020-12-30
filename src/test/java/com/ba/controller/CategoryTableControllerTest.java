package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


import com.ba.builder.*;
import com.ba.dto.CategoryTableDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.CategoryTable;
import com.ba.entity.Media;
import com.ba.exception.BussinessRuleException;
import com.ba.service.CategoryTableService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTableControllerTest {

    @InjectMocks
    private CategoryTableController controller;

    @Mock
    private CategoryTableService service;
    List<CategoryTable> list = new ArrayList<>();
    List<CategoryTableDTO> dtoList = new ArrayList<>();
    MediaBuilder mediaBuilder = new MediaBuilder();
    Media media = mediaBuilder.name("name").id(1L).build();
    MediaDTOBuilder mediaDTOBuilder = new MediaDTOBuilder();
    MediaDTO mediaDTO = mediaDTOBuilder.id(1L).name("name").build();
    CategoryTableDTOBuilder categoryDTOBuilder = new CategoryTableDTOBuilder();
    CategoryTableBuilder categoryBuilder=new CategoryTableBuilder();
    CategoryTable categoryTable =categoryBuilder.tableAmount(15).description("balkon").media(media).id(1L).name("balkon").build();
    CategoryTableDTO categoryTableDTO = categoryDTOBuilder.description("balkon").mediaDTO(mediaDTO).tableAmount(15).id(1L).name("balkon").build();

    @Test
    public void addCategoryCategoryTableControllerTest(){
        categoryTableDTO.setId(null);
        when(service.addCategory(categoryTableDTO)).thenReturn("category eklendi");
        String result=controller.addCategory(categoryTableDTO);
        assertEquals(result,"category eklendi");

    }
    @Test
    public void getAllCategoryCategoryTableControllerTest(){
        dtoList.add(categoryTableDTO);
        Mockito.when(service.getAllCategory()).thenReturn(dtoList);
        List<CategoryTableDTO> result=controller.getAllCategory();
        assertEquals(result,dtoList);
    }
    @Test
    public void deleteCategoryCategoryTableControllerTest(){
        Long id=1L;
        Mockito.when(service.deleteCategory(id)).thenReturn("kisi silindi");
        String result=controller.deleteCategory(id);
        assertEquals(result,"category silindi");

    }
    @Test
    public void getCategoryByIdCategoryTableControllerTest(){
        Long id=1L;
        Mockito.when(service.getCategoryById(id)).thenReturn(categoryTableDTO);
        CategoryTableDTO result=controller.getCategoryById(id);
        assertEquals(result,categoryTableDTO);
    }
     @Test
    public void updateCategoryCategoryTableControllerTest(){
        when(service.updateCategory(categoryTableDTO)).thenReturn(categoryTableDTO);
        CategoryTableDTO result=controller.updateCategory(categoryTableDTO);
        assertEquals(result,categoryTableDTO);
     }
    @Test(expected = BussinessRuleException.class)
    public void addCategoryTableIdNullTest() {
        controller.addCategory(categoryTableDTO);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteCategoryTableIdNullTest() {
        controller.deleteCategory(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void deleteCategoryTableIdTest() {
        controller.deleteCategory(-1L);
    }


    @Test(expected = BussinessRuleException.class)
    public void getCategoryTableIdNullTest() {
        controller.getCategoryById(null);
    }

    @Test(expected = BussinessRuleException.class)
    public void getCategoryTableIdTest() {
        controller.getCategoryById(-1L);
    }

    @Test(expected = BussinessRuleException.class)
    public void updateCategoryTableNullIdTest() {
        categoryTableDTO.setId(-1L);
        controller.updateCategory(categoryTableDTO);
    }

}