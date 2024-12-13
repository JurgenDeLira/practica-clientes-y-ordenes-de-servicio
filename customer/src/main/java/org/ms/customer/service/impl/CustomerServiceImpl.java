package org.ms.customer.service.impl;

import io.quarkus.security.Authenticated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.ms.customer.client.UserClient;
import org.ms.customer.dto.CustomerDto;
import org.ms.customer.dto.response.ResponseCustomerDto;
import org.ms.customer.entity.Customer;
import org.ms.customer.repository.CustomerRepository;
import org.ms.customer.service.CustomerService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ApplicationScoped
@Authenticated
public class CustomerServiceImpl implements CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    @RestClient
    UserClient userClient;

    public ResponseCustomerDto toResponseCustomerDto(Customer customer) {
        return new ResponseCustomerDto(customer.getName(), customer.getEmail(), customer.getPhoneNumber(), "Welcome");
    }

    public List<ResponseCustomerDto> findAllCustomers() {
        return customerRepository.listAll().stream()
                .map(this::toResponseCustomerDto)
                .collect(Collectors.toList());
    }

    public ResponseCustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            return toResponseCustomerDto(customer);
        } else {
            throw new NoSuchElementException("The customer with that id is'nt available: " + id)
        }
    }

    @Timeout(3000)
    @Fallback(fallbackMethod = "fallbackCreatedCustomer")
    @Transactional
    public  ResponseCustomerDto createCustomer(CustomerDto customerDto) {
        Response userResponse = userClient.getUserById(customerDto.getEmail())
    }



}
