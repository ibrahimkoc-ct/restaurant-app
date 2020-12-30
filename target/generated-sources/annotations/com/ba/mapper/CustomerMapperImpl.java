package com.ba.mapper;

import com.ba.dto.CustomerDTO;
import com.ba.dto.MediaDTO;
import com.ba.entity.Customer;
import com.ba.entity.Media;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-30T00:30:13+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 15.0.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO toDTO(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setMediaDTO( mediaToMediaDTO( customer.getMedia() ) );
        customerDTO.setId( customer.getId() );
        customerDTO.setName( customer.getName() );
        customerDTO.setSurname( customer.getSurname() );
        customerDTO.setPhoneNumber( customer.getPhoneNumber() );
        customerDTO.setAddress( customer.getAddress() );

        return customerDTO;
    }

    @Override
    public Customer toEntity(CustomerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setMedia( mediaDTOToMedia( dto.getMediaDTO() ) );
        customer.setId( dto.getId() );
        customer.setName( dto.getName() );
        customer.setSurname( dto.getSurname() );
        customer.setPhoneNumber( dto.getPhoneNumber() );
        customer.setAddress( dto.getAddress() );

        return customer;
    }

    @Override
    public List<Customer> toEntityList(List<CustomerDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Customer> list = new ArrayList<Customer>( dtoList.size() );
        for ( CustomerDTO customerDTO : dtoList ) {
            list.add( toEntity( customerDTO ) );
        }

        return list;
    }

    @Override
    public List<CustomerDTO> toDTOList(List<Customer> list) {
        if ( list == null ) {
            return null;
        }

        List<CustomerDTO> list1 = new ArrayList<CustomerDTO>( list.size() );
        for ( Customer customer : list ) {
            list1.add( toDTO( customer ) );
        }

        return list1;
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
