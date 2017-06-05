package ai151.grassi.model;

import java.util.Random;

public class Battle {

    private Gotchi gotchi;
    private Monster monster;
    private int attack;

    public Battle(Gotchi gotchi, Monster monster) {
        this.gotchi = gotchi;
        this.monster = monster;
    }

    public void beginBattle() {
        System.out.println("-----");
        Random random = new Random();
        attack = 1 + random.nextInt(3);
        switch (attack) {
            case 1:
                monster.attackLight(gotchi);
                if(!monster.isMoveDone()) {
                    monster.skipMove(gotchi);
                }
                attack = 0;
                break;
            case 2:
                monster.attackMedium(gotchi);
                if(!monster.isMoveDone()) {
                    monster.skipMove(gotchi);
                }
                attack = 0;
                break;
            case 3:
                monster.attackHard(gotchi);
                if(!monster.isMoveDone()) {
                    monster.skipMove(gotchi);
                }
                attack = 0;
                break;
        }
    }
}
