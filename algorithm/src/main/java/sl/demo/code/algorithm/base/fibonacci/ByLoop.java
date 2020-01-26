package sl.demo.code.algorithm.base.fibonacci;

/**
 * 斐波那契数列
 * 使用循环方式实现
 * <p>
 * 计算数列第40个数
 */
public class ByLoop {
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
        }

        long f1 = 1L;
        long f2 = 1L;
        long fn = 0;

        for (int i = 3; i <= n; i++) {
            fn = f1 + f2;
            f1 = f2;
            f2 = fn;
        }

        return fn;
    }
}
