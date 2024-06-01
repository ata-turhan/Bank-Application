package bank.adapter;

import bank.domain.Customer;
import bank.dto.CustomerDTO;

public class CustomerAdapter {

    private CustomerAdapter() {
        // Private constructor to prevent instantiation
    }

    public static Customer toEntity(CustomerDTO customerDto) {
        if (customerDto == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDTO toDto(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName(customer.getName());
        return customerDto;
    }
}
