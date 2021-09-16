package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import repository.CrudRepository;

import java.util.Scanner;

public abstract class BaseService<E extends BaseEntity<ID>, ID> {
    final Scanner sc = new Scanner(System.in);
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void selectCrudService(CrudRepository crudRepository) {
        String str = "Выберите действие над выбранной таблицой\n" +
                "1 - Вывести всю таблицу.\n2 - Найти элемент в таблице по ID\n" +
                "3 - Удалить елмент в табли6це по ID\n" +
                "4 - Записать/обновить(если уже такой ID существует) елемент в таблицу.";
        int i = selectedIsInt(str);
        switchCrudService(i, crudRepository);

    }

    void switchCrudService(int i, CrudRepository crudRepository) {
        switch (i) {
            case 1:
                System.out.println(gson.toJson(crudRepository.findALL()));
                break;
            case 2:
                System.out.println(gson.toJson(crudRepository.findById(selectId())));
                break;
            case 3:
                Long id = selectId();
                crudRepository.deleteById(id);
                System.out.println("Запись по ID = " + id + " удалена!");
                break;
            case 4:
                System.out.println(crudRepository.save(init()));
            default: break;
        }
    }

    protected abstract BaseEntity init();

    Long selectId() {
        Long i;
        System.out.println("Введите ID");
        while (!sc.hasNextLong()) {
            System.out.println("Вы ввели не число.");
            sc.next();
        }
        i = sc.nextLong();

        return i;
    }

    private Boolean selectIsGood(int i) {
        if (i > 0 && i < 5) return true;
        System.out.println("Вводите номер таблицы из предложенных значений.");
        return false;
    }

    private int selectedIsInt(String str) {
        int i;
        do {
            System.out.println(str);
            while (!sc.hasNextInt()) {
                System.out.println("Введите число от 1 до 5!");
                sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i));
        return i;
    }

    Long checkLong() {
        Long id;
        while (!sc.hasNextInt()) {
            System.out.println("ID должен иметь тип Long!");
            sc.next();
        }
        id = sc.nextLong();
        return id;
    }

    int checkInt() {
        int age;
        while (!sc.hasNextInt()) {
            System.out.println("Ввведите число!");
            sc.next();
        }
        age = sc.nextInt();
        return age;
    }

    String checkGender() {
        String gender = "";
        do {
            System.out.println("Правильно укажите пол (M) - Мужской, (W) - Женский");
            gender = sc.next();
        } while (!isGender(gender));
        return gender;
    }

    private boolean isGender(String s) {
        if (s.equals("M") || s.equals("m") || s.equals("W") || s.equals("w")) return true;
        return false;
    }

    String checkSkill() {
        String skill = "";
        do {
            System.out.println("Правильно укажите пол (M) - Мужской, (W) - Женский");
            skill = sc.next();
        } while (!isSkill(skill));
        return skill;
    }

    private boolean isSkill(String skill) {
        String s = skill.toLowerCase();
        if (s.equals("junior") || s.equals("middle") || s.equals("senior")) return true;
        return false;
    }
}
