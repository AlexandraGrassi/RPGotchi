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
        /*while(!gotchi.isLose() || !monster.isLose()) {
            if(gotchi.isMoveDone()) {
                gotchi.setMoveDone(false);
                while(!monster.isMoveDone()) {
                    System.out.printf("Ход монстра...");
                    Random random = new Random();
                    attack = 1 + random.nextInt(3);
                    switch (attack) {
                        case 1: {
                            monster.attackLight(gotchi);
                            if(!monster.isMoveDone()) {
                                monster.skipMove();
                            }
                        }
                        case 2:
                            monster.attackMedium(gotchi);
                            if(!monster.isMoveDone()) {
                                monster.skipMove();
                            }
                            break;
                        case 3:
                            monster.attackHard(gotchi);
                            if(!monster.isMoveDone()) {
                                monster.skipMove();
                            }
                            break;
                    }
                }
                monster.setMoveDone(false);
            }
        }*/
    }

}
