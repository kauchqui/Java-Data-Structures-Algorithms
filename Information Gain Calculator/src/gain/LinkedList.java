package gain;

public class LinkedList {
    Node head;
    class Node{
        int data;
        Node next;
        Node(int d){
            data = d;
            next = null;
        }
    }
    public void push(int d){
        Node newNode = new Node(d);
        newNode.next = head;
        head = newNode;
    }
    public void removeNode(int index){
        if (head == null){
            return;
        }
        Node temp = head;
        if(index == 0){
            head = temp.next;
            return;
        }
        for(int i = 0; temp!=null && i < index-1; i++) {
            temp = temp.next;
        }
            Node next = temp.next.next;
            temp.next = next;
            head.next = temp;
    }
}
