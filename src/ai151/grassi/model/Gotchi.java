package ai151.grassi.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by AGrassi on 11.04.2017.
 */
public class Gotchi implements Observable {
    private final int MAX_VALUE = 100;
    private final int MIN_VALUE = 0;
    private final int MAX_LEVEL = 10;
    private final int MIN_LEVEL = 1;

    private ObservableIntegerValue level;
    private ObservableIntegerValue experience;
    private ObservableStringValue name;

    private ObservableIntegerValue stamina, agility, strength; //выносливость, ловкость, сила
    private ObservableIntegerValue energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

    private List<Observer> observers; // наблюдатель

    public Gotchi(String name, int stamina, int agility, int strength) {
        observers = new LinkedList<>();

        this.name = new SimpleStringProperty(name);
        this.level = new SimpleIntegerProperty(MIN_LEVEL);
        this.experience = new SimpleIntegerProperty(MIN_VALUE);

        this.energy = new SimpleIntegerProperty(MIN_VALUE);
        this.food = new SimpleIntegerProperty(MIN_VALUE);
        this.health = new SimpleIntegerProperty(MIN_VALUE);
        this.mood = new SimpleIntegerProperty(MIN_VALUE);
        this.clean = new SimpleIntegerProperty(MIN_VALUE);

        this.stamina = new SimpleIntegerProperty(stamina);
        this.agility = new SimpleIntegerProperty(agility);
        this.strength = new SimpleIntegerProperty(strength);
    }

    public String getName() {
        return name.get();
    }
    public int getLevel() {
        return level.intValue();
    }
    public int getExperience() { return experience.intValue(); }

    public int getEnergy() { return energy.intValue(); }
    public int getFood() {
        return food.intValue();
    }
    public int getHealth() {
        return health.intValue();
    }
    public int getMood() {
        return mood.intValue();
    }
    public int getClean() {
        return clean.intValue();
    }

    public int getStamina() {
        return stamina.intValue();
    }
    public int getAgility() {
        return agility.intValue();
    }
    public int getStrength() {
        return strength.intValue();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
        notifyObservers();
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.updateMainInfo(getLevel(), getExperience());

            observer.updateEnergy(getEnergy());
            observer.updateFood(getFood());
            observer.updateHealth(getHealth());
            observer.updateMood(getMood());
            observer.updateClean(getClean());

            observer.updateFightAbilities(getStamina(), getAgility(), getStrength());
        }
    }

   protected int increase(int min, int max, int value) {
        int range = max - min;
        int result = value;
        /*value =  new SimpleIntegerProperty(value.intValue() + (int) ((Math.random() * range) + min));*/
        result +=  (Math.random() * range) + min;
        return result;
    }

    public void sleep () {
        if (getEnergy() == MAX_VALUE) {
            System.out.println("-Не хочу спать-");
        }
        energy = new SimpleIntegerProperty( increase(5, 20, getEnergy() ) );
        if (getEnergy() > MAX_VALUE) {
            energy = new SimpleIntegerProperty(getEnergy() - (getEnergy()%100));
        }
        notifyObservers();
    }

    public void eat() {
        if (getFood() == MAX_VALUE) {
            System.out.println("-Не хочу есть-");
        }
        food = new SimpleIntegerProperty( increase(5, 15, getFood() ) );
        if (getFood() > MAX_VALUE) {
            food = new SimpleIntegerProperty(getFood() - (getFood() % 100));
        }
        notifyObservers();
    }

    public void treat() {
        if (getHealth() == MAX_VALUE) {
            System.out.println("-Не хочу лечиться-");
        }
        health = new SimpleIntegerProperty( increase(5, 10, getHealth() ) );
        if (getHealth() > MAX_VALUE) {
            health = new SimpleIntegerProperty(getHealth() - (getHealth() % 100));
        }
        notifyObservers();
    }

    public void makeHappy() {
        mood = new SimpleIntegerProperty( increase(1, 2, getMood() ) );
        if (getMood() > MAX_VALUE) {
            health = new SimpleIntegerProperty(getMood() - (getMood() % 100));
        }
        notifyObservers();
    }

    public void wash() {
        if (getClean() == MAX_VALUE) {
            System.out.println("-Не хочу купаться-");
        }
        clean = new SimpleIntegerProperty( increase(5, 20, getClean() ) );
        if (getClean() > MAX_VALUE) {
            clean = new SimpleIntegerProperty(getClean() - (getClean() % 100));
        }
        notifyObservers();
    }
}




/* protected void decreaseEnergy(int min, int max) {
        int range = max - min;
        this.energy -= (int) (Math.random() * range) + min;
}*/


