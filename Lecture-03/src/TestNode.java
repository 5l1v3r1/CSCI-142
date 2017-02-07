/**
 * Created by ptnega on 06/02/2017.
 */
public class TestNode {

    public static void main(String[] args) {
        // n1 = Node(10, None)
        Node <Integer> n1 = new Node("lol", null);

        // n2 = Node(20, n1)
        Node n2 = new Node(20, n1);

        // n3 = Node(30, None)
        // n3.next = n2

        Node n3 = new Node(30, null);
        n3.setNext(n2);

        Node tmp = n3;
        while (tmp != null) {
            System.out.println(tmp.getData());
            tmp = tmp.getNext();
        }
    }
}
