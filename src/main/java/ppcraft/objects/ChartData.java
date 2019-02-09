package ppcraft.objects;

public class ChartData {
    private String address;
    private long time;
    private int ping;

    public ChartData(String address, long time, int ping){
        this.address = address;
        this.time = time;
        this.ping = ping;
    }

    public String getAddress() {
        return address;
    }

    public long getTime() {
        return time;
    }

    public int getPing() {
        return ping;
    }
}
