package main.ad;

public class AdvertisementManager {

    private final AdvertisementStorage storage = new AdvertisementStorage();
    public int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos(){
        if (storage.list().isEmpty()){
            throw new NoVideoAvailableException();
        }
    }
}
