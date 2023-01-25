package main.ad;

import main.ConsoleHelper;
import main.statistic.StatisticManager;
import main.statistic.event.VideoSelectedEventDataRow;

import java.util.List;

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
        List<Advertisement> optimalSortedVideo = selection.getAdvertisements();
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(optimalSortedVideo,selection.getTotalPrice(),selection.getTotalDuration()));
        for (Advertisement adv : optimalSortedVideo) {
            ConsoleHelper.writeMessage(adv.getName() + " is displaying... "
                    + adv.getAmountPerOneDisplaying() + ", "
                    + adv.getAmountPerOneSec());

            adv.revalidate();
        }
    }
}
