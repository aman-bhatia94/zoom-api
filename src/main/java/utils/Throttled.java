package utils;

public class Throttled {


    int interval; // the time to between each consecutive set of 10 requests in milliseconds, slower than documented rate
    int countConsecutiveRequest;
    long lastRequestedTime;

    final static int REQUEST_PER_SECOND = 2;
    final static int SLEEP_TIME = 3000;

    public Throttled() {
        this.lastRequestedTime = System.currentTimeMillis();
        this.countConsecutiveRequest = 1;
        this.interval = 1000;
    }

    public void throttle() {
        /**
         * The following code before ---xx---- mark worked well but since zoom has been rapidly changing
         * their code the rate limits have also been affected, so we pause between each request
         */
        /*if (System.currentTimeMillis() - lastRequestedTime <= interval && countConsecutiveRequest >= REQUEST_PER_SECOND) {
            try {
                Thread.sleep(SLEEP_TIME); // sleep
                countConsecutiveRequest = 1;
                this.lastRequestedTime = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            try {
                //sleep between every call
                Thread.sleep(SLEEP_TIME);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //record the last RequestedTime
        //this.lastRequestedTime = System.currentTimeMillis();
        //this.countConsecutiveRequest++;
    }


