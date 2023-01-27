package main;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {
    List<Tablet> tabletList;
    int interval;

    public RandomOrderGeneratorTask(List<Tablet> tabletList,int interval) {
        this.tabletList = tabletList;
        this.interval = interval;
    }

    @Override
    public void run() {

        while (true){
            Tablet activeTablet = tabletList.get((int) (Math.random()*3));
            activeTablet.createTestOrder();
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
