package org.ms.customer.service;

import org.ms.customer.dto.CustomerDto;
import org.ms.customer.dto.response.ResponseCustomerDto;

import java.util.List;

public interface CustomerService {
    public List<ResponseCustomerDto> findAllCustomers();

    public  ResponseCustomerDto findCustomerById(Long id);

    public ResponseCustomerDto createCustomer(CustomerDto customerDto);

    public ResponseCustomerDto updateCustomer(Long id, CustomerDto updateCustomerDto);

    public boolean deleteCustomer(Long id);

}
