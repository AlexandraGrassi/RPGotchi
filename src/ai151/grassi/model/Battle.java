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
        gotchi.setMoveDone(false);
        System.out.printf("Ход монстра...");
        Random random = new Random();
        attack = 1 + random.nextInt(3);
        switch (attack) {
            case 1:
                monster.attackLight(gotchi);
                System.out.println("Атака" + attack);
                if(!monster.isMoveDone()) {
                    System.out.println("Отдых");
                    monster.skipMove();
                }
                attack = 0;
                break;
            case 2:
                monster.attackMedium(gotchi);
                System.out.println("Атака" + attack);
                System.out.println(monster.getAttackInt());
                if(!monster.isMoveDone()) {
                    System.out.println("Отдых");
                    monster.skipMove();
                }
                attack = 0;
                break;
            case 3:
                monster.attackHard(gotchi);
                System.out.println("Атака" + attack);
                if(!monster.isMoveDone()) {
                    monster.skipMove();
                    System.out.println("Отдых");
                }
                attack = 0;
                break;
        }
        monster.skipMove();
        System.out.println(gotchi.getHp());
        System.out.println("Ход завершен...");
    }
}
