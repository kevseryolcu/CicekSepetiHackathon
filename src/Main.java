import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import  java.awt.Desktop;
import java.net.URI;

public class Main {
    public static void main(String [] args) throws IOException, URISyntaxException {
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

        /*for(Order order: orders) {
            order.printOrder();
        }*/

        optimizedistance(orders, sellers);
        System.out.println("############################");
        sellers.get(0).printSeller();
        for(Order order: sellers.get(0).orderlist) {
            order.printOrder();
        }

        System.out.println("############################");
        sellers.get(1).printSeller();
        for(Order order: sellers.get(1).orderlist) {
            order.printOrder();
        }

        System.out.println("############################");
        sellers.get(2).printSeller();
        for(Order order: sellers.get(2).orderlist) {
            order.printOrder();
        }

        printOptimumDistances(sellers);
        displayMap();

    }
    public static void displayMap() throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.google.com/maps/d/u/0/viewer?hl=tr&mid=13o-td513WZiKJrIJHgI-eyL-raaJYGsi&ll=41.06724490576909%2C29.01614500000005&z=13"));
    }

    public static double findDistance(Seller seller, Order order) {
        return Math.sqrt(Math.pow((seller.latitude - order.latitude), 2) + Math.pow((seller.longitude - order.longitude), 2));
    }

    public static void optimizedistance(ArrayList<Order> orders, ArrayList<Seller> sellers){


        for(Order o: orders){

          /*  o.distanceBlue;
            o.distanceRed;
            o.distanceGreen;*/

            double result =  Math.min(o.distanceBlue, Math.min(o.distanceRed, o.distanceGreen));

            if (result  == o.distanceBlue){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Mavi")){
                        seller.orderlist.add(o);
                    }
                }
            }
            else if (result  == o.distanceGreen){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Yesil")){
                        seller.orderlist.add(o);
                    }
                }
            }
            else if (result  == o.distanceRed){
                for(Seller seller: sellers ) {
                    if (seller.name.equals("Kirmizi")){
                        seller.orderlist.add(o);
                    }
                }
            }
        }

        fill_quota(sellers);
        opt_quota(sellers);

    }

    public static void fill_quota(ArrayList<Seller> sellers){

        for(Seller seller: sellers){
            while(seller.orderlist.size() < seller.minQuota){
                double min = 10000;
                double temp;
                Order second_min = null;

                if(seller.name.equals("Kirmizi")){
                    if(sellers.get(1).orderlist.size() > sellers.get(1).minQuota){
                        for(Order o : sellers.get(1).orderlist){
                            temp = o.distanceGreen - o.distanceRed;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    if(sellers.get(2).orderlist.size() > sellers.get(2).minQuota){
                        for(Order o : sellers.get(2).orderlist){
                            temp = o.distanceBlue - o.distanceRed;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    sellers.get(1).orderlist.remove(second_min);
                    sellers.get(2).orderlist.remove(second_min);
                    sellers.get(0).orderlist.add(second_min);
                }
                else if(seller.name.equals("Yesil")){
                    if(sellers.get(0).orderlist.size() > sellers.get(0).minQuota){
                        for(Order o : sellers.get(0).orderlist){
                            temp = o.distanceBlue - o.distanceGreen;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    if(sellers.get(2).orderlist.size() > sellers.get(2).minQuota){
                        for(Order o : sellers.get(2).orderlist){
                            temp = o.distanceRed - o.distanceGreen;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    sellers.get(0).orderlist.remove(second_min);
                    sellers.get(2).orderlist.remove(second_min);
                    sellers.get(1).orderlist.add(second_min);
                }
                else if(seller.name.equals("Mavi")){
                    if(sellers.get(1).orderlist.size() > sellers.get(1).minQuota){
                        for(Order o : sellers.get(1).orderlist){
                            temp = o.distanceGreen - o.distanceBlue;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    if(sellers.get(0).orderlist.size() > sellers.get(0).minQuota){
                        for(Order o : sellers.get(0).orderlist){
                            temp = o.distanceRed - o.distanceBlue;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    sellers.get(1).orderlist.remove(second_min);
                    sellers.get(0).orderlist.remove(second_min);
                    sellers.get(2).orderlist.add(second_min);
                }
            }
        }
    }

    public static void opt_quota(ArrayList<Seller> sellers){

        for(Seller seller: sellers){
            while(seller.orderlist.size() > seller.maxQuota){
                double min = 10000;
                double temp;
                Order second_min = null;
                int flag = 2;

                if(seller.name.equals("Kirmizi")){
                    for(Order o : sellers.get(0).orderlist){
                        temp = o.distanceBlue - o.distanceRed;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 0;
                        }
                        temp = o.distanceGreen - o.distanceRed;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 1;
                        }
                    }

                    sellers.get(0).orderlist.remove(second_min);

                    if(flag == 1){
                        sellers.get(1).orderlist.add(second_min);
                    }
                    else{
                        sellers.get(2).orderlist.add(second_min);
                    }
                }
                else if(seller.name.equals("Yesil")){
                    for(Order o : sellers.get(1).orderlist){
                        temp = o.distanceBlue - o.distanceGreen;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 0;
                        }
                        temp = o.distanceRed - o.distanceGreen;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 1;
                        }
                    }

                    sellers.get(1).orderlist.remove(second_min);

                    if(flag == 1){
                        sellers.get(0).orderlist.add(second_min);
                    }
                    else{
                        sellers.get(2).orderlist.add(second_min);
                    }
                }
                else if(seller.name.equals("Mavi")){
                    for(Order o : sellers.get(2).orderlist){
                        temp = o.distanceRed - o.distanceBlue;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 0;
                        }
                        temp = o.distanceGreen - o.distanceBlue;
                        if(min > temp){
                            min = temp;
                            second_min = o;
                            flag = 1;
                        }
                    }

                    sellers.get(2).orderlist.remove(second_min);

                    if(flag == 1){
                        sellers.get(1).orderlist.add(second_min);
                    }
                    else{
                        sellers.get(0).orderlist.add(second_min);
                    }
                }
            }
        }
    }

    public static void printOptimumDistances(ArrayList<Seller> sellers)
    {
        System.out.println("############################");
        double totalDistanceRed = 0;
        for(Order order: sellers.get(0).orderlist) {
            totalDistanceRed += order.distanceRed;
        }
        System.out.println("Total distance of red: " + totalDistanceRed);

        System.out.println("############################");
        double totalDistanceGreen = 0;
        for(Order order: sellers.get(1).orderlist) {
            totalDistanceGreen += order.distanceGreen;
        }
        System.out.println("Total distance of green: " + totalDistanceGreen);

        System.out.println("############################");
        double totalDistanceBlue = 0;
        for(Order order: sellers.get(2).orderlist) {
            totalDistanceBlue += order.distanceBlue;
        }
        System.out.println("Total distance of blue: " + totalDistanceBlue);
    }
}
