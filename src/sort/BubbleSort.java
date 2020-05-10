package sort;

/**
 * 冒泡排序
 */
public class BubbleSort {


    /**
     * 原始的冒泡排序
     * 从第一个元素开始，依次和后面的元素相比较，如果大于后面的元素则交换，最大的元素排在最后面
     * 时间复杂度o(n^2)
     *
     * @param array 待排序的数组
     */
    public void bubbleSort(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp = array[j];
                if (array[j] > array[j + 1]) {
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }

    }

    /**
     * 第一轮优化后的冒泡排序
     * 这里的优化点是加一个是否已经有序的flag
     * 因为如果没有发生交换的话，则证明，此时的数组已经是有序的状态了，没有必要再进行一轮遍历了
     */
    public void bubbleSortOptimizeFirst(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                int temp = array[j];
                if (array[j] > array[j + 1]) {
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }


    /**
     * 第二轮优化过的冒泡排序
     * 在第一轮优化的基础上，新增了一个最后交换位置的下标，为什么要增加这个下标呢？
     * 3,4,2,1,5,6,7,8 这个数组可以看到 后面的5 6 7 8都是有序的，可以说后面的几个是不需要排序的，是优化点
     * 怎么找到这个有序点的开始下标呢，最后一次交换过的下标，就是这个border
     * 例如 进行到2 和 1 比较的时候，2 比 1大，所以他们两个交换，交换后的数组是3,4,1,2,5,6,7,8 这个border的下表是现在1对应的下标，后面的没有发生交换就都是有序的
     * 只需要对0 到 border 的这一段排序即可
     *
     * @param array 待排序数组
     */
    public void bubbleSortOptimizeTwo(int[] array) {
        int sortBorder = array.length - 1;
        int lastChangeIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                int temp = array[j];
                if (array[j] > array[j + 1]) {
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                    lastChangeIndex = j;
                }
            }
            sortBorder = lastChangeIndex;
            if (isSorted) {
                break;
            }
        }
    }


    /**
     * 冒泡排序的第三轮优化
     * 这个优化俗称鸡尾酒优化
     * 特点是先从头到尾的排序交换，然后在从尾部到头排序交换，这个种适合特点条件下的排序，减少循环遍历次数
     * 例如2, 3, 4, 5, 6, 7, 8, 1 前面的2345678都是有序的，只有1需要排序
     * 先进行第一轮从头到尾 最后8和1交换 数组变为2,3,4,5,6,7,1,8
     * 进行第二轮从尾部到头 因为8已经是最到的在最后面了，所以从1开始，排序后变为1,2,3,4,5,6,7,8 变为有序了
     * 然后进行第三轮后发现没有发生交换，数组有序，直接跳出循环
     * 极大的减少了循环的次数
     *
     * @param array 待排序数组
     */
    public void bubbleSortOptimizeThree(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {

            boolean isSorted = true;

            for (int j = i; j < array.length - 1 - i; j++) {
                int temp = array[j];
                if (array[j] > array[j + 1]) {
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }

            isSorted = false;

            for (int k = array.length - 1 - i; k > i; k--) {
                int temp = array[k];
                if (array[k] < array[k - 1]) {
                    array[k] = array[k - 1];
                    array[k - 1] = temp;
                    isSorted = false;
                }
            }

            if (isSorted) {
                break;
            }
        }
    }

    public void output(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] array = new int[]{8, 5, 6, 3, 9, 2, 1, 7};
        BubbleSort bubbleSort = new BubbleSort();
        System.out.println("排序前");
        bubbleSort.output(array);
        System.out.println("排序后");
        bubbleSort.bubbleSort(array);
        bubbleSort.output(array);

        System.out.println("=============第一轮优化===============");

        int[] arrayTwo = new int[]{8, 5, 6, 3, 9, 2, 1, 7};
        System.out.println("排序前");
        bubbleSort.output(arrayTwo);
        System.out.println("排序后");
        bubbleSort.bubbleSortOptimizeFirst(arrayTwo);
        bubbleSort.output(arrayTwo);


        System.out.println("=============第二轮优化===============");

        int[] arrayThree = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        System.out.println("排序前");
        bubbleSort.output(arrayThree);
        System.out.println("排序后");
        bubbleSort.bubbleSortOptimizeTwo(arrayThree);
        bubbleSort.output(arrayTwo);


        System.out.println("=============第三轮优化===============");

        int[] arrayFour = new int[]{2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println("排序前");
        bubbleSort.output(arrayFour);
        System.out.println("排序后");
        bubbleSort.bubbleSortOptimizeThree(arrayFour);
        bubbleSort.output(arrayFour);

    }


}
