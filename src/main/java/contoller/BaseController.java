package contoller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.BaseEntity;
import repository.CrudRepository;
import service.CheckInputService;

import java.util.Scanner;

public abstract class BaseController <E extends BaseEntity<ID>, ID>{
    final Console console = Console.getInstance();
    final Scanner sc = new Scanner(System.in);
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CheckInputService check = CheckInputService.getInstance();


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
                console.exitOrNot();
                break;
            case 2:
                Long id = checkId(crudRepository);
                System.out.println(gson.toJson(crudRepository.findById(id)));
                console.exitOrNot();
                break;
            case 3:
                Long id1 = checkId(crudRepository);
                crudRepository.deleteById(id1);
                System.out.println("Запись по ID = " + id1 + " удалена!");
                console.exitOrNot();
                break;
            case 4:
                BaseEntity model = makeModel();
                System.out.println(crudRepository.save(model));
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
        while (!check.selectIsGood(i));
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
        } while (!check.isGender(gender));
        return gender;
    }

    String checkSkill() {
        String skill = "";
        do {
            System.out.println("Правильно укажите уровень из предложенных вариантов.");
            skill = sc.next();
        } while (!check.isSkill(skill));
        return skill;
    }
    private boolean isIdExists(CrudRepository repository,Long id){
        if(repository.findById(id).isPresent()) return true;
        System.out.println("Нет записей по данному ID!\nПовторите ввод ID.");
        return false;
    }
    Long checkId(CrudRepository repository){
        Long id;
        do {
            id = selectId();

        }
        while (!isIdExists(repository,id));
        return id;
    }
}
