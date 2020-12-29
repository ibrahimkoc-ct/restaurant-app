package com.ba.service;

import com.ba.builder.*;
import com.ba.dto.CategoryDTO;
import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Category;
import com.ba.entity.Media;
import com.ba.entity.Product;
import com.ba.exception.SystemException;
import com.ba.mapper.CategoryMapper;
import com.ba.mapper.ProductMapper;
import com.ba.repository.CategoryRepository;
import com.ba.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @InjectMocks
    private ProductService service;
    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private CategoryRepository categoryRepository;

    List<Product> list = new ArrayList<>();
    List<ProductDTO> dtoList = new ArrayList<>();
    List<CategoryDTO> categoryDTO = new ArrayList<>();
    List<Category> categoryList = new ArrayList<>();
    Set<Product> productSet = new HashSet<>();
    MediaBuilder mediaBuilder = new MediaBuilder();
    Media media = mediaBuilder.name("name").id(1L).build();
    MediaDTOBuilder mediaDTOBuilder = new MediaDTOBuilder();
    MediaDTO mediaDTO = mediaDTOBuilder.id(1L).name("name").build();
    CategoryBuilder categoryBuilder = new CategoryBuilder();
    CategoryDTOBuilder categoryDTOBuilder = new CategoryDTOBuilder();
    Category category = categoryBuilder.id(1L).description("pizza").name("Pizza").media(media).build();
    ProductBuilder productBuilder = new ProductBuilder();
    Product product = productBuilder.description("pizza").id(1L).media(media).price("15").title("Pizza").categories(categoryList).build();
    ProductDTOBuilder productDTOBuilder = new ProductDTOBuilder();
    ProductDTO productDTO = productDTOBuilder.mediaDTO(mediaDTO).description("pizza").id(1L).price("15").title("Pizza").build();
    CategoryDTO dto = categoryDTOBuilder.id(1L).description("pizza").name("Pizza").mediaDTO(mediaDTO).build();

    @Test(expected = RuntimeException.class)
    public void shouldDeleteProductById() {
        Long id = 1L;
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String result = service.deleteProduct(id);
        assertEquals(result, "kisi silindi");
        verify(repository, times(1)).deleteById(id);

    }

    @Test
    public void shouldBackOfficeProductList() {
        list.add(product);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<ProductDTO> dto = ProductMapper.INSTANCE.toDTOList(list);
        List<ProductDTO> productsList = service.getAllProduct();
        assertNotNull(dto);
        assertNotNull(productsList);
        assertEquals(dto.get(0).getId(), productsList.get(0).getId());

    }

    @Test
    public void shouldUpdateBackofficeProductBy() {
        Long id = 1L;
        categoryList.add(category);
        product.setCategories(categoryList);
        categoryDTO.add(dto);
        productDTO.setCategories(categoryDTO);
        dto.setProducts(dtoList);
        category.setProducts(list);
        Optional<Category> optionalCategory = Optional.of(category);
        Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.when(repository.findById(id)).thenReturn(optionalProduct);
        Mockito.when(repository.save(product)).thenReturn(product);
        ProductDTO result = service.updateProduct(1L, productDTO);
        assertEquals(result, productDTO);
    }

    @Test
    public void shouldGetProductByID() {
        Optional<Product> list = Optional.of(product);
        Mockito.when(repository.findById(1L)).thenReturn(list);
        ProductDTO dto = service.getProductById(1L);
        assertNotNull(dto);
        assertEquals(dto.getId(), list.get().getId());
    }

    @Test
    public void shouldProductadd() {
        Long id = 1L;
        productSet.add(product);
        category.setProducts(list);
        categoryDTO.add(dto);
        productDTO.setCategories(categoryDTO);
        Optional<Category> optionalCategory = Optional.of(category);
        Mockito.when(categoryRepository.findById(id)).thenReturn(optionalCategory);
        Mockito.when(categoryRepository.save(any())).thenReturn(category);
        when(categoryMapper.toEntityList(productDTO.getCategories())).thenReturn(product.getCategories());
        String result = service.addProductId(productDTO);
        assertEquals(result, "kisi eklendi");

    }
    @Test
    public void getPageCustomerTest(){
        list.add(product);
        Pageable pageable= PageRequest.of(1,10);
        Page<Product> page =new PageImpl<>(list);
        Mockito.when(repository.findAll(pageable)).thenReturn(page);
        Page<ProductDTO> result = service.getPageProduct(1,10);
        assertNotNull(result);
    }
    @Test
    public void getSliceCustomerTest(){
        list.add(product);
        Pageable pageable= PageRequest.of(1,10);
        Slice<Product> slice =new SliceImpl<>(list);
        Mockito.when(repository.findProductByCategoriesId(1L,pageable)).thenReturn(slice);
        Slice<ProductDTO> result =service.loadMoreProduct(1L,1,10);
        assertNotNull(result);
    }
    @Test(expected = SystemException.class)
    public void getAllProductListNull(){
        when(repository.findAll()).thenReturn(null);
        service.getAllProduct();
    }
    @Test(expected = SystemException.class)
    public void getProductByIdOptionalNull(){
        when(repository.findById(1L)).thenReturn(null);
        service.getProductById(1L);
    }
    @Test
    public void deleteProductById(){
        Optional<Product> list = Optional.of(product);
        Mockito.when(repository.findById(1L)).thenReturn(list);
        String result =service.deleteProduct(1L);
        assertEquals(result,"kisi silindi");
    }

}