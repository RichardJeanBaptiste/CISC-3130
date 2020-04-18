// Binary Tree Stack Traversal

import java.util.Stack;

class BinaryTree {

    Node root;

    public void add(int key){

        Node newNode = new Node(key);

        if(root == null){
            root = newNode;
        }else{
            Node focusNode = root;
            Node parent;

            while(true){
                parent = focusNode;
                if(key < focusNode.key){
                    focusNode = focusNode.leftChild;

                    if(focusNode == null){
                        parent.leftChild = newNode;
                        return;
                    }
                }else{
                    focusNode = focusNode.rightChild;

                    if(focusNode == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }

    }

    
    public void Sample6(BinaryTree Tree){

        int evenCount = 0;
        Node smallestNode;

        if(root == null){
            return;
        }

        Stack<Node> s = new Stack<>();
        Node curr = root;
        // check even integers in tree
        while(curr != null || s.size() > 0){

            while(curr != null){
                s.push(curr);
                curr = curr.leftChild;
            }

            curr = s.pop();
            if(curr.key % 2 == 0){
                evenCount++;
            }
            curr = curr.rightChild;
        }

        curr = root;

        // get the smallest element
        while(curr.leftChild != null){
            curr = curr.leftChild;
        }

        System.out.println(evenCount);
        System.out.println(curr.key);
        
    } 

    public void Sample1(BinaryTree Tree){

        int bothChildren = 0;
        Node smallestNode;

        if(root == null){
            return;
        }

        Stack<Node> s = new Stack<>();
        Node curr = root;
        // check even integers in tree
        while(curr != null || s.size() > 0){

            while(curr != null){
                s.push(curr);
                curr = curr.leftChild;
            }

            curr = s.pop();
            if(curr.leftChild != null && curr.rightChild != null){
                bothChildren++;
                System.out.println(curr.key + " has two children");
            }
            curr = curr.rightChild;
        }

        curr = root;

        // get the smallest element
        while(curr.leftChild != null){
            curr = curr.leftChild;
        }

        System.out.println(bothChildren);
        System.out.println(curr.key);
        
    } 

    

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.add(4);
        tree.add(12);
        tree.add(3);
        tree.add(36);
        tree.add(2);
        tree.add(10);
        tree.add(5);
        tree.add(11);

        tree.Sample1(tree);
    }

}
class Node {

    int key;
    Node leftChild,rightChild;

    Node(int key){
        this.key = key;
        leftChild = null;
        rightChild = null;
    }
}
