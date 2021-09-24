package service;

import contoller.*;
import lombok.SneakyThrows;
import model.*;
import repository.CrudRepository;
import repository.RepositoryFactory;

public class TableService {
    private static TableService tableService;

    @SneakyThrows
    public static synchronized TableService getInstance() {
        if (tableService == null) {
            tableService = new TableService();
        }
        return tableService;
    }
    public void switchTable(int i) {
        CrudRepository crudRepository;
        switch (i) {
            case 1: crudRepository = RepositoryFactory.of(Company.class);
                CompanyController.getInstance().selectCrudService(crudRepository);
                break;
            case 2: crudRepository = RepositoryFactory.of(Customer.class);
                CustomerController.getInstance().selectCrudService(crudRepository);
                break;
            case 3: crudRepository = RepositoryFactory.of(Developer.class);
                DeveloperController.getInstance().selectCrudService(crudRepository);
                break;
            case 4: crudRepository = RepositoryFactory.of(Project.class);
                ProjectController.getInstance().selectCrudService(crudRepository);
                break;
            case 5: crudRepository = RepositoryFactory.of(Skill.class);
                SkillController.getInstance().selectCrudService(crudRepository);
                break;
            case 0: Console.getInstance().selectMenu();
                break;
            default: break;
        }
    }
}
