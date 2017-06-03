package ai151.grassi.model;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LivingEngine {

    private Gotchi myGotchi;
    private LivingThread lv;
    final Lock lock = new ReentrantLock();
    final Condition notNull  = lock.newCondition();

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
            while (myGotchi != null) {
                lock.lock();
                try {
                    notNull.await(1000, TimeUnit.MILLISECONDS);
                    if (myGotchi != null) {
                        myGotchi.becomeSleepy();
                        myGotchi.becomeHungry();
                        myGotchi.becomeSad();
                        myGotchi.becomeDirty();
                        myGotchi.becomeSick();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
