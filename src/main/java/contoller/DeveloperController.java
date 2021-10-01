package contoller;

import lombok.SneakyThrows;
import model.Developer;

public class DeveloperController extends BaseController {
    private static DeveloperController service;

    @SneakyThrows
    public static synchronized DeveloperController getInstance() {
        if (service == null) {
            service = new DeveloperController();
        }
        return service;
    }

    @Override
    public Developer makeModel() {
        Developer developer = new Developer();
        System.out.println("Введите ID");
        developer.setId(checkLong());
        System.out.println("Введите имя разработчика.");
        developer.setName(sc.next());
        System.out.println("Введите фамилию разработчика.");
        developer.setSurname(sc.next());
        System.out.println("Укажите возраст разработчика.");
        developer.setAge(checkInt());
        System.out.println("Укажите пол разработчика. (M) - Мужской, (W) - Женский");
        developer.setGender(checkGender());
        System.out.println("Укажите зарпдату разработчика.");
        developer.setSalary(checkInt());

        return developer;
    }
}
