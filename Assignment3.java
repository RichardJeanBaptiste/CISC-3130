/*
    Richard Jean-Baptiste
    14051497
    CISC-3130
*/

import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws FileNotFoundException {

        LinkedList<String> widgList = new LinkedList<String>();
        
        Scanner dataReader = new Scanner(new File("Data.txt"));

        float discountAmt = 0;
        int discountsLeft = 0;

        while(dataReader.hasNextLine()){
            String[] data = dataReader.nextLine().split(" ");
            

            if(data[0].equals("R")){
                widgList.add(data[1] + " " + data[2]);
                System.out.println(data[1] + " widgets recieved for " + data[2] + " each");
            }

            if(data[0].equals("P")){
                System.out.println("The next two customers will recieve a " + data[1] + " discount");
                discountAmt = (Integer.parseInt(data[1].substring(0, data[1].length() - 1)) / 100.0f);
                discountsLeft += 2;
            }

            if(data[0].equals("S")){
                int currAmt,newAmt;
                float price;
                float finalPrice = 0;
                String priceS;
                int amtToSell = Integer.parseInt(data[1]); 
                
                while(amtToSell != 0){
                    String y[] = widgList.getFirst().split(" ");
                    currAmt = Integer.parseInt(y[0]);
                    priceS = y[1];
                    price = Float.parseFloat(y[1]);

                    if(widgList.size() == 1){
                        if(amtToSell - currAmt <= 0){
                            newAmt = currAmt - amtToSell;
                            String z = Integer.toString(newAmt) + " " + priceS;
                            widgList.set(0,z);
                            float x = amtToSell * price;
                            finalPrice += x;
                            String finalString = String.format("%d widgets sold at $%s each  Sales:$%.2f",amtToSell,priceS,x);
                            System.out.println(finalString);
                            amtToSell = 0;
                        }else if(amtToSell - currAmt > 0){
                            newAmt = amtToSell - currAmt;
                            amtToSell = newAmt;
                            widgList.removeFirst();
                            float x = currAmt * price;
                            finalPrice += x;
                            String finalString = String.format("%d widgets sold at $%s each Sales:$%.2f",currAmt,priceS,x);
                            System.out.println(finalString);
                            System.out.println(newAmt + " Widgets not avaiable");
                            amtToSell = 0;
                        }
                    }else if(amtToSell - currAmt <= 0){
                        newAmt = currAmt - amtToSell;
                        String z = Integer.toString(newAmt) + " " + priceS;
                        widgList.set(0,z);
                        float x = amtToSell * price;
                        finalPrice += x;
                        String finalString = String.format("%d widgets sold at $%s each  Sales:$%.2f",amtToSell,priceS,x);
                        System.out.println(finalString);
                        amtToSell = 0;
                    }else if(amtToSell - currAmt > 0){
                        if(currAmt == 0){
                            widgList.removeFirst();
                            String a[] = widgList.getFirst().split(" ");
                            currAmt = Integer.parseInt(a[0]);
                            priceS = a[1];
                        }

                        newAmt = amtToSell - currAmt;
                        amtToSell = newAmt;
                        widgList.removeFirst();
                        float x = currAmt * price;
                        finalPrice += x;
                        String finalString = String.format("%d widgets sold at $%s each Sales:$%.2f",currAmt,priceS,x);
                        System.out.println(finalString);
                    }

                }

                if(discountsLeft != 0){
                    float discount = finalPrice * discountAmt;
                    finalPrice = finalPrice - discount;
                    String finalSaleString = String.format("Total Sales: $%.2f",finalPrice);
                    System.out.println(finalSaleString);
                    discountsLeft -= 1;
                }else{
                    String finalSaleString = String.format("Total Sales: $%.2f",finalPrice);
                    System.out.println(finalSaleString);
                }
                  
            }
           
        }
        System.out.println("-------------------------------------");
        System.out.println("Remaining Stock");
        System.out.println(" ");
        for(int i = 0; i < widgList.size(); i++){
            String[] widgArr = widgList.get(i).split(" ");
            System.out.println(widgArr[0] + " widgets left in stock at " + widgArr[1] + " each");

        }
        dataReader.close();
    }

}
