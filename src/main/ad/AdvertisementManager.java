package main.ad;

import main.ConsoleHelper;

public class AdvertisementManager {

    private final AdvertisementStorage storage = new AdvertisementStorage();
    public int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){
        ConsoleHelper.writeMessage("calling processVideos method");
    }
}
