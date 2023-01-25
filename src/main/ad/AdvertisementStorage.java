package main.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    public AdvertisementStorage() {

        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min, 50
        add(new Advertisement(someContent, "second Video", 100, 10, 15 * 60)); //15 min, 10
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min, 200
        add(new Advertisement(someContent, "четвертое видео", 3400, 20, 22 * 60)); //22 min, 170
        add(new Advertisement(someContent, "Fifth Video", 3400, 17, 14 * 60)); //14 min, 200

    }

    private static AdvertisementStorage instance = new AdvertisementStorage();
    private final List <Advertisement> videos = new ArrayList<>();

    public static AdvertisementStorage getInstance() {
        if (instance == null) {
            instance = new AdvertisementStorage();
        }
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);

    }

}
