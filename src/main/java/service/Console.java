package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import model.BaseEntity;
import repository.QueryRepositoryImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class Console <T extends BaseEntity<ID>, ID>{

    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    final QueryRepositoryImpl repo = QueryRepositoryImpl.getInstance();
    Scanner sc = new Scanner(System.in);

    private static Console console;

    @SneakyThrows
    public static synchronized Console getInstance() {
        if (console == null) {
            console = new Console();
        }
        return console;
    }

    public void selectMenu() throws SQLException {
        String str = "1 - зарплата(сумма) всех разработчиков отдельного проекта.\n" +
                "2 - список разработчиков отдельного проекта.\n" +
                "3 - список всех Java разработчиков.\n" +
                "4 - список всех middle разработчиков.\n" +
                "5 - список проектов в следующем формате: дата создания - название проекта - количество разработчиков на этом проекте.\n" +
                "6 - использование CRUD сервисов для выбранных таблиц.\n" +
                "0 - выйти из программы.\n" +
                "Сделайте свой выбор! Введите число от 0 до 6.";
        int i;
        do {
            System.out.println(str);
            while (!sc.hasNextInt()){
                System.out.println("Введите число от 0 до 6!");
                sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i));
        switchSelected(i);

    }

    private Boolean selectIsGood(int i) {
        if (i >= 0 && i <= 6) return true;
        System.out.println("Вводите ответ из предложенных значений.");
        return false;
    }

    @SneakyThrows
    private void switchSelected(int i){
        switch (i) {
            case 1:
                System.out.println(gson.toJson(repo.getSumByProID(repo.selectProject())));
                exitOrNot();
                break;
            case 2:
                System.out.println(gson.toJson(repo.getDevsByProID(repo.selectProject())));
                exitOrNot();
                break;
            case 3:
                System.out.println(gson.toJson(repo.listJava()));
                exitOrNot();
                break;
            case 4:
                System.out.println(gson.toJson(repo.listMiddle()));
                exitOrNot();
                break;
            case 5:
                System.out.println(gson.toJson(repo.listProWithData()));
                exitOrNot();
                break;
            case 6:
                CrudService.getInstance().selectModel();
                exitOrNot();
                break;
            default:
                break;
        }
    }

    @SneakyThrows
    private void exitOrNot(){
        int i;
        do {
            System.out.println("Желаете ли вернутся к выбору задачи? 1 - Да!, 0 - Завершит работу!");
            while (!sc.hasNextInt()){
                System.out.println("Введите число 1 - Да!, 0 - Завершит работу!");
                sc.next();
            }
            i = sc.nextInt();
        }
        while (!isOneOrZero(i));

        switch (i){
            case 1: Console.getInstance().selectMenu();
            default: break;
        }
    }
    private boolean isOneOrZero (int i) {
        if (i!=0 && i!=1) {
            System.out.println("Вводите ответ только из предложенных значений.");
            return false;
        }
        return true;
    }
}
