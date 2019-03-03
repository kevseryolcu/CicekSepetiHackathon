import java.util.ArrayList;

public class Seller {
    public String name;
    public double latitude;
    public double longitude;
    public ArrayList<Order> orderlist = new ArrayList<>();
    public int minQuota;
    public int maxQuota;


    public Seller(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void printSeller() {
        System.out.println("name: " + name + " latitude: " + latitude + " longitude: " + longitude);    }
}
