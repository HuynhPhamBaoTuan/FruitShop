package view;

import Controller.MnFruitShop;
import View.Menu;

public class Main {
    public static void main (String[] args){
        Menu mn = new Menu();
        mn.add("1. Create fruit");
        mn.add("2. View orders");
        mn.add("3. Shopping (for buyer)");
        mn.add("4. Exit");
        MnFruitShop MnFS = new MnFruitShop();
        int userChoice;
        do{
            mn.print();
            userChoice = mn.getUserChoices();
            switch(userChoice){
                case 1 -> MnFS.createProduct();
                case 2 -> MnFS.viewOrder();
                case 3 -> MnFS.shopping();
                case 4 -> System.exit(0);
            }
        } while(true);
    }
}
