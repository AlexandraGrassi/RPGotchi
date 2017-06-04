package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Fighter {

    private SimpleDoubleProperty hp;
    private SimpleIntegerProperty stamina, agility, strength; //выносливость, ловкость, сила
    private int maxFighterStamina;

    private double attack;
    private int newStamina;
    private int chanceToDodge; // шанс увернуться
    private boolean moveDone = false;

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

    public boolean isMoveDone() {
        return moveDone;
    }

    public void setMoveDone(boolean moveDone) {
        this.moveDone = moveDone;
    }

    public void setHp(double hp) {
        if(getHp() > MIN_VALUE) {
            this.hp.set(hp);
            if(getHp() < MIN_VALUE) {
                this.hp.set(MIN_VALUE);
            }
        } else if(getHp() <= MIN_VALUE){
            this.hp.set(MIN_VALUE);
        }
    }

    public void setMaxHp() {
        this.hp.set(MAX_VALUE);
    }

    public double getAttack() {
        return attack;
    }
    public int getAttackInt() {
        return (int) attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setStamina(int stamina) {
        this.stamina.set(stamina);
    }

    public SimpleDoubleProperty hpProperty() {
        return hp;
    }

    public SimpleIntegerProperty staminaProperty() {
        return stamina;
    }

    public SimpleIntegerProperty agilityProperty() {
        return agility;
    }

    public SimpleIntegerProperty strengthProperty() {
        return strength;
    }

    public void setMaxFighterStamina(int maxFighterStamina) {
        this.maxFighterStamina = maxFighterStamina;
    }

    public int getNewStamina() {
        return newStamina;
    }

    public void setNewStamina(int newStamina) {
        this.newStamina = newStamina;
    }

    public int getChanceToDodge() {
        return chanceToDodge;
    }

    public void setChanceToDodge(int chanceToDodge) {
        this.chanceToDodge = chanceToDodge;
    }

    private void attack(double attack, int newStamina, Fighter opponent) {
        setAttack(attack);
        if(getStamina() >= newStamina) {
            if(newStamina < MIN) {
                System.out.println("Не хватает энергии для удара, необходимо пропустить ход");
                setAttack(0);
                setMoveDone(false);
            } else {
                setStamina(newStamina);
                opponent.setHp(opponent.getHp() - getAttack()/100);
                moveDone = true;
                setAttack(0);
            }
        } else {
            System.out.println("Пора отдохнуть");
        }
    }

    public void attackLight(Fighter opponent){
        attack(attack, newStamina, opponent);
    }

    public void attackMedium(Fighter opponent){
        attack(attack, newStamina, opponent);
    }

    public void attackHard(Fighter opponent){
        attack(attack, newStamina, opponent);
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
        moveDone = true;
    }

    public boolean isLose(){
        return getHp() == MIN_VALUE;
    }

}