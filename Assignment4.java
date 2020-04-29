/*
    Richard Jean-Baptiste
    14051497
    CISC-3130
*/


import java.io.*;
import java.util.*;

class BinaryTree {
    
    Node root;
    int nodeCount;

    public void add(int key){
        Node newNode = new Node(key);

        if(root == null){
            root = newNode;
            nodeCount++;
        } else {
            Node focusNode = root;

            Node parent;

            while(true){
                parent = focusNode;
                if(key < focusNode.key){
                    focusNode = focusNode.left;

                    if(focusNode == null){
                        parent.left = newNode;
                        nodeCount++;
                        return;
                    }
                } else {

                    focusNode = focusNode.right;

                    if(focusNode == null){
                        parent.right = newNode;
                        nodeCount++;
                        return;
                    }
                }
            }
        }
    }

    public void count(){
        System.out.println("This tree has " + nodeCount + " nodes");
    }

    public void childCount(Node focusNode){
        int numChildren;

        if(focusNode != null){
            numChildren = 0;

            if(focusNode.left != null){
                numChildren++;
            }

            if(focusNode.right != null){
                numChildren++;
            }

            System.out.println(focusNode.key + " has " + numChildren + " children");

            childCount(focusNode.left);
            childCount(focusNode.right);
        }
    }

  
    public boolean remove(int key){

        try {
            Node focusNode = root;
            Node parent = root;

            boolean isLeft = true;

            while(focusNode.key != key){

                parent = focusNode;

                if(key < focusNode.key){

                    isLeft = true;
                    focusNode = focusNode.left;
                } else {

                    isLeft = false;
                    focusNode = focusNode.right;
                }

                if(focusNode == null){
                    return false;
                }
            }

            if(focusNode.left == null && focusNode.right == null){

                if(focusNode == root){
                    root = null;
                } else if(isLeft){
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if(focusNode.right == null) {

                if(focusNode == root){
                    root = focusNode.left;
                }else if(isLeft){
                    parent.left = focusNode.left;
                }else {
                    parent.right = focusNode.left;
                }
            } else if(focusNode.left == null) {
                if(focusNode == root){
                    root = focusNode.right;
                }else if(isLeft){
                    parent.left = focusNode.right;
                }else{
                    parent.right = focusNode.left;
                }
            }else {
                
                Node replacement = replaceNode(focusNode);

                if(focusNode == root){
                    root = replacement;
                }else if(isLeft){
                    parent.left = replacement;
                }else {
                    parent.right = replacement;
                }

                replacement.left = focusNode.left;
            }
            nodeCount--;
            
        } catch (NullPointerException e) {
            
        }

        return true;
      
    }

    public Node replaceNode(Node replacedNode){

        Node replacementParent = replacedNode;
        Node replacement = replacedNode;

        Node focusNode = replacedNode.right;

        while(focusNode != null){
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.left;
        }

        if(replacement != replacedNode.right){
            replacementParent.left = replacement.right;
            replacement.right = replacedNode.right;
        }

        return replacement;
    }

    public void inorder(Node focusNode){

        if(focusNode != null){
            inorder(focusNode.left);

            System.out.println(focusNode);

            inorder(focusNode.right);
        }
    }

    public void preorder(Node focusNode){

        if(focusNode != null){

            System.out.println(focusNode);
            preorder(focusNode.left);
            preorder(focusNode.right);
        }
    }

    public void postorder(Node focusNode){

        if(focusNode != null){

            postorder(focusNode.left);
            postorder(focusNode.right);
            System.out.println(focusNode);
        }
    }

    void deleteTree(Node focusNode) { 
        root = null; 
        nodeCount = 0;
    } 
      
    public static void main(String[] args)throws FileNotFoundException{
        
        BinaryTree tree = new BinaryTree();

        File dataFile = new File("Data.txt");
        Scanner myReader = new Scanner(dataFile);

        while (myReader.hasNextLine()) {
            String dataLine = myReader.nextLine();
            String[] data = dataLine.split(" ");

            // Intial Tree
            for (String x : data) {
                if(x.equals("-999")){
                    break;
                }
                //System.out.println(x);
                tree.add(Integer.parseInt(x));    
            }

            //Print tree
            System.out.println(" ");
            System.out.println("Inorder");
            System.out.println("---------");
            tree.inorder(tree.root);
            System.out.println("---------");
            System.out.println("preorder");
            System.out.println("---------");
            tree.preorder(tree.root);
            System.out.println("---------");
            System.out.println("postorder");
            System.out.println("---------");
            tree.postorder(tree.root);

            //count nodes/children
            System.out.println(" ");
            tree.count();
            System.out.println(" ");
            tree.childCount(tree.root);

            //Insert/Delete Nodes
            for(int i = 0; i < data.length; i++){
                if(data[i].equals("Delete")){
                    System.out.println("Delete " + data[i + 1]);
                    tree.remove(Integer.parseInt(data[i + 1]));
                }else if(data[i].equals("Insert")){
                    System.out.println("Insert ---" + data[i + 1]);
                    tree.add(Integer.parseInt(data[i + 1]));
                }
            }

            //Print tree
            System.out.println(" ");
            System.out.println("Inorder");
            System.out.println("---------");
            tree.inorder(tree.root);
            System.out.println("---------");
            System.out.println("preorder");
            System.out.println("---------");
            tree.preorder(tree.root);
            System.out.println("---------");
            System.out.println("postorder");
            System.out.println("---------");
            tree.postorder(tree.root);

            //count nodes/children
            System.out.println(" ");
            tree.count();
            System.out.println(" ");
            tree.childCount(tree.root);

            System.out.println("Freeing Tree");
            tree.deleteTree(tree.root);

        }

        myReader.close();
        
    }
}

class Node {

    int key;
    

    Node left;
    Node right;

    Node(int key){
        this.key = key;
    }

    public String toString(){
        return Integer.toString(key);
    }
}
