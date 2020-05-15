package tree.heap;

import java.util.Arrays;

/**
 * 二叉堆的实现
 * 一般用数组实现
 * 父节点和子节点的关系
 * leftChild = parent * 2 + 1
 * rightChild = parent * 2 + 2
 */
public class BinaryHeap {


    /**
     * 最小堆
     * 上浮操作调整二叉堆
     *
     * @param array 待调整的数组
     */
    public void upAndJust(int[] array) {
        int childIndex = array.length - 1;//最后一个叶子节点
        int parentIndex = (childIndex - 1) / 2;//最后一个叶子节点的父节点
        int temp = array[childIndex];
        //注意这里的循环条件，childIndex = 0的话，说明到了堆顶，不需要在调整
        //temp既二叉树最后一个子节点，小于他的父节点就往上浮，知道堆顶
        while (childIndex > 0 && temp < array[parentIndex]) {
            //这里只需要做单次替换就好，思考为什么不需要做真正的替换？
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        //将
        array[childIndex] = temp;
    }


    /**
     * 最小堆
     * 下沉调整二叉堆
     * 注意这个下沉的概念，最后一个叶子节点和自己的子节点比较，比子节点大就下沉
     *
     * @param array       对调整的堆
     * @param parentIndex 最后一个非叶子节点的下表
     * @param length      堆的长度
     */
    public void downAndJust(int[] array, int parentIndex, int length) {
        int temp = array[parentIndex];
        int childIndex = parentIndex * 2 + 1;//左孩子
        while (childIndex < length) {
            if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
                childIndex++;
            }
            //如果父节点小于两个子节点，结束
            if (temp < array[childIndex]) {
                break;
            }
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex;
            childIndex = parentIndex * 2 + 1;
        }
        array[parentIndex] = temp;
    }

    public void buildHeap(int[] array) {
        //注意这里的最后一个非叶子结点的取值，最后一个叶子结点的下标为偶数 是左结点，是奇数 就是右节点
        for (int i = (array.length - 2) / 2; i >= 0; i--) {
            downAndJust(array, i, array.length);
        }
        System.out.println("构建小顶堆之后数组是");
        System.out.println(Arrays.toString(array));
    }


    /**
     * 堆排序
     * 如果要从小到大的排序，则构建大顶堆
     * 如果要从大到小的排序，则构建小顶堆
     * 因为排序的操作是拿最后一个子节点和头结点交换，然后在调整堆
     * 拿小顶堆来说，头元素肯定是最小的，最后一个元素与其交换后，开始调整
     * 这里注意一个点，最后一个元素和头元素交换后，由于小顶堆的特性，子节点一定比父节点大，所以
     */
    public void heapSort(int[] array) {
        //先构建最小堆
        buildHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downAndJust(array, 0, i);
        }
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 2, 6, 5, 7, 8, 9, 10, 0};
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.upAndJust(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{7, 1, 3, 10, 5, 2, 8, 9, 6};
        binaryHeap.buildHeap(array);
        System.out.println(Arrays.toString(array));

        array = new int[]{1, 3, 2, 6, 5, 8, 9, 10, 0};
        System.out.println("堆排序之前的数组");
        System.out.println(Arrays.toString(array));
        binaryHeap.heapSort(array);
        System.out.println("堆排序之后的数组");
        System.out.println(Arrays.toString(array));
    }


}
