package service;

import lombok.SneakyThrows;
import model.Developer;

public class DeveloperService extends BaseService<Developer,Long>{
    private static DeveloperService service;

    @SneakyThrows
    public static synchronized DeveloperService getInstance() {
        if (service == null) {
            service = new DeveloperService();
        }
        return service;
    }

    @Override
    public Developer init() {
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
        developer.setAge(checkInt());

        return developer;
    }
}
