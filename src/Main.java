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
                System.out.println(line + "***");
                sellers.add(new Seller(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2])));

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
                System.out.println(line + "***");
                orders.add(new Order(Integer.parseInt(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2])));

            }
            inputStream.close();
        }catch (FileNotFoundException e){

            e.printStackTrace();
        }

    }
}
