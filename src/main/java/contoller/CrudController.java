package contoller;

import lombok.SneakyThrows;
import model.*;
import repository.CrudRepository;
import repository.RepositoryFactory;
import service.TableService;

import java.util.Scanner;

public class CrudController<T extends BaseEntity<ID>, ID>{
    final Scanner sc = new Scanner(System.in);
    private static CrudController service;
    private TableService tableService = TableService.getInstance();
    @SneakyThrows
    public static synchronized CrudController getInstance() {
        if (service == null) {
            service = new CrudController();
        }
        return service;
    }

    public void selectModel() {
        String str = "Выберите номер таблицы?\n1 - Companies\n2 - Customers\n3 - Developers\n4 - Projects\n5 - Skills\n" +
                "0 - Вернутся в предыдущее меню";
        //System.out.println(str);
        int i =selectedIsInt(str);

        tableService.switchTable(i);
    }

    private Boolean selectIsGood(int i) {
        if (i >= 0 && i < 6) return true;
        System.out.println("Вводите номер таблицы из предложенных значений.");
        return false;
    }
    private int selectedIsInt(String str) {
        int i = 0;
        do {
            System.out.println(str);
            while (!sc.hasNextInt()){
                System.out.println("Введите число от 0 до 5!");
               sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i));
        return i;
    }

}
