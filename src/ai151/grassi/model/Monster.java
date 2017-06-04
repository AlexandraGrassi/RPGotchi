package ai151.grassi.model;

public class Monster extends Fighter {

    private String monsterName;

    public Monster(String name, int stamina, int strength, int agility) {
        super(stamina, strength, agility);
        this.monsterName = name;
    }

    public String getMonsterName() {
        return monsterName;
    }

    @Override
    public void attackLight(Fighter opponent) {
        setAttack(getStrength()/2 - 5);
        setNewStamina(getStamina()-getAttackInt() / 2);
        super.attackLight(opponent);
    }

    @Override
    public void attackMedium(Fighter opponent) {
        setAttack(getStrength() - 20);
        setNewStamina(getStamina()-getAttackInt());
        super.attackMedium(opponent);
    }

    @Override
    public void attackHard(Fighter opponent) {
        setAttack(getMaxFighterStamina() - 10);
        setNewStamina(getStamina()-getAttackInt());
        super.attackHard(opponent);
    }
}
