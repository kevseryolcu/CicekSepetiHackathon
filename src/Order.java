public class Order {
    public int orderId;
    public double latitude;
    public double longitude;

    public Order (int orderId, double longitude, double latitude)  {
        this.orderId = orderId;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
