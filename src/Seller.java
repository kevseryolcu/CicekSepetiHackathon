public class Seller {
    public String name;
    public double latitude;
    public double longitude;

    public Seller(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public void printSeller() {
        System.out.println("name: " + name + " latitude: " + latitude + " longtidute: " + longitude);    }
}
