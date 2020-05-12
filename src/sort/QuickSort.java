package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {


    public void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = partition(array, start, end);
        quickSort(array, start, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, end);
    }

    /**
     * 分冶思想就是以一个基准点为中心，把其他元素移动到它的左右两边
     * 双边循环法，选定基准元素，并且设置两个指针left以及right，指向数组的最左以及最右的两个元素
     * 小于基准点的放到左边，大于基准点的放右边
     * 当按规则排序好后，基准点的元素和左右指针重合点的元素交换 并返回重合点
     * 例如 321 (4) 5687
     *
     * @param array 待排序数组
     * @param start 数组的最左边的元素
     * @param end   数组的最右边的元素
     * @return 返回一次排序好的基准中心下标
     */
    public int partition(int[] array, int start, int end) {
        int pivot = array[start];
        int left = start;
        int right = end;

        while (left != right) {

            while (left < right && array[right] > pivot) {
                right--;
            }

            while (left < right && array[left] <= pivot) {
                left++;
            }

            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
        //指针重合点和pivot节点交换
        array[start] = array[left];
        array[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1};
        QuickSort sort = new QuickSort();
        System.out.println("排序前");
        System.out.println(Arrays.toString(array));
        System.out.println("排序后");
        sort.quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
