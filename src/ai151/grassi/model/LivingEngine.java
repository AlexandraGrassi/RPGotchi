package ai151.grassi.model;

public class LivingEngine {

    // Синглтон
    private Gotchi myGotchi;

    public void addGotchi(Gotchi gotchi) {
        myGotchi = gotchi;
    }

    public void deleteGotchi() {
        myGotchi = null;
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
                        // TODO - добавить потоки для понижения каждой потребности в разное время
                        Thread.sleep(5000);
                        myGotchi.unSleep();
                        myGotchi.unEat();
                        myGotchi.unHappy();
                        myGotchi.unWash();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
