package main.ad;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdvertisementSelection {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();

    int[][] tempArray;
    int[] durationArray;
    int countVideos = storage.list().size();

    public void sortVideos(int timeSeconds) {
        durationArray = new int[countVideos + 1];
        for (int i = 0; i < countVideos; i++) {
            durationArray[i] = storage.list().get(i).getDuration();
        }

        tempArray = new int[countVideos + 1][];
        for (int i = 0; i < countVideos + 1; i++) {
            tempArray[i] = new int[timeSeconds + 1];
        }

        for (int j = 0; j <= countVideos; j++) {
            for (int k = 0; k <= timeSeconds; k++) {
                if (j == 0 || k == 0) {
                    tempArray[j][k] = 0;
                } else {
                    if (k >= durationArray[j - 1]) {
                        tempArray[j][k] = (int) Math.max(tempArray[j - 1][k], tempArray[j - 1][k - durationArray[j - 1]] + storage.list().get(j - 1).getAmountPerOneDisplaying());
                    } else {
                        tempArray[j][k] = tempArray[j - 1][k];
                    }
                }
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        List<Advertisement> test = new ArrayList<>();
        traceResult(tempArray, durationArray, countVideos, timeSeconds, result);

        for (Integer integer : result) {
            test.add(storage.list().get(integer));
        }
        test.sort(Comparator.comparing(Advertisement::getAmountPerOneDisplaying)
                .thenComparing((Advertisement::getAmountPerOneSec))
                .reversed());
        for (Advertisement adv : test) {
            System.out.println(adv.getName() + " is displaying... "
                    + adv.getAmountPerOneDisplaying() + ", "
                    + adv.getAmountPerOneDisplaying() * 1000 / adv.getDuration());
        }
        for (Integer integer : result) {
            storage.list().get(integer).revalidate();
        }

    }

    private static void traceResult(int[][] A, int [] weights, int  j, int k, ArrayList <Integer> result) {
        if (A[j][k] == 0) {
            return;
        } if (A[j - 1][k] == A[j][k]){
            traceResult(A, weights,j - 1,k,result);
        } else  {
            traceResult(A,weights, j - 1, k - weights[j - 1],result);
            result.add(0,j-1);
        }
    }
}
