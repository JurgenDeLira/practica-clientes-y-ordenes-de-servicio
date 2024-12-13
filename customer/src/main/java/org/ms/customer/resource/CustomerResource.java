package org.ms.customer.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
import org.ms.customer.service.impl.CustomerServiceImpl;

@Path("/customers")
public class CustomerResource {
    @Inject
    CustomerServiceImpl customerService;

}
