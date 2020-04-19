// Single Linked List
// Function that seperates Odd and Even Integers
// from List


class LinkedList {

    Node head = null;

    public void add(int data){

        Node newNode = new Node(data);

        if(head == null){
            head = newNode;
            return;
        }

        newNode.next = null;

        Node last = head;
        while(last.next != null){
            last = last.next;
        }

        last.next = newNode;
        return;
    }

    public void printAll(Node head){

        Node curr = head;

        while(curr.next != null){
            System.out.println(curr.data);
            curr = curr.next;
        }

        System.out.println(curr.data);
    }

    public void evensOdds(LinkedList a){

        LinkedList even = new LinkedList();
        LinkedList odd = new LinkedList();

        Node curr = a.head;

        while(curr.next != null){

            if(curr.data % 2 == 0){
                even.add(curr.data);
            }else{
                odd.add(curr.data);
            }

            curr = curr.next;
        }

        System.out.println("------even------");
        even.printAll(even.head);
        System.out.println("------odd------");
        odd.printAll(odd.head);

        
    }

    public static void main(String[] args) {
        
        LinkedList a = new LinkedList();
        LinkedList b = new LinkedList();

        a.add(4);
        a.add(12);
        a.add(1);
        a.add(15);
        a.add(2);
        a.add(4);
        a.add(7);
        a.add(9);

        a.evensOdds(a);

    }

}
class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }

}
