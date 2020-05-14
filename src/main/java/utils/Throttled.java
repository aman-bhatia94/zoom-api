package utils;

public class Throttled {

    final static int SLEEP_TIME = 5000;

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


