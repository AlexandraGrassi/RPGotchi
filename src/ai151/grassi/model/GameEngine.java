package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

public class GameEngine {
    private Gotchi myGotchi;
    private Monster monster;
    private Fight battle;
    private LivingEngine livingEngine;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
        livingEngine = new LivingEngine();
        livingEngine.addGotchi(myGotchi);
    }

    public void freezeLivingEngine() {
        livingEngine.deleteGotchi();
    }

    public void sendSleep() {
        if(!myGotchi.isGone()) {
            if (myGotchi.getEnergy() == MAX_VALUE) {
                System.out.println("-Не хочу спать-");
            }
            myGotchi.setEnergy(myGotchi.getEnergy() + 0.07);
            if (myGotchi.getEnergy() > MAX_VALUE) {
                myGotchi.setEnergy(MAX_VALUE);
            }
        }
    }

    public void feed() {
        if(!myGotchi.isGone()) {
            if (myGotchi.getFood() == MAX_VALUE) {
                System.out.println("-Не хочу есть-");
            }
            myGotchi.setFood(myGotchi.getFood() + 0.06);
            if (myGotchi.getFood() > MAX_VALUE) {
                myGotchi.setFood( MAX_VALUE );
            }
        }
    }

    public void treat() {
        if(!myGotchi.isGone()) {
            if (myGotchi.getHealth() == MAX_VALUE) {
                System.out.println("-Не хочу лечиться-");
            }
            myGotchi.setHealth(myGotchi.getHealth() + 0.08);
            if (myGotchi.getHealth() > MAX_VALUE) {
                myGotchi.setHealth(MAX_VALUE);
            }
        }
    }

    public void makeHappy() {
        if(!myGotchi.isGone()) {
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
            if (myGotchi.getClean() == MAX_VALUE) {
                System.out.println("-Не хочу купаться-");
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

    public void startBattle() {
        battle = new Fight(myGotchi, getMonster());
    }

    public Fight getBattle() {
        return battle;
    }
    public Gotchi getMyGotchi() {
        return myGotchi;
    }
    public Monster getCurMonster() {
        return monster;
    }
}
