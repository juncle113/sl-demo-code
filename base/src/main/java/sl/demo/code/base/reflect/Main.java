package sl.demo.code.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        // 1 加载类
        Class robotClass = Class.forName("higher.reflect.Robot");

        // 2 生成该类实例
        Robot robot = (Robot) robotClass.newInstance();

        // 3 执行共有方法
        // 3.1 取得共有方法
        Method throwHello = robotClass.getMethod("throwHello", String.class);

        // 3.2 调用该共有方法（需具有该方法的实例和方法参数）
        Object s = throwHello.invoke(robot, "robot");

        // 3.3 显示结果
        System.out.println(s);

        // 4 执行私有方法
        // 4.1 取得私有属性
        Field name = robotClass.getDeclaredField("name");

        // 4.2 设置私有属性可访问
        name.setAccessible(true);

        // 4.3 设置私有属性值
        name.set(robot, "MyClass");

        // 4.4 取得私有方法
        Method sayHi = robotClass.getDeclaredMethod("sayHi", String.class);

        // 4.5 设置私有方法可访问
        sayHi.setAccessible(true);

        // 4.6 调用该私有方法（需要具有该方法的实例和方法参数）
        sayHi.invoke(robot, "hi");
    }
}