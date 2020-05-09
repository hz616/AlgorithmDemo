package tree.heap;


import java.util.Arrays;

/**
 * 优先队列
 * 基于二叉堆的实现
 */
public class PriorityQueue {


    private int[] array;

    /**
     * 已经入队的的元素数量
     */
    private int size;


    public PriorityQueue(int capacity) {
        array = new int[capacity];
    }


    public void enQueue(int value) {
        if (size >= array.length) {
            resize();
        }
        array[size++] = value;
        upAndJust();
    }

    public int deQueue() throws IllegalAccessException {
        if (size <= 0) {
            throw new IllegalAccessException("queue is empty");
        }
        int deQueueValue = array[0];//堆顶元素出队
        array[0] = array[size - 1];//最后一个元素到栈顶
        array[size - 1] = 0;//模拟删除最后一个元素
        size--;
        downAndJust();//下沉调整
        return deQueueValue;
    }

    private void downAndJust() {
        int parentIndex = 0;
        int temp = array[parentIndex];
        int childIndex = 1;//先取左孩子
        while (childIndex < size) {
            if (childIndex + 1 < size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;//父节点需要和右孩子交换
            }
            if (temp >= array[childIndex]) {
                //如果大于任意一个孩子，则不需要交换了，直接返回
                break;
            }
            array[parentIndex] = array[childIndex];
            //注意这里的赋值，为什么直接将父节点的下标 = 子孩子的下标 而不是 parentIndex + 1  ？
            //因为构建好的优先队列，已经是满足二叉堆的,只是将最后一个叶子节点放到堆顶的时候破坏了这种平衡
            //所以只要找到大于父节点的那个子节点，则这个子节点的兄弟节点以及这个兄弟节点的所有子节点都会小于父节点，所以就不需要在左右子树去遍历查找了，只需要找到那个大于父节点的子节点下面的子树中去遍历比较即可，有点绕。。。
            parentIndex = childIndex;
            childIndex = 2 * parentIndex + 1;
        }
        array[parentIndex] = temp;
    }

    private void upAndJust() {
        //最后一个入队的子节点开始上浮
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;//最后一个叶子节点的父节点
        int temp = array[childIndex];
        while (childIndex > 0 && temp > array[parentIndex]) {
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
        array[childIndex] = temp;
    }


    private void resize() {
        int newSize = size * 2;
        this.array = Arrays.copyOf(array, newSize);
    }

    public void outPut() {
        System.out.print(Arrays.toString(array));
    }


    public static void main(String[] args) {
        PriorityQueue priorityQueue = new PriorityQueue(32);
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("入队后的优先队列");
        priorityQueue.outPut();
        System.out.println("出队操作");
        try {
            System.out.println("出队的元素" + priorityQueue.deQueue());
            System.out.println("出队后的队列");
            priorityQueue.outPut();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
