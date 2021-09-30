package contoller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import model.Project;
import service.QueryService;
import service.ServiceFactory;

import java.util.List;
import java.util.Scanner;

public class Console {
    private QueryService service = QueryService.getInstance();
    private final Scanner sc = new Scanner(System.in);
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static Console console;

    @SneakyThrows
    public static synchronized Console getInstance() {
        if (console == null) {
            console = new Console();
        }
        return console;
    }
    @SneakyThrows
    public void selectMenu(){
        String str = "1 - зарплата(сумма) всех разработчиков отдельного проекта.\n" +
                "2 - список разработчиков отдельного проекта.\n" +
                "3 - список всех Java/C#/C++/JS разработчиков(на выбор).\n" +
                "4 - список всех junior/middle/senior разработчиков(на выбор).\n" +
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

        while (!selectIsGood(i,6,0));
        switchSelected(i);

    }

    private Boolean selectIsGood(int i, int max, int min) {
        if (i >= min && i <= max) return true;
        else {
            return false;
        }
    }
    @SneakyThrows
    private void switchSelected(int i){
        switch (i) {
            case 1:
                Long id = selectProject();
                System.out.println(service.getSumSalaryByProject(id));
                exitOrNot();
                break;
            case 2:
                Long id1 = selectProject();
                List developers = service.getDevelopersByProject(id1);
                String json = gson.toJson(developers);
                System.out.println(json);
                exitOrNot();
                break;
            case 3:
                String skill = selectSkillDeveloper();
                System.out.println(gson.toJson(service.listDevelopersOfSkill(skill)));
                exitOrNot();
                break;
            case 4:
                String level = selectLevelDeveloper();
                System.out.println(gson.toJson(service.listDevelopersOfLevelSkill(level)));
                exitOrNot();
                break;
            case 5:
                System.out.println(gson.toJson(service.listProjectsWithCountOfDevelopers()));
                exitOrNot();
                break;
            case 6:
                CrudController.getInstance().selectModel();
                break;
            default:
                break;
        }
    }
    @SneakyThrows
    public void exitOrNot(){
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
        if (i == 1) {
            Console.getInstance().selectMenu();
        }
    }
    private boolean isOneOrZero (int i) {
        if (i!=0 && i!=1) {
            System.out.println("Вводите ответ только из предложенных значений.");
            return false;
        }
        return true;
    }
    public Long selectProject() {
        List<Project> list = ServiceFactory.of(Project.class).findALL();
        System.out.println(list);
        long i = 0L;
        do {
            System.out.println("Выбирите проект указав его ID от 1 до "+ list.size() + "!");
            while (!sc.hasNextInt()){
                System.out.println("Введите число от 1 до " + list.size() + "!");
                sc.next();
            }
            i = sc.nextLong();
        } while (!isGood(i,list.size()));
        return i;
    }
    private boolean isGood(Long i, int size) {
        if (i > 0 && i <= size) return true;
        return false;
    }
    private String selectLevelDeveloper() {
        int i;
        String level = "";
        do {
            System.out.println("Введите уровень владения языка программироания.\n 1 - Junior, 2 - Middle, 3 - Senior ?");
            while (!sc.hasNextInt()) {
                System.out.println("Вводите число от 1 до 3!");
                sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i, 3, 1));
        switch (i) {
            case 1:
                level = "Junior";
                break;
            case 2:
                level = "Middle";
                break;
            case 3:
                level = "Senior";
            default:
                break;
        }
        return level;
    }
    public String selectSkillDeveloper() {
        int i;
        String skill = "";
        do {
            System.out.println("Введите названия языка программироания.\n 1 - Java, 2 - C++, 3 - C#, 4 - JS ?");
            while (!sc.hasNextInt()) {
                System.out.println("Вводите число от 1 до 4!");
                sc.next();
            }
            i = sc.nextInt();
        }
        while (!selectIsGood(i, 4, 1));
        switch (i) {
            case 1:
                skill = "Java";
                break;
            case 2:
                skill = "C++";
                break;
            case 3:
                skill = "C#";
                break;
            case 4:
                skill = "JS";
                break;
            default:
                break;
        }
        return skill;
    }
}
