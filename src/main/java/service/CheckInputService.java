package service;

import lombok.SneakyThrows;

public class CheckInputService {

    private static CheckInputService checkInputService;
    @SneakyThrows
    public static synchronized CheckInputService getInstance() {
        if (checkInputService == null) {
            checkInputService = new CheckInputService();
        }
        return checkInputService;
    }

    public Boolean selectIsGood(int i) {
        if (i >= 0 && i < 5) return true;
        System.out.println("Вводите номер таблицы из предложенных значений.");
        return false;
    }

    public boolean isGender(String s) {
        if (s.equals("M") || s.equals("m") || s.equals("W") || s.equals("w")) return true;
        return false;
    }

    public boolean isSkill(String skill) {
        String s = skill.toLowerCase();
        if (s.equals("junior") || s.equals("middle") || s.equals("senior")) return true;
        return false;
    }

}
