package ai151.grassi.model;

import java.util.List;

/**
 * Created by AGrassi on 11.04.2017.
 */
public class LivingEngine {

    // Синглтон

    private List<Gotchi> gotchi;

    public void addGotch() {

    }

    public void deleteGotchi() {
        //St
    }

    public LivingEngine() {
        new LivingThread().run();
    }

    class LivingThread extends Thread {

        public void run() {
           /* for (Gotchi g : gotchi) {
                g.decreaseEnergy(10, 20);
            }*/

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
