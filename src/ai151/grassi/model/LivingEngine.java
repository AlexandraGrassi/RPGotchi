package ai151.grassi.model;

import java.util.concurrent.locks.Lock;

public class LivingEngine {

    private Gotchi myGotchi;
    private LivingThread lv;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
    }

    public void deleteGotchi() {

        myGotchi = null;
    }

    public LivingEngine() {
        lv = new LivingThread();
        lv.start();
    }

    class LivingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (myGotchi != null) {
                    myGotchi.becomeSleepy();
                    myGotchi.becomeHungry();
                    myGotchi.becomeSad();
                    myGotchi.becomeDirty();
                    myGotchi.becomeSick();
                }
            }
        }
    }
}
