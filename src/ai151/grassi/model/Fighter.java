package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Fighter {

    private SimpleDoubleProperty hp;
    private SimpleIntegerProperty stamina, agility, strength; //выносливость, ловкость, сила
    private int maxFighterStamina;

    private int attack;
    private int chanceToDodge; // шанс увернуться

    public Fighter(int stamina, int strength, int agility) {
        this.hp = new SimpleDoubleProperty(MAX_VALUE);

        this.stamina = new SimpleIntegerProperty(stamina);
        this.strength = new SimpleIntegerProperty(strength);
        this.agility = new SimpleIntegerProperty(agility);

        maxFighterStamina = this.getStamina();
        this.attack = this.getAttack();
    }

    public int getMaxFighterStamina() {
        return maxFighterStamina;
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

    public double getHp() {
        return hp.get();
    }

    public SimpleDoubleProperty getHpProperty() {
        return hp;
    }

    public void setHp(double hp) {
        //this.hp.set(hp);
        if(getHp() > MIN_VALUE) {
            this.hp.set(hp);
            if(getHp() < MIN_VALUE) {
                this.hp.set(MIN_VALUE);
            }
        } else if(getHp() <= MIN_VALUE){
            this.hp.set(MIN_VALUE);
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void attackLight(Fighter opponent){
        if(getStamina() >= getAttack()) {
            setAttack(strength.intValue() / 2);
            setStamina(getStamina() - getAttack() / 2);
            opponent.setHp( (opponent.getHp()*100 - getAttack()) / 100 );

            if(getStamina() <= MIN_VALUE) {
                System.out.println("Не хватает энергии для удара, необходимо пропустить ход");
            }
        } else {
            System.out.println("Пора отдохнуть");
        }
    }


    public void attackMedium(Fighter opponent){
        setAttack(strength.intValue());
        if(getStamina() >= getAttack()) {
            setStamina(getStamina() - getAttack());
            opponent.setHp( (opponent.getHp()*100 - getAttack()) / 100 );
            if(getStamina() <= MIN_VALUE) {
                System.out.println("Не хватает энергии для удара, попробуйте легкий удар или пропустите ход");
            }
        } else {
            System.out.println("Пора отдохнуть");

        }
    }

    public void attackHard(Fighter opponent){
        setAttack(maxFighterStamina);
        if(getStamina() >= getAttack()) {
            setStamina(getStamina() - maxFighterStamina);
            opponent.setHp( (opponent.getHp()*100  - getAttack()) / 100 );
            if(getStamina() <= MIN_VALUE) {
                System.out.println("Не хватает энергии для удара, попробуйте легкий или средний удар, или пропустите ход");
            }
        } else {
            System.out.println("Пора отдохнуть");
        }
    }


    // увернуться
    public void dodge(Fighter opponent) {
        Random random = new Random();
        chanceToDodge = random.nextInt(1001);
        if(chanceToDodge < getAgility()*10) {
            opponent.setAttack(MIN);
        } else {
            System.out.println("Не увернулся");
        }
    }

    public void skipMove(){
        setAttack(MIN);
        if((getStamina() + maxFighterStamina/2) <= maxFighterStamina) {
            setStamina(getStamina() + maxFighterStamina/2);
        } else {
            setStamina(maxFighterStamina);
        }
    }

    public boolean isLose(){
        if(getHp() == MIN_VALUE) {
            System.out.println("Lose");
        }
        return false;
    }

}