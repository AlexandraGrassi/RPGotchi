package ai151.grassi.model;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * Created by AGrassi on 11.04.2017.
 */
public class Gotchi {
    private final int MAX_VALUE = 100;
    private final int MIN_VALUE = 0;
    private final int MAX_LEVEL = 10;
    private final int MIN_LEVEL = 1;

    private SimpleIntegerProperty level;
    private SimpleIntegerProperty experience;
    private String name;

    private SimpleIntegerProperty stamina, agility, strength; //выносливость, ловкость, сила
    private SimpleIntegerProperty energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

    public Gotchi(String name, int stamina, int agility, int strength) {
        this.name = name;
        this.level = new SimpleIntegerProperty(MIN_LEVEL);
        this.experience = new SimpleIntegerProperty(MIN_VALUE);

        this.energy = new SimpleIntegerProperty(MAX_VALUE);
        this.food = new SimpleIntegerProperty(MAX_VALUE);
        this.health = new SimpleIntegerProperty(MAX_VALUE);
        this.mood = new SimpleIntegerProperty(MAX_VALUE);
        this.clean = new SimpleIntegerProperty(MAX_VALUE);

        this.stamina = new SimpleIntegerProperty(stamina);
        this.agility = new SimpleIntegerProperty(agility);
        this.strength = new SimpleIntegerProperty(strength);
    }

    public String getName() {
        return name;
    }
    public SimpleIntegerProperty getLevelProperty() { return this.level; }
    public SimpleIntegerProperty getExperienceProperty() { return this.experience; }

    public SimpleIntegerProperty getEnergyProperty() {
        return this.energy;
    }
    public SimpleIntegerProperty getFoodProperty() { return this.food; }
    public SimpleIntegerProperty getHealthProperty() { return this.health; }
    public SimpleIntegerProperty getMoodProperty() { return this.mood; }
    public SimpleIntegerProperty getCleanProperty() { return this.clean; }

    public SimpleIntegerProperty getStaminaProperty() { return this.stamina; }
    public SimpleIntegerProperty getAgilityProperty() { return this.agility; }
    public SimpleIntegerProperty getStrengthProperty() { return this.strength; }

    public int getLevel() { return getLevelProperty().intValue(); }
    public int getExperience() { return getExperienceProperty().intValue(); }

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
    public void setExperience(int experience) { this.experience.set(experience); }
    public void setStamina(int stamina) { this.stamina.set(stamina); }
    public void setAgility(int agility) { this.agility.set(agility); }
    public void setStrength(int strength) { this.strength.set(strength); }

    public void sleep () {
        if (getEnergy() == MAX_VALUE) {
            System.out.println("-Не хочу спать-");
        }
        setEnergy(getEnergy() + 7);
        if (getEnergy() > MAX_VALUE) {
            setEnergy(getEnergy() - (getEnergy() % 100));
        }
    }

    public void unSleep () {
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

    public void eat() {
        if (getFood() == MAX_VALUE) {
            System.out.println("-Не хочу есть-");
        }
        setFood(getFood() + 6);
        if (getFood() > MAX_VALUE) {
            setFood(getFood() - (getFood() % 100) );
        }
    }

    public void unEat () {
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

    public void treat() {
        if (getHealth() == MAX_VALUE) {
            System.out.println("-Не хочу лечиться-");
        }
        setHealth(getHealth() + 8);
        if (getHealth() > MAX_VALUE) {
            setHealth(getHealth() - (getHealth() % 100) );
        }
    }

    /*public void unTreat () {
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

    public void makeHappy() {
        if (getMood() != MAX_VALUE) {
            setMood(getMood() + 2);
        }
        if (getMood() > MAX_VALUE) {
            setHealth(getHealth() - (getHealth() % 100));
        }
    }

    public void unHappy() {
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

    public void wash() {
        if (getClean() == MAX_VALUE) {
            System.out.println("-Не хочу купаться-");
        }
        setClean(getClean() + 4);
        if (getClean() > MAX_VALUE) {
            setClean(getClean() - (getClean() % 100));
        }
    }

    public void unWash() {
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