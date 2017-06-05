package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

public class GameEngine {
    private static Gotchi myGotchi;
    private Monster monster;
    private Battle battle;
    private LivingEngine livingEngine;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
        livingEngine = new LivingEngine();
        livingEngine.addGotchi(myGotchi);
    }

    public static Gotchi getMyGotchi() {
        return myGotchi;
    }

    public void freezeLivingEngine() {
        livingEngine.deleteGotchi();
    }

    public void sendSleep() {
        if(!myGotchi.isGone()) {
            myGotchi.setMainInfo("");
            if (myGotchi.getEnergy() == MAX_VALUE) {
                myGotchi.setMainInfo("Не хочу спать");
            }
            myGotchi.setEnergy(myGotchi.getEnergy() + 0.07);
            if (myGotchi.getEnergy() > MAX_VALUE) {
                myGotchi.setEnergy(MAX_VALUE);
            }
        }
    }

    public void feed() {
        if(!myGotchi.isGone()) {
            myGotchi.setMainInfo("");
            if (myGotchi.getFood() == MAX_VALUE) {
                myGotchi.setMainInfo("Не хочу есть");
            }
            myGotchi.setFood(myGotchi.getFood() + 0.06);
            if (myGotchi.getFood() > MAX_VALUE) {
                myGotchi.setFood( MAX_VALUE );
            }
        }
    }

    public void treat() {
        if(!myGotchi.isGone()) {
            myGotchi.setMainInfo("");
            if (myGotchi.getHealth() == MAX_VALUE) {
                myGotchi.setMainInfo("Не хочу лечиться");
            }
            myGotchi.setHealth(myGotchi.getHealth() + 0.08);
            if (myGotchi.getHealth() > MAX_VALUE) {
                myGotchi.setHealth(MAX_VALUE);
            }
        }
    }

    public void makeHappy() {
        if(!myGotchi.isGone()) {
            myGotchi.setMainInfo("");
            if (myGotchi.getMood() != MAX_VALUE) {
                myGotchi.setMood(myGotchi.getMood() + 0.02);
            }
            if (myGotchi.getMood() > MAX_VALUE) {
                myGotchi.setMood(MAX_VALUE);
            }
        }
    }

    public void wash() {
        if(!myGotchi.isGone()) {
            myGotchi.setMainInfo("");
            if (myGotchi.getClean() == MAX_VALUE) {
                myGotchi.setMainInfo("Не хочу купаться");
            }
            myGotchi.setClean(myGotchi.getClean() + 0.04);
            if (myGotchi.getClean() > MAX_VALUE) {
                myGotchi.setClean(MAX_VALUE);
            }
        }
    }

    public Monster getMonster() {
        monster =  MonsterFactory.createMonster(myGotchi.getSumOfAbilities());
        return monster;
    }
}
