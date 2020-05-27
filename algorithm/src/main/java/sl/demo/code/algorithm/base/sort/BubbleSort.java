package sl.demo.code.algorithm.base.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean isSort = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
