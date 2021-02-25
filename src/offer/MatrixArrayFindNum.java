package offer;

/**
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * <p>
 * <p>
 * 由题意得知，每个二维数组都有以下特点：
 * <p>
 * 每一行都按照从左到右递增的顺序排序
 * 每一列都按照从上到下递增的顺序排序
 * 因为都是有序的，所以可以选择将左上角 或者 右上角作为起点，寻找一个和目标数字相等的数，在这里我选择右上角作为起点即 matrix[0][maxColumn] 其中 maxColumn = matrix[0].size - 1 即每一行的最大列数，算法步骤如下
 * <p>
 * 初始化 raw = 0，column = maxColumn
 * 如果 matrix[raw][column] == target，说明存在和目标数字相等的数返回 true
 * 如果 matrix[raw][column] > target, 说明当前元素大于目标值，所以当前元素所在的下面所有元素都大于目标值，因此 column - 1，往左边寻找
 * 如果 matrix[raw][column] < target，说明当前元素小于目标值，所以当前元素所在的左边所有元素都小于目标值，因此 raw + 1，往下面寻找
 * 遍历结束之后，如果没有找到即返回 false
 * <p>
 * 时间复杂度：O(n+m)，如果二维数组的有 n 行 m 列，那么在循环过程中行数不会超过 n 行，列数不会超过 m 列，因此在循环结束之后总的次数不会超过 n+m 次
 * 空间复杂度：0(1)，因为没有额外的开销，在内存中一直都是恒定的
 */
public class MatrixArrayFindNum {


    boolean findNumber(int[][] matrix, int target) {

        if (matrix == null || matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }

        int row = 0;
        int column = matrix[row].length - 1;
        int maxRow = matrix.length - 1;
        while (row <= maxRow && column >= 0) {
            if (matrix[row][column] > target) {
                column--;
            } else if (matrix[row][column] < target) {
                row++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {


        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

        MatrixArrayFindNum matrixArrayFindNum = new MatrixArrayFindNum();
        System.out.println(matrixArrayFindNum.findNumber(matrix, 16));
    }


}
