package main;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    List<Tablet> tabletList;
    int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets,int interval) {
        this.tabletList = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            Tablet activeTablet = tabletList.get((int) (Math.random()* tabletList.size()));
            activeTablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
