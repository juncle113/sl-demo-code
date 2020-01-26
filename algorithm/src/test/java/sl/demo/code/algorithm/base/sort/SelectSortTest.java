package sl.demo.code.algorithm.base.sort;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectSortTest extends Base{

    @Test
    public void sort() {
        SelectSort.sort(arr);
    }
}
