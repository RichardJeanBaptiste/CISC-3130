import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class BinaryTree  {

    class Node {

        String name;
        String father;
        Node leftChild,rightChild;

        Node(String name){
            this.name = name;
            father = "Not available";
            leftChild = null;
            rightChild = null;
        }

        Node(String name, String father){
            this.name = name;
            this.father = father;
            leftChild = null;
            rightChild = null;
        }

        public String getFather(){
            return father;
        }
    }

    Node root;
    Node parent;
    Node focusNode;

    public void add(Node node ,String name,String parentName){

        try {

            Node currNode = new Node(name,parentName);

            if(root == null){
                root = currNode;
                parent = root;
                focusNode = root;
                return;
            }
    
            if(parent.leftChild == null){
                parent.leftChild = currNode;
                focusNode = parent.leftChild;
            }else{
                focusNode.rightChild = currNode;
                focusNode = focusNode.rightChild;
                return;
            }
            
        } catch (NullPointerException e) {
           
        }

    }

    public void setParent(Node node,String name){

        Node childNode = new Node(name);

        if(root == null){
            root = childNode;
            parent = childNode;
        }else{
            node = getNode(root, name);
            parent = node;
            focusNode = parent;
        }

    }

    public void getfather(String name){
        try {
            Node p = getNode(root, name);
            System.out.println("The father of " + name + " is " + p.getFather());
        } catch (Exception e) {
            System.out.println(name + " couldn't get father");
        }
        
    }
 
    public void getSons(String name){

        try {

            Node p = getNode(root, name);
            Node focus;

            if(p.leftChild == null){
                System.out.println(name + " has no sons");
                return;
            }

            System.out.print("The sons of " + name + " - ");

            System.out.print(p.leftChild.name);
            focus = p.leftChild;

            while(focus.rightChild != null){
                System.out.print(" " + focus.rightChild.name);
                focus = focus.rightChild;
            }

            System.out.println(" ");
            
        } catch (NullPointerException e) {
            System.out.println(name + " has no sons");
        }
 
    }

    public void getBrothers(String name){

        try {

            String first = name;

            Node p = getNode(root, name);

            String x = p.getFather();

            Node currFather = getNode(root, x);

            Node focus;

            System.out.print("The brothers of " + first + " - ");

            System.out.print(currFather.leftChild.name);

            focus = currFather.leftChild;

            while(focus.rightChild != null){
                System.out.print("  " + focus.rightChild.name);
                focus = focus.rightChild;
            }
            
        } catch (NullPointerException e) {
            System.out.println(name + " has no brothers");
        }

    }

    public void getOldestSon(String name){

        try {
            Node p = getNode(root, name);

            if(p.leftChild == null){
                System.out.println(" ");
                System.out.println(name + " has no children");
                return;
            }

            System.out.println(" ");
            System.out.println("The oldest son of " + name + " is " + p.leftChild.name);
        } catch (NullPointerException e) {
            System.out.println(name + " has no sons");
        }

        
    }

    public void getYoungestSon(String name){

        try {

            Node p = getNode(root, name);
            Node focus;

            if(p.leftChild == null){
                System.out.println(name + " has no children");
                return;
            }

            focus = p.leftChild;

            while(focus.rightChild != null){
                focus = focus.rightChild;
            }

            System.out.println("The youngest son of " + name + " is " + focus.name);
            
        } catch (NullPointerException e) {
            System.out.println(name + " has no sons");
        }

    }
    

    public Node getNode(Node node, String name){

        if(node != null){
            if(node.name.equals(name)){
               return node;
            } else {
                Node foundNode = getNode(node.leftChild, name);
                if(foundNode == null) {
                    foundNode = getNode(node.rightChild, name);
                }
                return foundNode;
             }
        } else {
            return null;
        }
    }

    public void deleteTree() { 
        root = null;
    } 

    static String randNames(ArrayList<String> x){

        Random rand = new Random(); 
        return x.get(rand.nextInt(x.size()));
    }

    public static void main(String[] args)throws FileNotFoundException, IOException {
        
        File data = new File("Data.txt");
        Scanner dataReader = new Scanner(new FileReader(data));
        ArrayList<String> ranNames = new ArrayList<String>();

        BinaryTree familyTree = new BinaryTree();
    
        String line = "";

        int count = 0;
        int lineNum = 1;

        while(dataReader.hasNextLine()){
            String fullData = dataReader.nextLine();
            String[] dataLine = fullData.split(" ");

            if(dataLine[0].equals("-999")){

                               
                familyTree.getfather(randNames(ranNames));
                System.out.println(" ");
                familyTree.getSons(randNames(ranNames));
                System.out.println(" ");
                familyTree.getBrothers(randNames(ranNames));
                System.out.println(" ");
                familyTree.getOldestSon(randNames(ranNames));
                System.out.println(" ");
                familyTree.getYoungestSon(randNames(ranNames));
                System.out.println("----------------------");
                
                lineNum += 2;
                familyTree.deleteTree();
                ranNames.clear();
                
            }else{

                
                String name = dataLine[0];
                count = Integer.parseInt(dataLine[1]);
                ranNames.add(name);

                familyTree.setParent(familyTree.root,dataLine[0]);

                for(int i = 0; i < count; i++){
                    line = Files.readAllLines(Paths.get("Data.txt")).get(lineNum + i);
                        String newLine[] = line.split(" ");
                        familyTree.add(familyTree.root,newLine[0],name);  
      
                }
           
                lineNum += count;

            }
                
        }
    
        dataReader.close();
        
    }
}
