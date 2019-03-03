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

    static void fill_quota(ArrayList<Seller> sellers){
       
        for(Seller seller: sellers){
            while(seller.orderlist.size() < seller.minQuota){
                double min = 10000;
                double temp;
                Order second_min;

                if(seller.name.equals("Kirmizi")){
                    if(seller[1].orderlist.size() > seller[1].minQuota){
                        for(Order o : sellers[1].orderlist){
                            temp = o.distanceGreen - o.distanceRed;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    if(seller[2].orderlist.size() > seller[2].minQuota){
                        for(Order o : sellers[2].orderlist){
                            temp = o.distanceBlue - o.distanceRed;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }
                    sellers[1].orderlist.remove(second_min);
                    sellers[2].orderlist.remove(second_min);
                    sellers[0].orderlist.add(second_min);
                }
                else if(seller.name.equals("Yesil")){
                    if(seller[0].orderlist.size() > seller[0].minQuota){
                        for(Order o : sellers[0].orderlist){
                            temp = o.distanceBlue - o.distanceGreen;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }    
                    if(seller[2].orderlist.size() > seller[2].minQuota){    
                        for(Order o : sellers[2].orderlist){
                            temp = o.distanceRed - o.distanceGreen;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }    
                    sellers[0].orderlist.remove(second_min);
                    sellers[2].orderlist.remove(second_min);
                    sellers[1].orderlist.add(second_min);
                }
                else if(seller.name.equals("Mavi")){
                    if(seller[1].orderlist.size() > seller[1].minQuota){
                        for(Order o : sellers[1].orderlist){
                            temp = o.distanceGreen - o.distanceBlue;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }    
                    if(seller[0].orderlist.size() > seller[0].minQuota){    
                        for(Order o : sellers[0].orderlist){
                            temp = o.distanceRed - o.distanceBlue;
                            if(min > temp){
                                min = temp;
                                second_min = o;
                            }
                        }
                    }    
                    sellers[1].orderlist.remove(second_min);
                    sellers[0].orderlist.remove(second_min);
                    sellers[2].orderlist.add(second_min);
                }
            }    
        }
    }

    static void opt_quota(ArrayList<Seller> sellers){
       
        for(Seller seller: sellers){
            while(seller.orderlist.size() > seller.maxQuota){
                double min = 10000;
                double temp;
                Order second_min;
                int flag = 2;

                if(seller.name.equals("Kirmizi")){
                    for(Order o : sellers[0].orderlist){
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
                    
                    sellers[0].orderlist.remove(second_min);

                    if(flag){
                        sellers[1].orderlist.add(second_min);
                    }    
                    else{
                        sellers[2].orderlist.add(second_min);
                    }    
                }
                else if(seller.name.equals("Yesil")){
                    for(Order o : sellers[1].orderlist){
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
                    
                    sellers[1].orderlist.remove(second_min);

                    if(flag){
                        sellers[0].orderlist.add(second_min);
                    }    
                    else{
                        sellers[2].orderlist.add(second_min);
                    }    
                }
                else if(seller.name.equals("Mavi")){
                    for(Order o : sellers[2].orderlist){
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
                    
                    sellers[2].orderlist.remove(second_min);

                    if(flag){
                        sellers[1].orderlist.add(second_min);
                    }    
                    else{
                        sellers[0].orderlist.add(second_min);
                    }    
                }
                }
            }    
        }
    }
}
