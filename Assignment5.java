import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

class BinaryTree  {

    class Node {

        String name;
        Node leftChild,rightChild;

        Node(String name){
            this.name = name;
            leftChild = null;
            rightChild = null;
        }
    }

    Node root;
    Node parent;
    Node focusNode;
    String father;

    public void add(Node node ,String name){

        Node currNode = new Node(name);

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
    
    public void getSons(String name){

       Node p = getNode(root, name);
       Node focus;

       if(p.leftChild == null){
        System.out.println("no children");
        return;
        }


       System.out.print("The sons of " + name);
       System.out.println(" ");

       System.out.print(p.leftChild.name);
       focus = p.leftChild;

       while(focus.rightChild != null){
            System.out.print(" " + focus.rightChild.name);
            focus = focus.rightChild;
       }
 
    }

    public void getOldestSons(String name){

        Node p = getNode(root, name);

        if(p.leftChild == null){
            System.out.println("no children");
            return;
        }

        System.out.println("The oldest son of " + name + " is " + p.leftChild.name);
    }

    public void getYoungestSon(String name){

        Node p = getNode(root, name);
        Node focus;

        if(p.leftChild == null){
            System.out.println("no children");
            return;
        }

        focus = p.leftChild;

        while(focus.rightChild != null){
            focus = focus.rightChild;
        }

        System.out.println("The youngest son of " + name + " is " + focus.name);

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
    
    public void preorder(Node focusNode){

        if(focusNode != null){

            System.out.println(focusNode.name);
            preorder(focusNode.leftChild);
            preorder(focusNode.rightChild);
        }
    }


    public static void main(String[] args)throws FileNotFoundException, IOException {
        
        File data = new File("Data.txt");
        Scanner dataReader = new Scanner(new FileReader(data));

        BinaryTree familyTree = new BinaryTree();
    
        String line = "";

        int count = 0;
        int lineNum = 1;

        while(dataReader.hasNextLine()){
            String fullData = dataReader.nextLine();
            String[] dataLine = fullData.split(" ");
            String name = dataLine[0];
            count = Integer.parseInt(dataLine[1]);

            familyTree.setParent(familyTree.root,name);

            for(int i = 0; i < count; i++){
                line = Files.readAllLines(Paths.get("Data.txt")).get(lineNum + i);
                String newLine[] = line.split(" ");

                familyTree.add(familyTree.root,newLine[0]);
                
            }

            lineNum += count;

        }

        
        //Node x = familyTree.getNode(familyTree.root, "Bob");
        //System.out.println(x.leftChild.name);
        
        //System.out.println(familyTree.root.leftChild.leftChild.name);
        //familyTree.preorder(familyTree.root);

        dataReader.close();
        
    }
}
