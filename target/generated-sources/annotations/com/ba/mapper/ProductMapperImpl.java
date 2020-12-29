package com.ba.mapper;

import com.ba.dto.MediaDTO;
import com.ba.dto.ProductDTO;
import com.ba.entity.Media;
import com.ba.entity.Product;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-29T02:37:08+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    private final CategoryMapper categoryMapper = Mappers.getMapper( CategoryMapper.class );

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setMediaDTO( mediaToMediaDTO( product.getMedia() ) );
        productDTO.setId( product.getId() );
        productDTO.setTitle( product.getTitle() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setCategories( categoryMapper.toDTOList( product.getCategories() ) );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setMedia( mediaDTOToMedia( dto.getMediaDTO() ) );
        product.setId( dto.getId() );
        product.setTitle( dto.getTitle() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setCategories( categoryMapper.toEntityList( dto.getCategories() ) );

        return product;
    }

    @Override
    public List<ProductDTO> toDTOList(List<Product> productList) {
        if ( productList == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( productList.size() );
        for ( Product product : productList ) {
            list.add( toDTO( product ) );
        }

        return list;
    }

    @Override
    public List<Product> toEntity(List<ProductDTO> productDTOList) {
        if ( productDTOList == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDTOList.size() );
        for ( ProductDTO productDTO : productDTOList ) {
            list.add( toEntity( productDTO ) );
        }

        return list;
    }

    protected MediaDTO mediaToMediaDTO(Media media) {
        if ( media == null ) {
            return null;
        }

        MediaDTO mediaDTO = new MediaDTO();

        mediaDTO.setId( media.getId() );
        mediaDTO.setName( media.getName() );
        byte[] fileContent = media.getFileContent();
        if ( fileContent != null ) {
            mediaDTO.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return mediaDTO;
    }

    protected Media mediaDTOToMedia(MediaDTO mediaDTO) {
        if ( mediaDTO == null ) {
            return null;
        }

        Media media = new Media();

        media.setId( mediaDTO.getId() );
        media.setName( mediaDTO.getName() );
        byte[] fileContent = mediaDTO.getFileContent();
        if ( fileContent != null ) {
            media.setFileContent( Arrays.copyOf( fileContent, fileContent.length ) );
        }

        return media;
    }
}
