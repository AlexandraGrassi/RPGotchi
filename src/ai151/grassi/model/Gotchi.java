package ai151.grassi.model;

import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;

/**
 * Created by AGrassi on 11.04.2017.
 */
public abstract class Gotchi {
    private final int MAX_VALUE = 100;
    private final int MIN_VALUE = 0;
    private final int MAX_LEVEL = 10;
    private final int MIN_LEVEL = 1;


    private ObservableIntegerValue level;
    private ObservableIntegerValue experience;
    private ObservableStringValue name;

    private ObservableIntegerValue stamina, agility, strength; //выносливость, ловкость, сила
    private ObservableIntegerValue energy, food, health, mood, clean; // энергия, здоровье, настроение, чистота, сытость

   /* Gotchi(ObservableStringValue name, ObservableIntegerValue stamina, ObservableIntegerValue agility, ObservableIntegerValue strength) {
        this.name = name;
        this.stamina = stamina;
        this.agility = agility;
        this.strength = strength;
        level =
        energy = 100;
        food = 100;
        health = 100;
        mood = 100;
        clean = 100;
    }

    protected void decreaseEnergy(int min, int max) {
        int range = max - min;
        this.energy -= (int) (Math.random() * range) + min;
    }

    protected void increaseEnergy(int min, int max) {
        int range = max - min;
        this.energy += (int) (Math.random() * range) + min;
    }

    public void sleep () {
        if (getEnergy() == 100) {
            System.out.println("-Не хочу спать-");
        }
        increaseEnergy(20, 80);
        if (energy > 100) {
            energy -= energy % 100;
        }
    }

*//*    public void eat() {
        if (getFood() == 100) {
            System.out.println("-Не хочу есть-");
        }
        increase(5, 40);
        if (food > 100) {
            food -= food % 100;
        }
    }

    public void takeShower() {
        if (getClean() == 100) {
            System.out.println("-Не хочу купаться-");
        }
        increase(5, 30);
        if (clean > 100) {
            clean -= clean % 100;
        }
    }*//*
*/
    public abstract String play();
}
