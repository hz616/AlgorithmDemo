package offer;

import java.util.Arrays;

/**
 * 快慢指针思想
 */
public class FastAndSlowPointer {


    /**
     * 调整数组顺序试奇数位于偶数前面
     * a & 1 == 1 // 奇数
     * a & 1 ！= 1 // 偶数
     * <p>
     * 初始化快慢指针，指向数组的开始位置
     * 遍历数组，当fast>size时推出循环
     * 快指针fast从左边开始，遇到偶数了则 fast++
     * 遇到奇数了则交换快慢指针所指向的数字，并且slow++
     * <p>
     * 复杂度分析：
     * <p>
     * 时间复杂度：O(N) ，为数组 nums 长度
     * 空间复杂度：O(1)，使用常数大小的空间
     */
    public void exchangeOne(int[] nums) {
        int fast = 0;
        int slow = 0;
        int size = nums.length;

        while (fast < size) {
            if ((nums[fast] & 1) == 1) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }

    }


    /**
     * 双指针
     * 初始化左右指针分别指向数组头尾
     * 遍历数组，直到left>=right则推出
     * 指针left从左边开始，遇到奇数则left++，遇到偶数则停止
     * 指针right从右边开始，遇到偶数则right--，遇到奇数则停止
     * 交换位置
     *
     * @param nums
     */
    public void exchangeTwo(int[] nums) {


        int left = 0;
        int right = nums.length - 1;


        while (left < right) {

            while (left < right && (nums[left] & 1) == 1) left++;
            while (left < right && (nums[right] & 1) != 1) right--;

            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }


        }

    }


    public static void main(String[] args) {

        FastAndSlowPointer exchangeOne = new FastAndSlowPointer();
        int[] numsOne = new int[]{4, 1, 8, 9, 4, 6, 8, 1, 5, 7, 3};
        exchangeOne.exchangeOne(numsOne);
        System.out.println(Arrays.toString(numsOne));

        int[] numsTwo = new int[]{4, 1, 8, 9, 4, 6, 8, 1, 5, 7, 3};
        exchangeOne.exchangeTwo(numsTwo);
        System.out.println(Arrays.toString(numsTwo));
    }

}
