package contoller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BaseEntity;
import service.BaseService;

import java.util.Scanner;

public abstract class BaseController {
    final Console console = Console.getInstance();
    final Scanner sc = new Scanner(System.in);
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    public void selectCrudService(BaseService baseService) {
        String str = "Выберите действие над выбранной таблицой\n" +
                "1 - Вывести всю таблицу.\n2 - Найти элемент в таблице по ID\n" +
                "3 - Удалить елмент в табли6це по ID\n" +
                "4 - Записать/обновить(если уже такой ID существует) елемент в таблицу.";
        int i = selectedIsInt(str);
        switchCrudService(i, baseService);

    }

    void switchCrudService(int i, BaseService baseService) {
        switch (i) {
            case 1:
                System.out.println(gson.toJson(baseService.findALL()));
                console.exitOrNot();
                break;
            case 2:
                Long id = checkId(baseService);
                System.out.println(gson.toJson(baseService.findById(id)));
                console.exitOrNot();
                break;
            case 3:
                Long id1 = checkId(baseService);
                baseService.deleteById(id1);
                System.out.println("Запись по ID = " + id1 + " удалена!");
                console.exitOrNot();
                break;
            case 4:
                BaseEntity model = makeModel();
                System.out.println(baseService.save(model));
                console.exitOrNot();
            default: break;
        }
    }

    public abstract BaseEntity makeModel();

    Long selectId() {
        long i;
        System.out.println("Введите ID");
        while (!sc.hasNextLong()) {
            System.out.println("Вы ввели не число.");
            sc.next();
        }
        i = sc.nextLong();

        return i;
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

    String checkSkill() {
        String skill = "";
        do {
            System.out.println("Правильно укажите уровень из предложенных вариантов.");
            skill = sc.next();
        } while (!isSkill(skill));
        return skill;
    }
    private boolean isIdExists(BaseService service,Long id){
        if(service.findById(id).isPresent()) return true;
        System.out.println("Нет записей по данному ID!\nПовторите ввод ID.");
        return false;
    }
    Long checkId(BaseService baseService){
        Long id;
        do {
            id = selectId();

        }
        while (!isIdExists(baseService,id));
        return id;
    }
    private Boolean selectIsGood(int i) {
        if (i >= 0 && i < 5) return true;
        System.out.println("Вводите номер таблицы из предложенных значений.");
        return false;
    }
    private boolean isGender(String s) {
        if (s.equals("M") || s.equals("m") || s.equals("W") || s.equals("w")) return true;
        return false;
    }
    private boolean isSkill(String skill) {
        String s = skill.toLowerCase();
        if (s.equals("junior") || s.equals("middle") || s.equals("senior")) return true;
        return false;
    }
}
