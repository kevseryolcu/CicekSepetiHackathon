public class Order {
    public int orderId;
    public double latitude;
    public double longitude;
    public double distanceRed;
    public double distanceGreen;
    public double distanceBlue;
    

    public Order (int orderId, double latitude, double longitude)  {
        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
        // this.distanceRed = distanceRed;
        // this.distanceGreen = distanceGreen;
        // this.distanceBlue = distanceBlue;
    }
    public void printOrder() {
        System.out.println(
        "name: " + orderId
        + " latitude: " + latitude 
        + " longtidute: " + longitude 
        + " distanceRed " + distanceRed
        + " distanceGreen " + distanceGreen
        + " distanceBlue " + distanceBlue
        );    
    }
}
