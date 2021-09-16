package service;

import lombok.SneakyThrows;
import model.Company;

public class CompanyService extends BaseService<Company,Long>{
    private static CompanyService service;
    @SneakyThrows
    public static synchronized CompanyService getInstance() {
        if (service == null) {
            service = new CompanyService();
        }
        return service;
    }
    @Override
    public Company init(){
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
