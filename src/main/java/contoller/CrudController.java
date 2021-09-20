package contoller;

import lombok.SneakyThrows;
import model.*;
import repository.CrudRepository;
import repository.RepositoryFactory;
import service.*;

import java.util.Scanner;

public class CrudController<T extends BaseEntity<ID>, ID>{
    final Scanner sc = new Scanner(System.in);
    private static CrudController service;
    @SneakyThrows
    public static synchronized CrudController getInstance() {
        if (service == null) {
            service = new CrudController();
        }
        return service;
    }

    public void selectModel() {
        String str = "Выберите номер таблицы?\n1 - Companies\n2 - Customers\n3 - Developers\n4 - Projects\n5 - Skills";
        //System.out.println(str);
        int i =selectedIsInt(str);

        switchTable(i);
    }

    private void switchTable(int i) {
        CrudRepository crudRepository;
        switch (i) {
            case 1: crudRepository = RepositoryFactory.of(Company.class);
                    CompanyService.getInstance().selectCrudService(crudRepository);
                    break;
            case 2: crudRepository = RepositoryFactory.of(Customer.class);
                    CustomerService.getInstance().selectCrudService(crudRepository);
                    break;
            case 3: crudRepository = RepositoryFactory.of(Developer.class);
                    DeveloperService.getInstance().selectCrudService(crudRepository);
                    break;
            case 4: crudRepository = RepositoryFactory.of(Project.class);
                    ProjectService.getInstance().selectCrudService(crudRepository);
                    break;
            case 5: crudRepository = RepositoryFactory.of(Skill.class);
                    SkillService.getInstance().selectCrudService(crudRepository);
                    break;
            default: break;
        }
    }
    private Boolean selectIsGood(int i) {
        if (i > 0 && i < 6) return true;
        System.out.println("Вводите номер таблицы из предложенных значений.");
        return false;
    }
    private int selectedIsInt(String str) {
        int i = 0;
        do {
            System.out.println(str);
            while (!sc.hasNextInt()){
                System.out.println("Введите число от 1 до 5!");
               sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i));
        return i;
    }

}
