package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;

public class Gotchi extends Fighter {

    private SimpleIntegerProperty level;
    private SimpleIntegerProperty exp;
    private String name;

    private SimpleIntegerProperty energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

    private int countWins;

    public Gotchi(String name, int stamina, int strength, int agility) {
        super(stamina, strength, agility);
        this.name = name;
        this.level = new SimpleIntegerProperty(MIN_LEVEL);
        this.exp = new SimpleIntegerProperty(MIN_VALUE);

        this.energy = new SimpleIntegerProperty(MAX_VALUE);
        this.food = new SimpleIntegerProperty(MAX_VALUE);
        this.health = new SimpleIntegerProperty(MAX_VALUE);
        this.mood = new SimpleIntegerProperty(MAX_VALUE);
        this.clean = new SimpleIntegerProperty(MAX_VALUE);
    }

    public String getName() {
        return name;
    }
    public SimpleIntegerProperty getLevelProperty() { return this.level; }
    public SimpleIntegerProperty getExpProperty() { return this.exp; }

    public SimpleIntegerProperty getEnergyProperty() {
        return this.energy;
    }
    public SimpleIntegerProperty getFoodProperty() { return this.food; }
    public SimpleIntegerProperty getHealthProperty() { return this.health; }
    public SimpleIntegerProperty getMoodProperty() { return this.mood; }
    public SimpleIntegerProperty getCleanProperty() { return this.clean; }

    public int getLevel() { return getLevelProperty().intValue(); }
    public int getExp() { return getExpProperty().intValue(); }

    public int getEnergy() { return getEnergyProperty().intValue(); }
    public int getFood() { return getFoodProperty().intValue(); }
    public int getHealth() { return getHealthProperty().intValue(); }
    public int getMood() { return getMoodProperty().intValue(); }
    public int getClean () { return getCleanProperty().intValue(); }

    public void setEnergy(int energy) { this.energy.set(energy); }
    public void setFood(int food) { this.food.set(food); }
    public void setHealth(int health) { this.health.set(health); }
    public void setMood(int mood) { this.mood.set(mood); }
    public void setClean(int clean) { this.clean.set(clean); }

    public void setLevel(int level) { this.level.set(level); }
    public void setExp(int exp) { this.exp.set(exp); }

    private void levelUp() {
        if(countWins == 1 || countWins == 2 || countWins == 3 || countWins == 4) {
            setStrength(getStrength()+10);
            setStamina(getStamina()+10);
            setAgility(getAgility()+10);
        } else if (countWins == 6 || countWins == 8 || countWins == 10 || countWins == 12){
            setStrength(getStrength()+5);
            setStamina(getStamina()+5);
            setAgility(getAgility()+5);
        } else if(countWins == 15) {
            setStrength(100);
            setStamina(100);
            setAgility(100);
        }
    }

    public int getSumOfAbilities() {
        return getStamina()+getAgility()+getStrength();
    }

    public Monster getMonster() {
        Monster monster;
        MonsterFactory monsterFactory = new MonsterFactory();
        monster = monsterFactory.createMonster(getSumOfAbilities());
        return monster;
    }


    public void becomeSleepy() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (getEnergy() == MIN_VALUE) {
                    System.out.println("-Я в коме-");
                }
                setEnergy(getEnergy() - 10);
                if (getEnergy() < MIN_VALUE) {
                    setEnergy(getEnergy() - (getEnergy() % 100));
                }
            }
        });
    }

    public void becomeHungry() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (getFood() == MIN_VALUE) {
                    System.out.println("-Я умер-");
                }
                setFood(getFood() - 5);
                if (getFood() < MIN_VALUE) {
                    setFood(getFood() - (getFood() % 100));
                }
            }
        });
    }

    /*public void becomeSick() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (getHealth() == MIN_VALUE) {
                    System.out.println("-Я умер-");
                }
                setEnergy(getHealth() - 5);
                if (getFood() < MIN_VALUE) {
                    setHealth(getHealth() - (getHealth() % 100));
                }
            }
        });
    }*/

    public void becomeSad() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (getMood() == MIN_VALUE) {
                    System.out.println("-Я не счастлив-");
                }
                setMood(getMood() - 1);
                if (getMood() < MIN_VALUE) {
                    setMood(getMood() - (getMood() % 100));
                }
            }
        });
    }

    public void becomeDirty() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (getClean() == MIN_VALUE) {
                    System.out.println("-Я очень болен-");
                }
                setClean(getClean() - 5);
                if (getClean() < MIN_VALUE) {
                    setClean(getClean() - (getClean() % 100));
                }
            }
        });
    }
}