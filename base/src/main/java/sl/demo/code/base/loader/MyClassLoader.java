package sl.demo.code.base.loader;

import java.io.*;

/**
 * 自定义类加载器
 *
 * @author sunli
 * @date 2019/07/11
 */
public class MyClassLoader extends ClassLoader {

    private String path;
    private static final String SUFFIX = ".class";

    MyClassLoader(String path) {
        this.path = path;
    }

    /**
     * 查找加载类
     */
    @Override
    public Class findClass(String name) {

        // 1 读取加载类信息
        byte[] b = loadClassData(name);

        // 2 根据读取的信息定义加载类
        return defineClass(name, b, 0, b.length);
    }

    /**
     * 通过字节流方式，读取加载类信息
     */
    private byte[] loadClassData(String name) {

        byte[] result = null;
        name = path + name + SUFFIX;

        try (InputStream in = new FileInputStream(new File(name));
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            int temp;
            while ((temp = in.read()) != -1) {
                out.write(temp);
            }

            result = out.toByteArray();
        } finally {
            return result;
        }
    }
}