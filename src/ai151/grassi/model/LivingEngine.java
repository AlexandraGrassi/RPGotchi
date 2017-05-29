package ai151.grassi.model;

public class LivingEngine {

    private Gotchi myGotchi;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
    }
    public Gotchi deleteGotchi() {
        myGotchi = null;
        return myGotchi;
    }

    public LivingEngine() {
        LivingThread lv = new LivingThread();
        lv.start();
    }

    class LivingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (myGotchi != null) {
                    try {
                        Thread.sleep(1000);
                        myGotchi.becomeSleepy();
                        myGotchi.becomeHungry();
                        myGotchi.becomeSad();
                        myGotchi.becomeDirty();
                        myGotchi.becomeSick();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
