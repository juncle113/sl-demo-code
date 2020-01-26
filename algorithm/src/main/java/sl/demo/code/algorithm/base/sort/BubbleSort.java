package sl.demo.code.algorithm.base.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void sort(int[] arr) {
        boolean isSort;
        int temp;

        for (int i = 0; i < arr.length - 1; i++) {
            isSort = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j + 1] < arr[j]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
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
