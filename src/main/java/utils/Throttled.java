package utils;

public class Throttled {


    int  interval; // the time to between each consecutive set of 10 requests in milliseconds, slower than documented rate
    int countConsecutiveRequest;
    long lastRequestedTime;

    public Throttled(){
        this.lastRequestedTime = System.currentTimeMillis();
        this.countConsecutiveRequest = 1;
        this.interval = 500;
    }

    public void throttle(){
        if(System.currentTimeMillis() - lastRequestedTime <= interval && countConsecutiveRequest >= 10){
            try {
                Thread.sleep(1000); // sleep for 1 second
                countConsecutiveRequest = 1;
                this.lastRequestedTime = System.currentTimeMillis();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //record the last RequestedTime
        //this.lastRequestedTime = System.currentTimeMillis();
        this.countConsecutiveRequest++;
    }



}
