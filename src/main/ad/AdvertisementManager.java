package main.ad;

public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    public int timeSeconds;
    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {
        if (storage.list().isEmpty()){
            throw new NoVideoAvailableException();
        }
        AdvertisementSelection selection = new AdvertisementSelection();
        selection.sortVideos(timeSeconds);
    }
}
