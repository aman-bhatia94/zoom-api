package utils;

public class Throttled {

    final static int SLEEP_TIME = 3000;
    int interval; // the time to between each consecutive set of 10 requests in milliseconds, slower than documented rate
    int countConsecutiveRequest;
    long lastRequestedTime;

    public Throttled() {
        this.lastRequestedTime = System.currentTimeMillis();
        this.countConsecutiveRequest = 1;
        this.interval = 1000;
    }

    public void throttle() {
//         Zoom has been rapidly changing
//         their code the rate limits have also been affected, so we pause between each request
        try {
            //sleep between every call
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


