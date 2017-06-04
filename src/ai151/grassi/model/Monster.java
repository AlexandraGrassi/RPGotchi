package ai151.grassi.model;

public class Monster extends Fighter {

    public Monster(String name, int stamina, int strength, int agility) {
        super(name, stamina, strength, agility);
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
