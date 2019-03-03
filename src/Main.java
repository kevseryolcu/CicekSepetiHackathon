import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String [] args)
    {
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Seller> sellers = new ArrayList<Seller>();

        String sellerFile = "bayi.csv";
        String orderFile = "siparis.csv";

        //read seller info
        File file = new File(sellerFile);
        try{
            Scanner inputStream = new Scanner(file);

            while(inputStream.hasNext()){

                String line = inputStream.next();
                String[] data = line.split(";");
                //System.out.println(line + "***");
                Seller newSeller = new Seller(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                if(newSeller.name.equals("Kirmizi")) {
                    newSeller.maxQuota = 30;
                    newSeller.minQuota = 20;
                }
                else if(newSeller.name.equals("Yesil")) {
                    newSeller.maxQuota = 50;
                    newSeller.minQuota = 35;
                }
                else {
                    newSeller.maxQuota = 80;
                    newSeller.minQuota = 20;
                }

                sellers.add(newSeller);
            }
            inputStream.close();
        }catch (FileNotFoundException e){

            e.printStackTrace();
        }

        //read order info
        File fileOrder = new File(orderFile);
        try{
            Scanner inputStream = new Scanner(fileOrder);

            while(inputStream.hasNext()){

                String line = inputStream.next();
                String[] data = line.split(";");
                //System.out.println(line + "***");
                Order newOrder = new Order(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]));

                newOrder.distanceRed = findDistance(sellers.get(0), newOrder );
                newOrder.distanceGreen = findDistance(sellers.get(1), newOrder );
                newOrder.distanceBlue = findDistance(sellers.get(2), newOrder );

                orders.add(newOrder);
             

            }
            inputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        for(Order order: orders) {
            order.printOrder();
        }

        

    }

    public static double findDistance(Seller seller, Order order) {
        return Math.sqrt(Math.pow((seller.latitude - order.latitude), 2) + Math.pow((seller.longitude - order.longitude), 2));
    }
    
    public static void optimizedistance(ArrayList<orders> orders, ArrayList<Seller> sellers){ 

        
        for(Order o: orders){
            
          /*  o.distanceBlue;
            o.distanceRed;
            o.distanceGreen;*/
        
             double result =  Math.min(o.distanceBlue, Math.min(o.distanceRed, o.distanceGreen);
            
             if (result  == o.distanceBlue){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Mavi")){
                        seller.orderlist.add(o);
                    }
                }
             }
             if (result  == o.distanceGreen){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Yesil")){
                        seller.orderlist.add(o);
                    }
                }
             }
             if (result  == o.distanceRed){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Kirmizi")){
                        seller.orderlist.add(o);
                    }
                }
             }
            
        
        }

    }
}
