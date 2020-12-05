package subject;

/**
 * 给定一个 originString 字符串和一个 specialString 字符串，在 originString 字符串中找出 specialString 字符串出现的第一个位置 (从 0 开始)。如果不存在，则返回 -1。
 */
public class FindFirstEqualString {


    private int findFirstAppearAndPosition(String originString, String specialString) {

        if (specialString.isEmpty()) return -1;
        if (specialString.length() > originString.length()) return -1;

        int originSize = originString.length();
        int specialSize = specialString.length();
        int i, j = 0;
        for (i = 0; i < originSize - specialSize + 1; i++) {

            for (j = 0; j < specialSize; j++) {
                //遍历specialString[j]和originString[i+j]作比较,如果不相等则直接返回，因为但凡有不相等，则这个字符串就不是我们要找的
                //注意这里的取值originString[i+j]
                if (!String.valueOf(originString.charAt(i + j)).equals(String.valueOf(specialString.charAt(j)))) {
                    break;
                }
            }
            //当j的值等于我们要找的字符串长度的时候 则找到我们要找的字符串 返回i的位置就是我们要找的
            if (j == specialSize) {
                return i;
            }
        }

        return -1;
    }


    public static void main(String[] args) {


        String originString = "People prepare for the worst but hope for the best";
        String specialString = "op";

        FindFirstEqualString object = new FindFirstEqualString();
        int returnPosition = object.findFirstAppearAndPosition(originString, specialString);
        if (returnPosition == -1) {
            System.out.println("can not find equal string");
        } else {
            System.out.println("find position and position is " + returnPosition);
        }

    }
}
