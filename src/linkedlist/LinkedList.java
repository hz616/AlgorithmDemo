package linkedlist;

import java.util.Arrays;

public class LinkedList {


    private int size;

    private Node head = null;

    private Node tail = head;


    public LinkedList() {
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
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


    /**
     * 删除指定的元素
     *
     * @param head
     * @param value
     * @return
     */
    public Node removeNode(Node head, int value) {

        if (head == null) return head;
        if (head.data == value) return head.next;
        Node currentNode = head;
        while (currentNode.next != null) {
            if (currentNode.next.data == value) {
                currentNode.next = currentNode.next.next;
                break;
            }
            currentNode = currentNode.next;

        }
        return head;

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


    public int[] reversePrint(Node head) {


        if (head == null) return null;

        int count = 0;//链表的长度

        Node currentNode = head;

        while (currentNode != null) {
            count++;
            currentNode = currentNode.next;
        }

        int[] reverseList = new int[count];


        currentNode = head;

        while (currentNode != null) {
            reverseList[--count] = currentNode.data;
            currentNode = currentNode.next;
        }

        return reverseList;

    }

    /**
     * 链表翻转
     * 步骤如下：
     * 1 定义一个空的节点perNode，让一个节点指向pre，就可以理解为部分翻转了，因为正常情况下是next = current.next 现在需要是pre=current.next
     * 2 确定当前的节点
     * 3 记录下当前节点的下一个节点
     * 4 将当前节点的下一个节点指向前一个节点(部分翻转)
     * 5 pre节点后移，即pre = head
     * 6 head后移 即 head = next
     *
     * @param head
     * @return
     */
    public Node reverseLinkList(Node head) {
        Node preNode = null;
        while (head != null) {
            Node currentNode = head;
            Node nextNode = head.next;
            currentNode.next = preNode;
            preNode = head;
            head = nextNode;
        }
        return preNode;
    }


    /**
     * 输入一个链表，输出该链表中倒数第k个节点。
     * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
     * 例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
     *
     * @param head
     * @param k
     * @return
     */
    public Node getKthFromEnd(Node head, int k) {

        if (head == null || k < 0) return null;
        Node fastNode = head;
        for (int i = 1; i < k; i++) {
            if (fastNode == null) return null;//避免倒数的数大于链表长度
            fastNode = fastNode.next;
        }

        Node slowNode = head;

        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }

        return slowNode;

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

//        System.out.println("==================链表翻转=================");
//        linkedList.head = linkedList.reverseLinkList(linkedList.head);
//        linkedList.outPut();

//        System.out.println("==================链表中删除指定的元素=================");
//        System.out.println("头结点的数据为"+linkedList.removeNode(linkedList.head, 3).data);
//        linkedList.outPut();

        System.out.println("==================查找倒数第几个数的值=================");
        System.out.println("倒数第3个数的值为：" + linkedList.getKthFromEnd(linkedList.head, 3).data);


        System.out.println("==================链表从尾部打印链表=================");
        System.out.println(Arrays.toString(linkedList.reversePrint(linkedList.head)));

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





        System.out.println("==================合并两个排序链表=================");
        LinkedList listOne = new LinkedList();
        listOne.insertTail(1);
        listOne.insertTail(3);
        listOne.insertTail(4);
        System.out.println("==================listOne=================");
        listOne.outPut();

        LinkedList listTwo = new LinkedList();
        listTwo.insertTail(2);
        listTwo.insertTail(3);
        listTwo.insertTail(5);

        System.out.println("==================listTwo=================");
        listTwo.outPut();




        LinkedList mergeList = new LinkedList();
        Node listOneHead = listOne.getHead();
        Node listTwoHead = listTwo.getHead();

        while (listOneHead != null && listTwoHead != null) {
            if (listOneHead.data < listTwoHead.data) {
                mergeList.insertTail(listOneHead.data);
                listOneHead = listOneHead.next;
            } else {
                mergeList.insertTail(listTwoHead.data);
                listTwoHead = listTwoHead.next;
            }
        }

        Node mergeListTail = mergeList.getTail();

        mergeListTail.next = listOneHead == null ? listTwoHead : listOneHead;
        System.out.println("==================合并后的链表=================");
        mergeList.outPut();

    }


}
