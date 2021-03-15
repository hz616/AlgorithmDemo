package offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 声明一个 StringBuffer，遍历字符串，如果遇见空格，添加 %20 到 buffer 中, 否则添加当前字符
 * 时间复杂度：O(n)，当一个字符串的长度为 n 时，遍历字符串一遍，时间复杂度为 O(n)
 * 空间复杂度：0(n)，需要创建 StringBuffer 有额外的开销，每次遇见空格，就替换为 %20，最坏的情况下，长度是原来的 3 倍
 */
public class ReplaceStringBlank {


    String replaceSpace(String value) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) == ' ') {
                builder.append("%20");
            } else {
                builder.append(value.charAt(i));
            }
        }
        return builder.toString();

    }


    public static void main(String[] args) {
        ReplaceStringBlank replaceStringBlank = new ReplaceStringBlank();
        String value = "We are happy.";
        System.out.println(replaceStringBlank.replaceSpace(value));
    }


}
