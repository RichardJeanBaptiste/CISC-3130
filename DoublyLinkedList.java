/*
    Doubly Linked List
    with forward and backword traversal
*/
class DLinkedList {

    class Node {

        int data;
        Node prev;
        Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head;

    public void add(int data){

        Node newNode = new Node(data);

        newNode.next = head;
        newNode.prev = null;

        if(head != null){
            head.prev = newNode;
        }

        head = newNode;
    }

    public void display(Node head){

        Node curr = head;

        while(curr.next != null){
            System.out.println(curr.data);
            curr = curr.next;
        }

        System.out.println(curr.data);

    }

    public void change(Node head){

        Node curr = head;

        while(curr.next != null){
            if(curr.data % 2 == 0){
                curr.data += 10;
            }
            curr = curr.next;
        }

        if(curr.data % 2 == 0){
            curr.data += 10;
        }

        // Reverse
        while(curr.prev != null){
            if(curr.data % 2 != 0){
                curr.data -= 4;
            }
            curr = curr.prev;
        }

        if(curr.data % 2 != 0){
            curr.data -= 4;
        }
    }

    public static void main(String[] args) {
        
        DLinkedList a = new DLinkedList();

        a.add(4);
        a.add(3);
        a.add(10);
        a.add(1);
        a.add(7);
        a.add(11);
        a.add(2);
        a.add(12);

        a.change(a.head);
        a.display(a.head);
    }
}
