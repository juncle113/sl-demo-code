package sl.demo.code.base.loader;

/**
 * 自定义类加载器加载用类
 *
 * @author sunli
 * @date 2019/07/11
 */
public class MyClass {

    static {
        // 创建实例的时候执行该方法
        System.out.println("Hello MyClass");
    }
}
