package sl.demo.code.algorithm.base.factorial;

/**
 * 阶乘
 * 使用递归方式实现
 * <p>
 * 计算5的阶乘
 */
public class ByRecursion {
    public static void main(String[] args) {
        System.out.println(f(5));
    }

    public static long f(int n) {

        if (n < 1) {
            System.out.println("invalid parameter!");
            return -1;
        }

        if (n == 1) {
            return 1;
        } else {
            return n * f(n - 1);
        }
    }
}
