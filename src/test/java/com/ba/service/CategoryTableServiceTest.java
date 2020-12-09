package com.ba.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


import com.ba.builder.CategoryTableBuilder;
import com.ba.builder.CategoryTableDTOBuilder;
import com.ba.converter.CategoryTableDtoConverter;
import com.ba.dto.CategoryTableDTO;
import com.ba.dto.UsersDTO;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CategoryTableServiceTest {

    @InjectMocks
    private CategoryTableService service;

    @Mock
    private CategoryTableRepository repository;
    List<CategoryTable> list = new ArrayList<>();
    List<CategoryTableDTO> dtoList = new ArrayList<>();
    CategoryTableDTOBuilder categoryDTOBuilder = new CategoryTableDTOBuilder();
    CategoryTableBuilder categoryBuilder=new CategoryTableBuilder();
    CategoryTable categoryTable =categoryBuilder.tableAmount(15).description("balkon").id(1L).imageToUrl("no image").name("balkon").build();
    CategoryTableDTO categoryTableDTO = categoryDTOBuilder.description("balkon").id(1L).imageToUrl("no image").name("balkon").build();


    @Test
    public void shouldAddNewCategoryTable() {
        Mockito.when(repository.save(any())).thenReturn(categoryTable);
        String result = service.addCategory(categoryTableDTO);
        assertEquals(result, "kisi eklendi");
    }

    @Test(expected = RuntimeException.class)
    public void shouldDeleteCategoryTableById() {
        Long id = 1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result = service.deleteCategory(id);
        assertEquals(result, "kisi silindi");
        verify(repository, times(1)).deleteById(id);

    }

    @Test
    public void shouldCategoryTableList() {
        list.add(categoryTable);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<CategoryTableDTO> dtoList = CategoryTableDtoConverter.categoryListToCategoryDTOList(list);
        List<CategoryTableDTO> dtoList1 = service.getAllCategory();
        assertEquals(dtoList1.get(0).getId(), dtoList.get(0).getId());
    }

    @Test
    public void shouldUpdateCategoryTable() {
        Mockito.when(repository.saveAndFlush(categoryTable)).thenReturn(categoryTable);
        CategoryTableDTO result = service.updateCategory(categoryTableDTO);
        assertEquals(result, categoryTableDTO);

    }

    @Test
    public void shouldGetCategoryById() {
        Long id = 1L;
        Optional<CategoryTable> dtolist1 = Optional.of(categoryTable);
        Mockito.when(repository.findById(id)).thenReturn(dtolist1);
        CategoryTableDTO result = service.getCategoryById(id);
        assertEquals(result.getId(), id);
    }
}