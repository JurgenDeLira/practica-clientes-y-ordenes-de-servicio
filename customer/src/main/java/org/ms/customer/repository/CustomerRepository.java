package org.ms.customer.repository;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ms.customer.entity.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
