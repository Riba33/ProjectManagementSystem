package service;

import lombok.SneakyThrows;
import model.Customer;

public class CustomerService extends BaseService<Customer, Long> {
    private static CustomerService service;

    @SneakyThrows
    public static synchronized CustomerService getInstance() {
        if (service == null) {
            service = new CustomerService();
        }
        return service;
    }
    @Override
    public Customer init() {
        Customer customer = new Customer();
        System.out.println("Введите ID");
        customer.setId(checkLong());
        System.out.println("Введите название компании.");
        customer.setName(sc.next());
        System.out.println("Введите название деятельности компании.");
        customer.setSafer(sc.next());
        return customer;
    }
}
