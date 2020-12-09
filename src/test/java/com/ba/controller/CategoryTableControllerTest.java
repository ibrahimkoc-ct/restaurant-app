package com.ba.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


import com.ba.builder.*;
import com.ba.converter.CategoryTableDtoConverter;
import com.ba.dto.CategoryTableDTO;
import com.ba.dto.UsersDTO;
import com.ba.entity.Category;
import com.ba.entity.CategoryTable;
import com.ba.repository.CategoryTableRepository;
import com.ba.service.CategoryTableService;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTableControllerTest {

    @InjectMocks
    private CategoryTableController controller;

    @Mock
    private CategoryTableService service;
    List<CategoryTable> list = new ArrayList<>();
    List<CategoryTableDTO> dtoList = new ArrayList<>();
    CategoryTableDTOBuilder categoryDTOBuilder = new CategoryTableDTOBuilder();
    CategoryTableBuilder categoryBuilder=new CategoryTableBuilder();
    CategoryTable categoryTable =categoryBuilder.tableAmount(15).description("balkon").id(1L).imageToUrl("no image").name("balkon").build();
    CategoryTableDTO categoryTableDTO = categoryDTOBuilder.description("balkon").id(1L).imageToUrl("no image").name("balkon").build();

    @Test
    public void addCategoryCategoryTableControllerTest(){

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

}