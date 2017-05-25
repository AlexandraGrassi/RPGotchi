package ai151.grassi.model;
import static ai151.grassi.model.GameConstants.*;

public class GameEngine {
    private Gotchi myGotchi;
    private Monster monster;
    private Fight battle;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
    }

    public void sendSleep() {
        if (myGotchi.getEnergy() == MAX_VALUE) {
            System.out.println("-Не хочу спать-");
        }
        myGotchi.setEnergy(myGotchi.getEnergy() + 7);
        if (myGotchi.getEnergy() > MAX_VALUE) {
            myGotchi.setEnergy(myGotchi.getEnergy() - (myGotchi.getEnergy() % 100));
        }
    }

    public void feed() {
        if (myGotchi.getFood() == MAX_VALUE) {
            System.out.println("-Не хочу есть-");
        }
        myGotchi.setFood(myGotchi.getFood() + 6);
        if (myGotchi.getFood() > MAX_VALUE) {
            myGotchi.setFood(myGotchi.getFood() - (myGotchi.getFood() % 100) );
        }
    }

    public void treat() {
        if (myGotchi.getHealth() == MAX_VALUE) {
            System.out.println("-Не хочу лечиться-");
        }
        myGotchi.setHealth(myGotchi.getHealth() + 8);
        if (myGotchi.getHealth() > MAX_VALUE) {
            myGotchi.setHealth(myGotchi.getHealth() - (myGotchi.getHealth() % 100) );
        }
    }

    public void makeHappy() {
        if (myGotchi.getMood() != MAX_VALUE) {
            myGotchi.setMood(myGotchi.getMood() + 2);
        }
        if (myGotchi.getMood() > MAX_VALUE) {
            myGotchi.setHealth(myGotchi.getHealth() - (myGotchi.getHealth() % 100));
        }
    }


    public void wash() {
        if (myGotchi.getClean() == MAX_VALUE) {
            System.out.println("-Не хочу купаться-");
        }
        myGotchi.setClean(myGotchi.getClean() + 4);
        if (myGotchi.getClean() > MAX_VALUE) {
            myGotchi.setClean(myGotchi.getClean() - (myGotchi.getClean() % 100));
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
