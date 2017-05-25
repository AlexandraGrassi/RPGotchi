package ai151.grassi.model;

import java.util.Random;

public class MonsterFactory {

    private static int monsterType;
    private static int averageAbility;
    private static int minAbility;
    private static int maxAbility;

    public static Monster createMonster(int sumOfAbilities){
        averageAbility = sumOfAbilities/3;
        minAbility = averageAbility - 5;
        maxAbility = averageAbility + 5;
        Random random = new Random();
        monsterType = 1 + random.nextInt(3);
        switch (monsterType) {
            case 1:
                return new StoneMonster("Камушек", maxAbility, averageAbility, minAbility);
            case 2:
                return new WaterMonster("Капелька", averageAbility, maxAbility, minAbility);
            case 3:
                return new FireMonster("Исккорка", minAbility, averageAbility, maxAbility);
            default:
                return null;
        }
    }
}
