package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Fighter {

    private SimpleIntegerProperty hp;
    private SimpleIntegerProperty stamina, agility, strength; //выносливость, ловкость, сила
    private int maxFighterStamina;

    private SimpleIntegerProperty attack;
    private int chanceToDodge; // шанс увернуться

    public Fighter() {
        this.hp = new SimpleIntegerProperty(MAX_VALUE);
    }

    public Fighter(int stamina, int strength, int agility) {
        this.stamina = new SimpleIntegerProperty(stamina);
        this.strength = new SimpleIntegerProperty(strength);
        this.agility = new SimpleIntegerProperty(agility);

        maxFighterStamina = this.getStamina();
    }

    public SimpleIntegerProperty getStaminaProperty() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina.set(stamina);
    }

    public int getStamina() {
        return stamina.get();
    }

    public int getAgility() {
        return agility.get();
    }

    public SimpleIntegerProperty getAgilityProperty() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility.set(agility);
    }

    public int getStrength() {
        return strength.get();
    }

    public SimpleIntegerProperty getStrengthProperty() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength.set(strength);
    }

    public int getHp() {
        return hp.get();
    }

    public SimpleIntegerProperty getHpProperty() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp.set(hp);
    }

    public int getAttack() {
        return attack.get();
    }

    public SimpleIntegerProperty attackStrengthProperty() {
        return attack;
    }

    public void setAttackStrength(int attackStrength) {
        this.attack.set(attackStrength);
    }

    public void attackLight(Fighter opponent){
        setAttackStrength(strength.intValue() / 2);
        opponent.setHp(opponent.getHp()-getAttack());
        if(getStamina() != 0) {
            setStamina(getStamina() - getAttack() / 2);
            if(getStamina() <= 0) {
                System.out.println("Не хватает энергии для удара, необходимо пропустить ход");
            }
        } else {
            System.out.println("Пора отдохнуть");

        }
    }

    public void attackMedium(Fighter opponent){
        setAttackStrength(strength.intValue());
        opponent.setHp(opponent.getHp()-getAttack());
        if(getStamina() != 0) {
            setStamina(getStamina() - getAttack());
            if(getStamina() <= 0) {
                System.out.println("Не хватает энергии для удара, попробуйте легкий удар или пропустите ход");
            }
        } else {
            System.out.println("Пора отдохнуть");

        }
    }

    public void attackHard(Fighter opponent){
        setAttackStrength(strength.intValue()*2);
        opponent.setHp(opponent.getHp()-getAttack());
        if(getStamina() != 0) {
            setStamina(getStamina() - maxFighterStamina);
            if(getStamina() <= 0) {
                System.out.println("Не хватает энергии для удара, попробуйте легкий или средний удар, или пропустите ход");
            }
        } else {
            System.out.println("Пора отдохнуть");
        }
    }

    public void dodge(Fighter opponent) {
        Random random = new Random();
        chanceToDodge = random.nextInt(1001);
        if(chanceToDodge < getAgility()*10) {
            opponent.setAttackStrength(0);
        } else {
            System.out.println("Не увернулся");
        }
    }

    public void skipMove(){
        setAttackStrength(0);
        setStamina(maxFighterStamina/2);
    }

    public boolean isLose(){
        if(hp.intValue() == 0) {
            System.out.println("Lose");
        }
        return false;
    }

    public boolean isWin(Fighter opponent){
        if(opponent.isLose()){
            System.out.println("WIN");
            return true;
        }
        return false;
    }
}