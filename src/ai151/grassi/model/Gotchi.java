package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Gotchi extends Fighter {

    private SimpleIntegerProperty level;
    private SimpleIntegerProperty exp;
    private String name;
    private boolean isGone;

    private SimpleDoubleProperty energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

    private int countWins;


    public Gotchi(String name, int stamina, int strength, int agility) {
        super(stamina, strength, agility);

        this.name = name;
        this.level = new SimpleIntegerProperty(MIN_LEVEL);
        this.exp = new SimpleIntegerProperty(MIN);

        this.energy = new SimpleDoubleProperty(MAX_VALUE);
        this.food = new SimpleDoubleProperty(MAX_VALUE);
        this.health = new SimpleDoubleProperty(MAX_VALUE);
        this.mood = new SimpleDoubleProperty(MAX_VALUE);
        this.clean = new SimpleDoubleProperty(MAX_VALUE);

        this.isGone = false;
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone(boolean gone) {
        isGone = gone;
    }

    public String getName() {
        return name;
    }
    public SimpleIntegerProperty getLevelProperty() { return this.level; }
    public SimpleIntegerProperty getExpProperty() { return this.exp; }

    public SimpleDoubleProperty getEnergyProperty() {
        return this.energy;
    }
    public SimpleDoubleProperty getFoodProperty() { return this.food; }
    public SimpleDoubleProperty getHealthProperty() { return this.health; }
    public SimpleDoubleProperty getMoodProperty() { return this.mood; }
    public SimpleDoubleProperty getCleanProperty() { return this.clean; }

    public int getLevel() { return getLevelProperty().intValue(); }
    public int getExp() { return getExpProperty().intValue(); }

    public double getEnergy() { return getEnergyProperty().doubleValue(); }
    public double getFood() { return getFoodProperty().doubleValue(); }
    public double getHealth() { return getHealthProperty().doubleValue(); }
    public double getMood() { return getMoodProperty().doubleValue(); }
    public double getClean () { return getCleanProperty().doubleValue(); }

    public void setEnergy(double energy) {
        if(getEnergy() > MIN_VALUE) {
            this.energy.set(energy);
            if(getEnergy() < MIN_VALUE) {
                this.energy.set(MIN_VALUE);
            }
        } else if(getEnergy() <= MIN_VALUE){
            this.energy.set(MIN_VALUE);
        }
    }

    public void setFood(double food) {
        if(getFood() > MIN_VALUE) {
            this.food.set(food);
            if(getFood() < MIN_VALUE) {
                this.food.set(MIN_VALUE);
            }
        } else if(getFood() <= MIN_VALUE){
            this.food.set(MIN_VALUE);
        }
    }

    public void setHealth(double health) {
        if(getHealth() > MIN_VALUE) {
            this.health.set(health);
            if(getHealth() < MIN_VALUE) {
                this.health.set(MIN_VALUE);
            }
        } else if(getHealth() <= MIN_VALUE){
            this.health.set(MIN_VALUE);
        }
    }

    public void setMood(double mood) {
        if(getMood() > MIN_VALUE) {
            this.mood.set(mood);
            if(getMood() < MIN_VALUE) {
                this.mood.set(MIN_VALUE);
            }
        } else if(getMood() <= MIN_VALUE){
            this.mood.set(MIN_VALUE);
        }
    }

    public void setClean(double clean) {
        if(getClean() > MIN_VALUE) {
            this.clean.set(clean);
            if(getClean() < MIN_VALUE) {
                this.clean.set(MIN_VALUE);
            }
        } else if(getClean() <= MIN_VALUE){
            this.clean.set(MIN_VALUE);
        }
    }

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
                setEnergy(getEnergy() - 0.1);
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
                setFood(getFood() - 0.05);
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
                setEnergy((getHealth() - 5)/100);
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
                setMood(getMood() - 0.01);
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
                setClean(getClean() - 0.05);
            }
        });
    }

}