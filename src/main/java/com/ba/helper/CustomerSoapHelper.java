package com.ba.helper;

import com.ba.dto.CustomerDTO;
import okhttp3.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerSoapHelper {

    public static void deleteSoapCustomer(Long id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\">\r\n   " +
                "<soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:deleteCustomer>\r\n         <ser:id>"+id+"</ser:id>\r\n      </ser:deleteCustomer>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/CustomerServerSoap/services/CustomerService.CustomerServiceHttpEndpoint/deleteCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        Response response = client.newCall(request).execute();
    }
    public static void addSoapCustomer(CustomerDTO customerDTO) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\r\n  " +
                " <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:addCustomer>\r\n         <ser:dto>\r\n            <xsd:address>"+customerDTO.getAddress()+"</xsd:address>\r\n           " +
                " <xsd:name>"+customerDTO.getName()+"</xsd:name>\r\n            <xsd:phoneNumber>"+customerDTO.getPhoneNumber()+"</xsd:phoneNumber>\r\n            <xsd:surname>"+customerDTO.getSurname()+"</xsd:surname>\r\n         </ser:dto>\r\n     " +
                " </ser:addCustomer>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/CustomerServerSoap/services/CustomerService.CustomerServiceHttpEndpoint/addCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        Response response = client.newCall(request).execute();
    }
    public static void updateSoapCustomer(CustomerDTO customerDTO) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\" xmlns:xsd=\"http://dto.ba.com/xsd\">\r\n  " +
                " <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:updateCustomer>\r\n         <ser:dto>\r\n            <xsd:address>"+customerDTO.getAddress()+"</xsd:address>\r\n            <xsd:id>"+customerDTO.getId()+"</xsd:id>\r\n    " +
                "        <xsd:name>"+customerDTO.getName()+"</xsd:name>\r\n            <xsd:phoneNumber>"+customerDTO.getPhoneNumber()+"</xsd:phoneNumber>\r\n            <xsd:surname>"+customerDTO.getSurname()+"</xsd:surname>\r\n         </ser:dto>\r\n     " +
                " </ser:updateCustomer>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/CustomerServerSoap/services/CustomerService.CustomerServiceHttpEndpoint/updateCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        Response response = client.newCall(request).execute();
    }
    public static List<CustomerDTO> getAllCustomer() throws IOException, JAXBException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/xml");
        RequestBody body = RequestBody.create(mediaType, "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.ba.com\">\r\n   <soapenv:Header/>\r\n   <soapenv:Body>\r\n      <ser:getAllCustomer/>\r\n   </soapenv:Body>\r\n</soapenv:Envelope>");
        Request request = new Request.Builder()
                .url("http://localhost:8081/CustomerServerSoap/services/CustomerService.CustomerServiceHttpEndpoint/getAllCustomer")
                .method("POST", body)
                .addHeader("Content-Type", "text/xml")
                .build();
        Response response = client.newCall(request).execute();

        return customerXmlParse(response.body().string());
    }





    public static List<CustomerDTO> customerXmlParse(String xml) {
        List<CustomerDTO> customers = new ArrayList<>();
        String firstKey = "<ns:getAllCustomerResponse xmlns:ns=\"http://service.ba.com\" xmlns:ax27=\"http://model.ba.com/xsd\" xmlns:ax25=\"http://dto.ba.com/xsd\">";
        String endKey = "</ns:getAllCustomerResponse>";
        int firstIndex = xml.indexOf(firstKey) + firstKey.length();
        int endIndex = xml.indexOf(endKey, firstIndex);
        xml = xml.substring(firstIndex, endIndex);
        firstKey = "<ns:return xsi:type=\"ax27:Customer\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">";
        firstIndex = xml.indexOf(firstKey) + firstKey.length() + 1;

        endIndex=0;

        while (firstIndex > -1) {
            CustomerDTO customer = new CustomerDTO();
            firstKey="<ax27:address>";
            endKey="</ax27:address>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customer.setAddress(xml.substring(firstIndex,endIndex));

            firstKey="<ax27:id>";
            endKey="</ax27:id>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customer.setId(Long.valueOf((xml.substring(firstIndex,endIndex))));

            firstKey="<ax27:name>";
            endKey="</ax27:name>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customer.setName(xml.substring(firstIndex,endIndex));

            firstKey="<ax27:phoneNumber>";
            endKey="</ax27:phoneNumber>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customer.setPhoneNumber(xml.substring(firstIndex,endIndex));

            firstKey="<ax27:surname>";
            endKey="</ax27:surname>";
            firstIndex=xml.indexOf(firstKey,endIndex)+firstKey.length();
            endIndex=xml.indexOf(endKey,firstIndex);
            customer.setSurname(xml.substring(firstIndex,endIndex));
            customers.add(customer);
            firstKey = "<ns:return xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ax27:Customer\">";
            firstIndex = xml.indexOf(firstKey,endIndex);
            if(firstIndex>-1){
                firstIndex+=firstKey.length();
            }

        }
        return customers;
    }
}
