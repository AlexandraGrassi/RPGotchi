package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Random;

public class Fighter {

    private SimpleDoubleProperty hp;
    private SimpleIntegerProperty stamina, agility, strength; //выносливость, ловкость, сила
    private SimpleStringProperty fightInfo;
    private int maxFighterStamina;
    private String name;
    private boolean isSkipMove = false;

    private double attack;
    private int newStamina;
    private int chanceToDodge; // шанс увернуться
    private boolean moveDone = false;

    public Fighter(String name, int stamina, int strength, int agility) {
        this.hp = new SimpleDoubleProperty(MAX_VALUE);

        this.name = name;
        this.stamina = new SimpleIntegerProperty(stamina);
        this.strength = new SimpleIntegerProperty(strength);
        this.agility = new SimpleIntegerProperty(agility);

        maxFighterStamina = this.getStamina();
        this.attack = this.getAttack();
        this.fightInfo = new SimpleStringProperty("");
    }

    public String getName() {
        return name;
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

    public void setMoveDone() {
        this.moveDone = false;
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

    public String getFightInfo() {
        return fightInfo.get();
    }

    public SimpleStringProperty fightInfoProperty() {
        return fightInfo;
    }

    public void setFightInfo(String fightInfo) {
        this.fightInfo.set(fightInfo);
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

    public void setNewStamina(int newStamina) {
        this.newStamina = newStamina;
    }

    private void attack(double attack, int newStamina, Fighter opponent) {
        setAttack(attack);
        if(getStamina() >= newStamina) {
            if(newStamina < MIN) {
                setFightInfo("Не хватает энергии");
                setAttack(0);
                setMoveDone();
            } else {
                setStamina(newStamina);
                opponent.dodge(this);
                opponent.setHp(opponent.getHp() - getAttack()/100);
                moveDone = true;
                setAttack(0);
            }
        } else {
            setFightInfo("Не хватает энергии");
        }
    }

    public void attackLight(Fighter opponent){
        this.setFightInfo("");
        isSkipMove = false;
        attack(attack, newStamina, opponent);
    }

    public void attackMedium(Fighter opponent){
        this.setFightInfo("");
        isSkipMove = false;
        attack(attack, newStamina, opponent);
    }

    public void attackHard(Fighter opponent) {
        this.setFightInfo("");
        isSkipMove = false;
        attack(attack, newStamina, opponent);
    }

    // увернуться
    public void dodge(Fighter opponent) {
        Random random = new Random();
        chanceToDodge = random.nextInt(1001);
        if(chanceToDodge < getAgility()*10) {
            opponent.setAttack(MIN);
            System.out.println(this.getName() + " увернулся");
            if(!opponent.isSkipMove) {
                opponent.setFightInfo(this.getName() + " увернулся");
            }
        }
    }

    public void skipMove(Fighter opponent){
        this.setFightInfo("");
        setAttack(MIN);
        if((getStamina() + maxFighterStamina/2) <= maxFighterStamina) {
            setStamina(getStamina() + maxFighterStamina/2);
        } else {
            setStamina(maxFighterStamina);
        }
        dodge(opponent);
        isSkipMove = true;
        moveDone = true;
    }

    public boolean isLose(){
        return getHp() == MIN_VALUE;
    }

}