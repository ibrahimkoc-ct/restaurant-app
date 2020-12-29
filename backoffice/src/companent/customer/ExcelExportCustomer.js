import React, {useEffect, useState} from 'react';
import CustomerService from "../../services/CustomerService";
import {Table} from "react-bootstrap";
import ReactHTMLTableToExcel from 'react-html-table-to-excel';


const ExcelExportCustomer = () => {

    const [listCustomer, setListCustomer] = useState([]);

    useEffect(() => {
        CustomerService.getCustomer().then((res) => {
            setListCustomer(res.data);
        })
    }, [])


    return (
        <div>
            <ReactHTMLTableToExcel
                id="test-table-xls-button"
                className="download-table-xls-button"
                table="table-to-xls"
                filename="tablexls"
                sheet="tablexls"
                buttonText="Download as XLS"/>
            <Table striped bordered hover id="table-to-xls">
                <thead>
                <tr>
                    <th>Musteri Id</th>
                    <th>Musteri Adı</th>
                    <th>Musteri Soyadı</th>
                    <th>Telefon Numarası</th>
                    <th>Musteri Fotografı</th>
                    <th>Musteri Adresi</th>
                    <th>Butonlar</th>
                </tr>
                </thead>
                <tbody>
                {
                    listCustomer.map((customer) => {
                            return (
                                <tr key={customer.id}>
                                    <td>{customer.id}</td>
                                    <td>{customer.name}</td>
                                    <td>{customer.surname}</td>
                                    <td>{customer.phoneNumber}</td>
                                    <td>{customer.address}</td>
                                    <td>

                                    </td>
                                </tr>)
                        }
                    )}
                </tbody>
            </Table>
        </div>
    );
}
export default ExcelExportCustomer();