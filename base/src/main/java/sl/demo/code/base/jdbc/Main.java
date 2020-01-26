package sl.demo.code.base.jdbc;

import java.sql.*;

/*
数据库建表语句

CREATE TABLE `user` (
        `user_id` int(11) NOT NULL AUTO_INCREMENT,
        `user_name` varchar(32) DEFAULT NULL,
        PRIMARY KEY (`user_id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1016 DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` VALUES (1, '张三');
*/

public class Main {

    public static void main(String[] args) throws Exception {

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement pstmt = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;

        try {
            // 1 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2 获取数据库连接
            String url = "jdbc:mysql://192.168.99.101:3306/mydb?user=root&password=root&serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
            conn = DriverManager.getConnection(url);

            // 3 创建statement
            stmt = conn.createStatement();

            // 4 执行查询
            rs = stmt.executeQuery("select * from user");

            // 5 显示结果
            while (rs.next()) {
                System.out.print(rs.getInt("user_id") + "\t");
                System.out.println(rs.getString("user_name"));
            }

            // 6 开启手动事务
            conn.setAutoCommit(false);

            // 7 创建预编译
            pstmt = conn.prepareStatement("update user set user_id=?, user_name=? where user_name=?");
            pstmt.setInt(1, 99);
            pstmt.setString(2, "李四");
            pstmt.setString(3, "张三");
            pstmt.executeUpdate();
            rs = pstmt.executeQuery("select * from user");

            // 显示结果
            while (rs.next()) {
                System.out.print(rs.getInt("user_id") + "\t");
                System.out.println(rs.getString("user_name"));
            }

            // 8 存储过程
            int a = 10;
            int b = 20;

            cstmt = conn.prepareCall("call mySum(?, ?, ?)");
            cstmt.setInt(1, a);
            cstmt.setInt(2, b);
            cstmt.registerOutParameter(3, Types.INTEGER);
            cstmt.execute();

            int c = cstmt.getInt(3);
            System.out.println(String.format("%d + %d = %d", a, b, c));

            // 9 批处理
            pstmt = conn.prepareStatement("insert into user set user_name=?");
            pstmt.setString(1, "王五");
            pstmt.addBatch();
            pstmt.setString(1, "赵六 ");
            pstmt.addBatch();
            pstmt.executeUpdate();

            // 10 滚动结果集
            // 当创建Statement的时候，有两个参数需要指定。
            // 第一个：type_scroll_insensitive,就是结果集拿出来以后，它对滚动的是不是敏感。insensitive：不敏感；
            // 第二个：concur_read_only,当并发访问结果集的时候，只能只读访问；不能更新。
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from user");
            rs.next();
            System.out.println(rs.getInt(1));
            rs.last();
            System.out.println(rs.getInt(1));
            rs.previous();
            System.out.println(rs.getInt(1));
            rs.absolute(2);
            System.out.println(rs.getInt(1));
            System.out.println(rs.isLast());
            System.out.println(rs.isAfterLast());
            System.out.println(rs.getRow());

            // 提交事务
            conn.commit();

            // 关闭手动事务
            conn.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();

            try {
                // 回滚事务
                conn.rollback();

            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                // 关闭连接
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
