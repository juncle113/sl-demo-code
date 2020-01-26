package sl.demo.code.algorithm.base.fibonacci;

/**
 * 斐波那契数列
 * 使用递归方式实现
 * <p>
 * 计算数列第40个数
 */
public class ByRecursion {
    public static void main(String[] args) {
        System.out.println(f(40));
    }

    public static long f(int n) {

        if (n < 1) {
            System.out.println("invalid parameter!");
            return -1;
        }

        if (n == 1 || n == 2) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }
}
