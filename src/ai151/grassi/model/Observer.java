package ai151.grassi.model;

/**
 * Created by AGrassi on 24.04.2017.
 */
public interface Observer {
    void updateMainInfo(int level, int experience); // обносвляет уровень и опыт Готчи

    // обносвляет потребности Готчи
    void updateEnergy(int energy);
    void updateFood(int food);
    void updateHealth(int health);
    void updateMood(int mood);
    void updateClean(int clean);

    void updateFightAbilities(int stamina, int agility, int strength); // обносвляет потребности Готчи
}
