package offer;

/**
 * 旋转数组找最小值
 * 何为旋转数组
 * 如：原数组0 1 2 4 5 6 7旋转后得到4 5 6 7 0 1 2   (排序好的数组是前提)
 * 可用二分查找法
 * left middle right
 * 如果mid > right，则mid左边是个正常的升序数组，mid右边是个规模小一半的旋转数组，即最小值在mid和right之间
 * 如果mid < right，则mid右边是个正常的升序数组，mid左边是个规模小一半的旋转数组，即最小值在mid和left之间
 */
public class FinMinNumInRotateArray {


    int minArray(int[] array) {


        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int middle = (left + right) / 2;
            if (array[middle] > array[right]) {
                left = middle + 1;
            } else if (array[middle] < array[right]) {
                right = middle;
            } else {
                right = right - 1;
            }
        }

        return array[left];

    }


    public static void main(String[] args) {
        int[] array = new int[]{5, 1, 2, 3, 4};
        FinMinNumInRotateArray finMinNumInRotateArray = new FinMinNumInRotateArray();
        System.out.println(finMinNumInRotateArray.minArray(array));
    }


}
