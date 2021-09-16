package service;

import lombok.SneakyThrows;
import model.Skill;

public class SkillService extends BaseService<Skill,Long>{
    private static SkillService service;

    @SneakyThrows
    public static synchronized SkillService getInstance() {
        if (service == null) {
            service = new SkillService();
        }
        return service;
    }

    @Override
    public Skill init(){
        Skill skill = new Skill();
        System.out.println("Введите ID");
        skill.setId(checkLong());
        System.out.println("Введите название языка программирования.");
        skill.setName(sc.next());
        System.out.println("Введите уровень владения языка программирования. (Junior), (Middle), (Senior)");
        skill.setLevel(checkSkill());
        return skill;
    }
}
