package sort;

import java.util.Arrays;

/**
 * 计数排序
 * 计数排序适用于那些在一定范围内的整数排序，例如 4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 0, 10
 * 都是10以内的数字
 */
public class CountSort {


    /**
     * 计数排序的规则其实很简单只是需要几个变量的辅助
     * 首先得知道数组的最大值 max，这样就能确定我们辅助数组的长度，具体看countArray，countArray的长度是max+1，这样才能保证数组最后一个值是最大值
     *
     * @param array 待排序数组
     * @return 排序好的数组
     */
    public int[] countSort(int[] array) {

        int max = array[0];//最大值，方便知道countArray 的长度
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int countLength = max + 1;
        int[] countArray = new int[countLength];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i]]++;//这里可能有点复杂，array[i]的值对应countArray的下标，并将对应的countArray的值+1,就可以知道有几个array[i] ，countArray数组中，index对应的整数为0，则表示array中没有index这个数
        }


        //遍历统计的数组
        int countIndex = 0;
        int[] sortArray = new int[array.length];

        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortArray[countIndex++] = i;
            }
        }

        return sortArray;
    }


    public static void main(String[] args) {
        int[] array = new int[]{4, 4, 6, 5, 3, 2, 8, 1, 7, 5, 6, 10};
        System.out.println("排序前");
        System.out.println(Arrays.toString(array));
        System.out.println("排序后");
        CountSort sort = new CountSort();
        System.out.println(Arrays.toString(sort.countSort(array)));
    }


}
