package contoller;

import lombok.SneakyThrows;
import model.Company;

public class CompanyController extends BaseController {
    private static CompanyController service;
    @SneakyThrows
    public static synchronized CompanyController getInstance() {
        if (service == null) {
            service = new CompanyController();
        }
        return service;
    }
    @Override
    public Company makeModel(){
        Company company = new Company();
        System.out.println("Введите ID");
        company.setId(checkLong());
        System.out.println("Введите название компании.");
        company.setName(sc.next());
        System.out.println("Введите название деятельности компании.");
        company.setSafer(sc.next());
        return company;
    }



}
