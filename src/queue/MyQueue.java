package queue;

/**
 * 循环队列的实现
 * 这里注意队列和栈不同
 * 队列能入队的数量 = 数组的长度-1
 * 需空出一个位置来
 * 思考 为什么需要空出一个位置来？
 * 因为如果不空出一个位置的话
 * 队列空的情况下是front=tail
 * 队列满的情况下也是front=tail
 * 这样就没法区分
 * 所以空出一个位置后
 * 队列空的条件是front == tail
 * 队列满的条件是 (tail+1)%array.length == front
 */
public class MyQueue {


    int[] array;

    int front;
    int tail;


    public MyQueue(int capacity) {
        array = new int[capacity];
    }


    /**
     * 每次插入一个，tail向后移动一位，指向下一个空位
     *
     * @param value 入队的值
     */
    public void enqueue(int value) {

        if ((tail + 1) % array.length == front) {
            throw new IllegalStateException("queue have been full");
        }
        array[tail] = value;
        tail = (tail + 1) % array.length;
    }

    /**
     * 每出队一个元素，front移动指向下一个元素
     *
     * @return 出队的元素
     */
    public int dequeue() {
        if (front == tail) {
            throw new IllegalStateException("queue is empty");
        }
        int dequeueValue = array[front % array.length];
        front = (front + 1) % array.length;
        return dequeueValue;
    }


    /**
     * 队列输出的时候
     * 从队列头部，循环到队列尾部结束
     * 这里注意i的取值
     */
    public void output() {
        for (int i = front; i != tail; i = ((i + 1) % array.length)) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("===============入队操作===========");
        MyQueue queue = new MyQueue(5);
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
//        queue.enqueue(4);//因为队列的长度为5，所以这里在入队的话，就会抛出满队的异常

        queue.output();
        System.out.println("===============出队操作===========");

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.output();

        System.out.println("===============再次入队操作===========");
        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.output();

    }

}
