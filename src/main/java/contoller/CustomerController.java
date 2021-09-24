package contoller;

import lombok.SneakyThrows;
import model.Customer;

public class CustomerController extends BaseController<Customer, Long> {
    private static CustomerController service;

    @SneakyThrows
    public static synchronized CustomerController getInstance() {
        if (service == null) {
            service = new CustomerController();
        }
        return service;
    }
    @Override
    public Customer makeModel() {
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
