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
    private boolean isWin;

    private SimpleDoubleProperty energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

    private int countWins;


    public Gotchi(String name, int stamina, int strength, int agility) {
        super(stamina, strength, agility);

        this.name = name;
        this.level = new SimpleIntegerProperty(MIN_LEVEL);
       /* this.level = new SimpleIntegerProperty(9);
        countWins = 12;*/
        this.exp = new SimpleIntegerProperty(MIN);

        this.energy = new SimpleDoubleProperty(MAX_VALUE);
        this.food = new SimpleDoubleProperty(MAX_VALUE);
        this.health = new SimpleDoubleProperty(MAX_VALUE);
        this.mood = new SimpleDoubleProperty(MAX_VALUE);
        this.clean = new SimpleDoubleProperty(MAX_VALUE);

        this.isGone = false;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin() {
        isWin = true;
    }

    public boolean isGone() {
        return isGone;
    }

    public void setGone() {
        isGone = true;
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

    public void setExp() {
        if (countWins == 5 || countWins == 7 || countWins == 9 || countWins == 11) {
            this.exp.set(50);
        } else if(countWins == 13 || countWins == 14) {
            this.exp.set(getExp() + 34);
        }
    }

    public void setMinExp() {
        this.exp.set(MIN);
    }

    public void levelUp() {
        setExp();
        if(countWins == 1 || countWins == 2 || countWins == 3 || countWins == 4) {
            setMinExp();
            setStrength(getStrength()+10);
            setStamina(getStamina()+10);
            setAgility(getAgility()+10);
            setLevel(getLevel() + 1);
        } else if (countWins == 6 || countWins == 8 || countWins == 10 || countWins == 12){
            setMinExp();
            setStrength(getStrength()+5);
            setStamina(getStamina()+5);
            setAgility(getAgility()+5);
            setLevel(getLevel() + 1);
        } else if(countWins == 15) {
            setMinExp();
            setStrength(100);
            setStamina(100);
            setAgility(100);
            setLevel(getLevel() + 1);
        }
    }

    public void setCountWins(int countWins) {
        this.countWins = countWins;
    }

    public int getCountWins() {
        return countWins;
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
        Platform.runLater(() -> {
            if (getEnergy() == MIN_VALUE) {
                System.out.println("-Я в коме-");
            }
            setEnergy(getEnergy() - 0.1);
        });
    }

    public void becomeHungry() {
        Platform.runLater(() -> {
            if (getFood() == MIN_VALUE) {
                System.out.println("-Я умер-");
            }
            setFood(getFood() - 0.05);
        });
    }

    public void becomeSick() {
        Platform.runLater(() -> {
            if(getHealth() == MIN_VALUE) {
                System.out.println("-Я умер-");
            }
            if(getClean() < 0.3 || getFood() < 0.3 || getEnergy() < 0.3) {
                setHealth(getHealth() - 0.05);
            } else {
                setHealth(getHealth());
            }
        });
    }

    public void becomeSad() {
        Platform.runLater(() -> {
            if (getMood() == MIN_VALUE) {
                System.out.println("-Я не счастлив-");
            }
            setMood(getMood() - 0.01);
        });
    }

    public void becomeDirty() {
        Platform.runLater(() -> {
            if (getClean() == MIN_VALUE) {
                System.out.println("-Я очень болен-");
            }
            setClean(getClean() - 0.05);
        });
    }

    @Override
    public void attackLight(Fighter opponent) {
        setAttack(getStrength()/2);
        setNewStamina(getStamina()-getAttackInt() / 2);
        super.attackLight(opponent);
    }

    @Override
    public void attackMedium(Fighter opponent) {
        setAttack(getStrength()-10);
        setNewStamina(getStamina()-getAttackInt());
        super.attackMedium(opponent);
    }

    @Override
    public void attackHard(Fighter opponent) {
        setAttack(getMaxFighterStamina());
        setNewStamina(getStamina()-getAttackInt());
        super.attackHard(opponent);
    }

}