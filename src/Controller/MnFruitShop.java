package Controller;

import Model.Order;
import Common.Utilily;
import Model.Fruit;
import java.util.ArrayList;
import java.util.Hashtable;

public class MnFruitShop {
    protected int id = 0, fr_quantity;
    protected double price;
    protected String fr_name, origin;
    private ArrayList<Fruit> fr_list = new ArrayList<>();
    protected Manager manage = new Manager();
    protected Hashtable<String, ArrayList<Order>> ord_ht = new Hashtable<String, ArrayList<Order>>();
    
    
    public void createProduct(){
        Manager Mng = new Manager();
        int id = 0;
        boolean loop = true;
        do{
            id++;
            System.out.println("Create a new fruit: ");
            boolean loop2 = true;
            do{  
                 fr_name = Utilily.GetString("Enter fruit's name: ", false);
                 if(fr_list.size() >= 1){
                    for(Fruit fr : fr_list){
                        if(fr_name.equalsIgnoreCase(fr.getFr_name())){
                            loop2 = true;
                            System.out.println("This fruit has already existed!");
                            break;
                        } else loop2 = false;
                    }                   
                 } else loop2 = false;
               } while(loop2);
            origin = Utilily.GetString("Enter fruit's origin: ", false);
            fr_quantity = Utilily.getInt("Enter fruit's quantity: ");
            price = Utilily.getDouble("Enter fruit's price: ");
            Fruit fr = new Fruit(id, fr_quantity, price, fr_name, origin);
            fr_list.add(fr);
            if(Utilily.GetYesNo("Do you want to continue (Y/N)? ")==false) loop=false;
        } while(loop);
            Mng.displayCreatedFruit(fr_list);
    }
    
    public void shopping(){
        int select_item;
        int input_quantity;
        String fr_name = "";
        double price = 0;
        String customer = "";
        ArrayList<Order> ord_list = new ArrayList<>();
        Manager Mng = new Manager();
        boolean loop = true;
        do{
            Mng.displayShopFruit(fr_list);
            
            boolean loop1 = true;
            do{
               select_item = Utilily.getInt("Select item: ");
               loop1 = Mng.checkFruitbyItem(select_item, fr_list);    
            } while(loop1);
            
            for(Fruit fr : fr_list){
                if(select_item == fr.getFruitID()){
                    fr_name = fr.getFr_name();
                    price = fr.getPrice();
                }
            }
            
            boolean loop2 = true;
            do{
               input_quantity = Utilily.getInt("Please input quantity: ");
               loop2 = Mng.inputValidQuantity(select_item, fr_list, input_quantity);
            } while(loop2);
            
            for(Fruit fr : fr_list){
                if(select_item == fr.getFruitID()){
                    fr.setQuantity(fr.getQuantity()-input_quantity);
                }
            }
            
            Order ord = new Order(fr_name, input_quantity, price);
            ord_list.add(ord);
            
            if(Utilily.GetYesNo("Do you want to order now (Y/N)? ")==true){
               Mng.displayOrderList(ord_list);
               loop = false;
            } 
        } while(loop);
        customer = Utilily.GetString("Input your name: ", false);
        ord_ht.put(customer, ord_list);
    }
    
    public void viewOrder(){
        Manager Mng = new Manager();
        
        if(ord_ht.size()<1){
            System.out.println("There have not any orders yet!");
            return;
        }
        for(String name : ord_ht.keySet()){
            System.out.println("Customer: " + name);
            ArrayList<Order> ord_list = ord_ht.get(name);
            Mng.displayOrderList(ord_list);
        }
    }
}