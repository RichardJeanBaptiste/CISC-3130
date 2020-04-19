/*
  Circular Linked List
*/

class CLinkedList {

    public class Node {

        int data;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
    }

    public Node head = null;
    public Node tail = null;

    public void add(int data){

        Node newNode = new Node(data);

        if(head == null){

            head = newNode;
            tail = newNode;
            newNode.next = head;
        }else{

            tail.next = newNode;
            tail = newNode;
            tail.next = head;

        }
    }

    public void display(){

        Node curr = head;

        if(head == null){
            System.out.println("List is empty");
        }else{
            
            do{
                System.out.println(curr.data);
                curr = curr.next;
            }while(curr != head);
        }
    }

    public void evensOdds(){

        int even = 0;
        int odd = 0;

        Node curr = head;
        
        do{
            if(curr.data % 2 == 0){
                even++;
            }else{
                odd++;
            }
            curr = curr.next;
        }while(curr != head);

        System.out.println(even + " " + odd);
    }

    public static void main(String[] args) {
        
        CLinkedList a = new CLinkedList();

        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);

        a.display();
        a.evensOdds();
    }
}
