package main.ad;

public class Advertisement {
    private Object content; // video
    private String name; // video name
    private long initialAmount; // stating sum, ad cost in penny
    private int hits; // count of paid showing
    private int duration; // duration :)
    private long amountPerOneDisplaying;



    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if (hits > 0) {
            this.amountPerOneDisplaying = initialAmount / hits;
        } else {
            this.amountPerOneDisplaying = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getAmountPerOneSec() {
        return (getAmountPerOneDisplaying() * 1000) / getDuration();
    }
    public int getHits() {
        return hits;
    }

    public void revalidate(){
        if (hits <= 0) {
            throw new UnsupportedOperationException();
        }
        hits--;
    }
}
