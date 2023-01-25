package main.ad;

import main.ConsoleHelper;

import java.util.ArrayList;
import java.util.List;

public class StatisticAdvertisementManager {
    public static StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public List<Advertisement> activeVideoSet() {
        List<Advertisement> allVideos = advertisementStorage.list();
        List<Advertisement> activeVideo = new ArrayList<>();
        for (Advertisement active: allVideos) {
            if (active.getHits() > 0) {
                activeVideo.add(active);
            }
        }
        sortListByName(activeVideo);
        return activeVideo;
    }

    public List<Advertisement> archivedVideoSet() {
        List<Advertisement> allVideos = advertisementStorage.list();
        List<Advertisement> archivedVideo = new ArrayList<>();
        for (Advertisement archived: allVideos) {
            if (archived.getHits() <= 0) {
                archivedVideo.add(archived);
            }
        }
        sortListByName(archivedVideo);
        return archivedVideo;
    }

    private void sortListByName(List<Advertisement> unsortedList) {
        unsortedList.sort((o1, o2) -> {
            int cmp = o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            if (cmp != 0){
                return cmp;
            }
            return cmp;
        });
    }

}
