package sort;

import java.util.Arrays;

/**
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字
 * 原地置换排序，以及基于原地置换排序的找出重复的数字
 */
public class ReplacementSort {

    void sort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            while (array[i] != i) {
                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }

    }


    int findRepeatNum(int[] array) {

        for (int i = 0; i < array.length; i++) {

            while (array[i] != i) {
                if (array[array[i]] == array[i]) {
                    return array[i];
                }

                int temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }

        return -1;

    }


    public static void main(String[] args) {


        int[] array = new int[]{4, 1, 3, 2, 0};
        ReplacementSort replacementSort = new ReplacementSort();
        replacementSort.sort(array);
        System.out.println(Arrays.toString(array));


        int[] arrayTwo = new int[]{2, 3, 1, 0, 2, 5, 3};
        System.out.println(replacementSort.findRepeatNum(arrayTwo));

    }
}
