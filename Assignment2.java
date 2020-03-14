import java.io.*;
import java.text.ParseException;
import java.util.*;

class Warehouse {

    String city;
    int amt1,amt2,amt3;

    public Warehouse(String city, int amt1, int amt2, int amt3){
        this.city = city;
        this.amt1 = amt1;
        this.amt2 = amt2;
        this.amt3 = amt3;
    }

    public void setAmt1(int newAmt){
        this.amt1 = newAmt;
    }

    public void setAmt2(int newAmt){
        this.amt2 = newAmt;
    }

    public void setAmt3(int newAmt){
        this.amt3 = newAmt;
    }

    public int getAm1(){
        return amt1;
    }

    public int getAm2(){
        return amt2;
    }

    public int getAm3(){
        return amt3;
    }

    public String getCity(){
        return city;
    }
   
}
class Main {

    public static void main(String[] args)throws IOException,ParseException,ArrayIndexOutOfBoundsException {

        Float price1,price2,price3;
        int amt1,amt2,amt3;
        int newAmt1 = 0;
        int newAmt2 = 0;
        int newAmt3 = 0;
        String city = "";
        String amounts = "";
        
        List<Warehouse> listOfWarehouses = new ArrayList<Warehouse>();
        listOfWarehouses.add(new Warehouse("New York", 0, 0, 0));
        listOfWarehouses.add(new Warehouse("Miami", 0, 0, 0));
        listOfWarehouses.add(new Warehouse("Los Angeles", 0, 0, 0));
        listOfWarehouses.add(new Warehouse("Houston", 0, 0, 0));
        listOfWarehouses.add(new Warehouse("Chicago", 0, 0, 0));

        File dataFile = new File("Data.txt");
        Scanner dataReader = new Scanner(new FileReader(dataFile));

        // get prices
        String[] prices = dataReader.nextLine().split("Price ");
        price1 = Float.parseFloat(prices[1].substring(3));
        price2 = Float.parseFloat(prices[2].substring(3));
        price3 = Float.parseFloat(prices[3].substring(3));

        while(dataReader.hasNextLine()){
            String fullData = dataReader.nextLine();
            String[] dataLine = fullData.split(" ");
            
            if(dataLine[0].equals("s")){
                System.out.println(fullData);
                //get the city 
                city = fullData.replaceAll("\\d","").substring(2).trim();
                //get the amounts
                amounts = fullData.replaceAll("\\b[\\d.]*[^ \\d.]+[\\d.]*\\b", "").replaceAll("  +", " ").trim();
                String[] eachAmount = amounts.split(" ");
                amt1 = Integer.parseInt(eachAmount[0]);
                amt2 = Integer.parseInt(eachAmount[1]);
                amt3 = Integer.parseInt(eachAmount[2]);
                //update array
                for(int i = 0; i < listOfWarehouses.size(); i++){
                    if(listOfWarehouses.get(i).getCity().equals(city)){
                        newAmt1 = listOfWarehouses.get(i).getAm1() + amt1;
                        newAmt2 = listOfWarehouses.get(i).getAm2() + amt2;
                        newAmt3 = listOfWarehouses.get(i).getAm3() + amt3;
                        listOfWarehouses.get(i).setAmt1(newAmt1);
                        listOfWarehouses.get(i).setAmt2(newAmt2);
                        listOfWarehouses.get(i).setAmt3(newAmt3);
                        System.out.println(city + " " + newAmt1 + " " + newAmt2 + " " + newAmt3);
                    }
                }    
            }else if(dataLine[0].equals("o")){
                float sale1 = 0;
                float sale2 = 0;
                float sale3 = 0;

                System.out.println(fullData);

                amounts = fullData.replaceAll("\\b[\\d.]*[^ \\d.]+[\\d.]*\\b", "").replaceAll("  +", " ").trim();
                String[] eachAmount = amounts.split(" ");
                amt1 = Integer.parseInt(eachAmount[0]);
                amt2 = Integer.parseInt(eachAmount[1]);
                amt3 = Integer.parseInt(eachAmount[2]);

                //get city
                city = fullData.replaceAll("\\d","").substring(2).trim();
                for(int i = 0; i < listOfWarehouses.size(); i++){
                   if(listOfWarehouses.get(i).getCity().equals(city)){
                    Warehouse currHouse = listOfWarehouses.get(i);
                    String currCity = currHouse.getCity();
                    int currAmt1 = currHouse.getAm1();
                    int currAmt2 = currHouse.getAm2();
                    int currAmt3 = currHouse.getAm3();
                    int maxAmount = 0;
                    int amtNeeded = 0;
                    String maxCity = "";
                    boolean unfilled = false;
            
                    if(currAmt1 > amt1 && currAmt2 > amt2 && currAmt3 > amt3){
                        int tempAmt1 = currAmt1 - amt1;
                        int tempAmt2 = currAmt2 - amt2;
                        int tempAmt3 = currAmt3 - amt3;
                        currHouse.setAmt1(tempAmt1);
                        currHouse.setAmt2(tempAmt2);
                        currHouse.setAmt3(tempAmt3);
                        sale1 = currAmt1 * price1;
                        sale2 = currAmt2 * price2;
                        sale3 = currAmt3 * price3;
                        float finalSale = sale1 + sale2 + sale3;
                        String x = String.format("Price of Order: $%.2f", finalSale);
                        System.out.println(x);
                    }else{

                         if(currAmt1 > amt1){
                            int tempAmt1 = currAmt1 - amt1;
                            currHouse.setAmt1(tempAmt1);
                            sale1 = tempAmt1 * price1;
                         }else if(currAmt1 < amt1){
                            amtNeeded = amt1 - currAmt1;
                            //find the max amt
                            for(i = 0; i < listOfWarehouses.size(); i++){
                                if(listOfWarehouses.get(i).getAm1() > maxAmount){
                                    maxAmount = listOfWarehouses.get(i).getAm1();
                                    maxCity = listOfWarehouses.get(i).getCity();    
                                }
                            }
                            if(maxAmount < amtNeeded){
                                unfilled = true;
                            }else{
                                //get from other warehouse
                                for(i = 0; i < listOfWarehouses.size(); i++){
                                    if(listOfWarehouses.get(i).getCity().equals(maxCity)){
                                        int tempAmt1 = listOfWarehouses.get(i).getAm1() - amtNeeded;
                                        if(tempAmt1 < 0){
                                            unfilled = true;
                                        }else{
                                            listOfWarehouses.get(i).setAmt1(tempAmt1);
                                            System.out.println(amtNeeded + " of item 1 shipped from " + listOfWarehouses.get(i).getCity() + " to " + currCity);
                                            System.out.println(listOfWarehouses.get(i).getCity() + " " + listOfWarehouses.get(i).getAm1() + " " + listOfWarehouses.get(i).getAm2() + " " + listOfWarehouses.get(i).getAm3());
                                        }
                                    }
                                }
                                float y = (float) (price1 * .10);
                                sale1 = currAmt1 * price1 + y;
                            }
                         }

                         if(currAmt2 > amt2){
                            int tempAmt2 = currAmt2 - amt2;
                            currHouse.setAmt2(tempAmt2);
                            sale2 = tempAmt2 * price2;
                         }else if(currAmt2 < amt2){
                            amtNeeded = amt2 - currAmt2;
                            for(i = 0; i < listOfWarehouses.size(); i++){
                                if(listOfWarehouses.get(i).getAm2() > maxAmount){
                                    maxAmount = listOfWarehouses.get(i).getAm2();
                                    maxCity = listOfWarehouses.get(i).getCity();    
                                }
                            }
                            if(maxAmount < amtNeeded){
                                unfilled = true;
                            }else{
                                //get from other warehouse
                                for(i = 0; i < listOfWarehouses.size(); i++){
                                    if(listOfWarehouses.get(i).getCity().equals(maxCity)){
                                        int tempAmt2 = listOfWarehouses.get(i).getAm2() - amtNeeded;
                                        if(tempAmt2 < 0){
                                            unfilled = true;
                                        }else{
                                            listOfWarehouses.get(i).setAmt2(tempAmt2);
                                            System.out.println(amtNeeded + " of item 2 shipped from " + listOfWarehouses.get(i).getCity() + " to " + currCity);
                                            System.out.println(listOfWarehouses.get(i).getCity() + " " + listOfWarehouses.get(i).getAm1() + " " + listOfWarehouses.get(i).getAm2() + " " + listOfWarehouses.get(i).getAm3());
                                        }        
                                    }
                                }
                                float y = (float) (price2 * .10);
                                sale2 = currAmt2 * price2 + y;
                            }

                            if(currAmt3 > amt3){
                                int tempAmt3 = currAmt3 - amt3;
                                currHouse.setAmt3(tempAmt3);
                                sale3 = tempAmt3 * price3;
                            }else if(currAmt3 < amt3){
                                amtNeeded = amt3 - currAmt3;

                                for(i = 0; i < listOfWarehouses.size(); i++){
                                    if(listOfWarehouses.get(i).getAm3() > maxAmount){
                                        maxAmount = listOfWarehouses.get(i).getAm3();
                                        maxCity = listOfWarehouses.get(i).getCity();    
                                    }
                                }

                                if(maxAmount < amtNeeded){
                                    unfilled = true;
                                }else{
                                    //get from other warehouse
                                    for(i = 0; i < listOfWarehouses.size(); i++){
                                        if(listOfWarehouses.get(i).getCity().equals(maxCity)){
                                            int tempAmt3 = listOfWarehouses.get(i).getAm3() - amtNeeded;
                                            if(tempAmt3 < 0){
                                                unfilled = true;
                                            }else{
                                                listOfWarehouses.get(i).setAmt3(tempAmt3);
                                                System.out.println(amtNeeded + " of item 3 shipped from " + listOfWarehouses.get(i).getCity() + " to " + currCity);
                                                System.out.println(listOfWarehouses.get(i).getCity() + " " + listOfWarehouses.get(i).getAm1() + " " + listOfWarehouses.get(i).getAm2() + " " + listOfWarehouses.get(i).getAm3());
                                            }        
                                        }
                                    }
                                    float y = (float) (price3 * .10);
                                    sale3 = currAmt3 * price3 + y;
                                }          
                            }
                         }
                         if(unfilled == true){
                            System.out.println("Order Unfilled");
                         }else{
                            float finalSale = sale1 + sale2 + sale3;
                            String x = String.format("Price of Order: $%.2f", finalSale);
                            System.out.println(x);
                         }
                    }
                  }
                }   
            }
        }   
    }
}
