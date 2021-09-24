package contoller;

import lombok.SneakyThrows;
import model.Skill;

public class SkillController extends BaseController<Skill,Long> {
    private static SkillController service;

    @SneakyThrows
    public static synchronized SkillController getInstance() {
        if (service == null) {
            service = new SkillController();
        }
        return service;
    }

    @Override
    public Skill makeModel(){
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
