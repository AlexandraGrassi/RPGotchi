package ai151.grassi.model;

public class Monster extends Fighter {

    private String monsterName;

    public Monster(String name, int stamina, int strength, int agility) {
        super(stamina, strength, agility);
        this.monsterName = name;
    }

}
