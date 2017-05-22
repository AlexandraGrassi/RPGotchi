package ai151.grassi.model;

import java.util.Random;

public class MonsterFactory {

    private int monsterType;
    private int averageAbility;
    private int minAbility;
    private int maxAbility;

    public Monster createMonster(int sumOfAbilities){
        averageAbility = sumOfAbilities/3;
        minAbility = averageAbility - 5;
        maxAbility = averageAbility + 5;
        Random random = new Random();
        monsterType = random.nextInt(1001);
        switch (monsterType) {
            case 1:
                new StoneMonster("Камушек", maxAbility, averageAbility, minAbility);
                break;
            case 2:
                new WaterMonster("Капелька", averageAbility, maxAbility, minAbility);
                break;
            case 3:
                new FireMonster("Исккорка", minAbility, averageAbility, maxAbility);
                break;
        }
        return null;
    }
}
