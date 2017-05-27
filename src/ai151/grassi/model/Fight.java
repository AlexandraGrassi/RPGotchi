package ai151.grassi.model;

import java.util.Random;

public class Fight {

    private Gotchi gotchi;
    private Monster monster;
    private int attack;

    public Fight(Gotchi gotchi, Monster monster) {
        this.gotchi = gotchi;
        this.monster = monster;
    }

    public void battle() {
        /*while(!gotchi.isLose() || !monster.isLose()) {
            System.out.printf("Ход монстра...");
            Random random = new Random();
            attack = 1 + random.nextInt(3);
            switch (attack) {
                case 1:
                    monster.attackLight(gotchi);
                case 2:
                    monster.attackMedium(gotchi);
                case 3:
                    monster.attackHard(gotchi);
            }
        }*/
    }

}
