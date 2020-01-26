package sl.demo.code.base.loader;

/**
 * 自定义类加载器入口
 *
 * @author sunli
 * @date 2019/07/11
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // 1 取得加载类文件路径
        String classPath = System.getProperty("user.dir") + "/src/higher/loader/";

        // 2 创建自定义类加载器
        MyClassLoader myClassLoader = new MyClassLoader(classPath);

        // 3 使用自定义类加载器加载类文件
        Class clazz = myClassLoader.loadClass("higher.loader.MyClass");

        // 4 生成该加载类实例，并执行静态方法
        clazz.newInstance();
    }
}