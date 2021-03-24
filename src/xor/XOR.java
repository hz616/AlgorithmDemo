package xor;

/**
 * 异或运算 特性
 * 异或是一种基于二进制的位运算，用符号XOR或者 ^ 表示，其运算法则是对运算符两侧数的每一个二进制位，同值取0，异值取1
 * <p>
 * 性质
 * 　　　　1、交换律
 * 　　　　2、结合律（即(a^b)^c == a^(b^c)）
 * 　　　　3、对于任何数x，都有x^x=0，x^0=x
 * 　　　　4、自反性 A XOR B XOR B = A XOR 0 = A
 */
public class XOR {


    /**
     * 异或运算可以交换两个值(自反性)
     * A  ^ B  ^ B = A
     * 即对给定的数A，用同样的运算因子（B）作两次异或运算后仍得到A本身
     */
    public void changeNumber() {

        int a = 10, b = 5;
        System.out.println("before change a is " + a + " and b is " + b);
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("after change a is " + a + " and b is " + b);

    }


    /**
     * 1-1000放在含有1001个元素的数组中，只有唯一的一个元素值重复，其它均只出现一次。每个数组元素只能访问一次，设计一个算法，将它找出来；不用辅助存储空间，能否设计一个算法实现？
     * 解法一:
     * 将所有的数加起来，减去1+2+3+...+1000 则可以得到这个重复的数
     * <p>
     * 解法二：
     * 可以通过异或运算
     * 将所有的数全部异或，得到的结果与1^2^3^...^1000的结果进行异或，得到的结果就是重复数
     * 例如
     * 1^2^3^...^n^...^n^...^1000 这个n无论出现在哪里，都可以转换为 1^2^3^...^1000^（n^n）
     * 其次，对于任何数X，都有都有x^x=0，x^0=x
     * 所以1^2^...^n^...^n^...^1000 = 1^2^...^1000^(n^n)= 1^2^...^1000^0 = 1^2^...^1000（即序列中除了n的所有数的异或）
     * 令，1^2^...^1000（序列中不包含n）的结果为T
     * 则1^2^...^1000（序列中包含n）的结果就是T^n
     * T^(T^n)=n
     *
     * @param number
     */
    public void findRepeatNumber(int[] number) {


    }

    /**
     * 一个数组存放若干整数，一个数出现奇数次，其余数均出现偶数次，找出这个出现奇数次的数
     */
    public void findNumber() {

        int[] array = new int[]{22, 38, 38, 22, 22, 4, 4, 11, 11};

        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp = temp ^ array[i];
        }

        System.out.println("find number and number is " + temp);

    }


    public static void main(String[] args) {
        XOR xor = new XOR();
        xor.changeNumber();
        xor.findNumber();
    }

}
