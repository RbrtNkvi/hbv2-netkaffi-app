package hi.netkaffi.entities;

import java.util.List;

public class Booking {

    private List<User> uName;
    private List<Product> pName;
    private long startTime;

    public Booking() {
    }

    public Booking(List<User> uName, List<Product> pName, long startTime) {
        this.uName = uName;
        this.pName = pName;
        this.startTime = startTime;
    }

    public List<User> getuName() {
        return uName;
    }

    public void setuName(List<User> uName) {
        this.uName = uName;
    }

    public List<Product> getpName() {
        return pName;
    }

    public void setpName(List<Product> pName) {
        this.pName = pName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void getEndTime() { // Skilar long
        //TODO: Klára fall;
    }
}
