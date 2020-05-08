package linkedlist;

public class LinkedList {


    private int size;

    private Node head = null;

    private Node tail = head;


    public LinkedList() {
    }

    /**
     * 头插法
     *
     * @param data
     */
    public void insertHead(int data) {
        Node insertNode = new Node(data);
        if (head == null) {
            head = insertNode;
        } else {
            insertNode.next = head;
            head = insertNode;
        }
        size++;
    }

    /**
     * 尾插法
     *
     * @param data
     */
    public void insertTail(int data) {
        Node insertNode = new Node(data);
        if (tail == null) {
            head = tail = insertNode;
        } else {
            tail.next = insertNode;
            tail = insertNode;
        }
        size++;
    }

    public void insert(int index, int value) {
        Node insertNode = new Node(value);
        Node preNode = get(index - 1);
        //先找到要插入节点的前面一个节点，插入节点的的下一个节点，指向pre的next
        //这里思考为什么不先pre.next = insert ? 因为如果pre.next = insert后，那么pre的之前的next节点不知道是谁了，这样就没法插入了
        insertNode.next = preNode.next;
        preNode.next = insertNode;
        size++;
    }

    public int removeHeader() {
        if (head == null) return -1;
        int value = head.data;
        head = head.next;
        size--;
        return value;
    }

    public int removeTail() {
        if (tail == null) return -1;
        int value = tail.data;
        //注意这里是get的index是size-2 因为坐标是从0开始的，最后一个节点应该是size-1 所以前一个节点应该是size-2
        Node preNode = get(size - 2);
        preNode.next = null;
        tail = preNode;
        size--;
        return value;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("size is " + size + " and index is " + index);
        }
        Node preNode = get(index - 1);
        Node removeNode = preNode.next;
        preNode.next = removeNode.next;
        size--;
        return removeNode.data;
    }

    public Node get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("size is " + size + " and index is " + index);
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }


    public void outPut() {
        if (head != null) {
            Node temp = head;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("==================头插入=================");
        LinkedList linkedList = new LinkedList();
        linkedList.insertHead(0);
        linkedList.insertHead(1);
        linkedList.insertHead(2);
        linkedList.insertHead(3);
        linkedList.insertHead(4);
        linkedList.insertHead(5);

        linkedList.outPut();

        System.out.println("get node index and index is 3 value is " + linkedList.get(3).data);

        System.out.println("==================中间头插入20=================");
        linkedList.insert(3, 20);
        linkedList.outPut();
        System.out.println("==================删除头部=================");
        System.out.println("remove value is " + linkedList.removeHeader());
        System.out.println("==================删除头部后的链表=================");
        linkedList.outPut();
        System.out.println("==================删除中间任意节点2=================");
        linkedList.remove(2);
        linkedList.outPut();

        System.out.println("==================尾插入=================");
        LinkedList tailInsertList = new LinkedList();
        tailInsertList.insertTail(0);
        tailInsertList.insertTail(1);
        tailInsertList.insertTail(2);
        tailInsertList.insertTail(3);
        tailInsertList.insertTail(4);
        tailInsertList.insertTail(5);
        tailInsertList.outPut();
        System.out.println("get node index and index is 3 value is " + tailInsertList.get(3).data);
        System.out.println("==================中间尾插入20=================");
        tailInsertList.insert(3, 20);
        tailInsertList.outPut();

        System.out.println("==================删除尾部=================");
        System.out.println("remove value is " + tailInsertList.removeTail());
        System.out.println("==================删除尾部后的链表=================");
        tailInsertList.outPut();

        System.out.println("==================删除中间任意节点2=================");
        tailInsertList.remove(2);
        tailInsertList.outPut();


    }


}
