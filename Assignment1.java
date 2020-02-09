
/*
    Richard Jean-Baptiste
    14051497
    CISC-3130
*/

import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {

        File masterFile = new File("masterfile.txt");
        String name,custNo,prevBalance;
       

        try{
            Scanner masterReader = new Scanner(masterFile);
            
            while(masterReader.hasNextLine()){
                
                String masterData = masterReader.nextLine();
                String[] masterDataSplit = masterData.split(" ", 0);
                name = masterDataSplit[1];
                custNo = masterDataSplit[0];
                prevBalance = masterDataSplit[2];

                System.out.println("Customer Name - " + name + " " + "Customer Number - " + custNo + " Previous Balance - " + prevBalance);
                Transactions(custNo, masterData);
                BalanceDue(custNo);
            
            }
            masterReader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
 
    }
    static void Transactions(String custNo, String masterData)
    {
       try {
            File transFile = new File("transactionfile.txt");
            Scanner transReader = new Scanner(transFile);

            int purchase,payment,previousAmount,finalAmount;
            String newString,oldString;

            String masterDataSplit[] = masterData.split(" ", 0);
            previousAmount = Integer.parseInt(masterDataSplit[2]);

            oldString = masterData;
            finalAmount = previousAmount;

            while(transReader.hasNextLine()){
                String transaction = transReader.nextLine();
                String[] transactionsSplit = transaction.split(" ", 0);
                if(transactionsSplit[1].equals(custNo) && transactionsSplit[0].equals("O")){
                    purchase = Integer.parseInt(transactionsSplit[4]) * Integer.parseInt(transactionsSplit[5]);
                    finalAmount += purchase;
                    newString = masterDataSplit[0] + " " + masterDataSplit[1] + " " + finalAmount;
                    updateFile("masterfile.txt", oldString, newString);
                    oldString = newString;
                    System.out.println("Transaction # - " + transactionsSplit[2] + " " + transactionsSplit[3] + " $" + purchase);
                }else if(transactionsSplit[1].equals(custNo) && transactionsSplit[0].equals("P")){
                    payment = Integer.parseInt(transactionsSplit[3]);
                    finalAmount -= payment;
                    newString = masterDataSplit[0] + " " + masterDataSplit[1] + " " + finalAmount;
                    updateFile("masterfile.txt", oldString, newString);
                    oldString = newString;
                    System.out.println("Transaction # - " + transactionsSplit[2] + " Payment - $" + payment);                 
                }
                   
            }

            transReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("File not found");
       }
    }
    static void BalanceDue(String custNo)
    {
        File masterFile = new File("masterfile.txt");
        try {
            Scanner masterReader = new Scanner(masterFile);

            while(masterReader.hasNextLine()){
                String masterData = masterReader.nextLine();
                String[] masterDataSplit = masterData.split(" ", 0);

                if(masterDataSplit[0].equals(custNo)){
                    System.out.println("Balance Due - $" + masterDataSplit[2]);
                }
                               
            }
            masterReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void updateFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
         
        String oldContent = "";
         
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
              
            String line = reader.readLine();
             
            while (line != null) 
            {
                oldContent = oldContent + line + System.lineSeparator();
                 
                line = reader.readLine();
            }
             
             
            String newContent = oldContent.replace(oldString, newString);
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);

           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
