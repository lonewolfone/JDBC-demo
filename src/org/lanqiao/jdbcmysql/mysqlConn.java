package org.lanqiao.jdbcmysql;

import java.sql.*;

public class mysqlConn {
    public  static void main(String[] args) throws SQLException, ClassNotFoundException {
        //加载和注册驱动，通常使用的方法。
        //Mysql的加载和注册驱动
        String mysqlDriver = "com.mysql.jdbc.Driver";
        Class.forName(mysqlDriver);
        //获取Mysql的数据库连接
        String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "root";
        //得到 Connection对象
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        //获取Statement对象
        Statement statement = conn.createStatement();
        //执行静态sql
        //如何得到插入信息的自增长的id值
        String sql = "insert into stu(name,age) values('小陪陪',33)";
        boolean  str = statement.execute(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet res = statement.getGeneratedKeys();
        if (res.next()){
            System.out.println(res.getInt(1));
        }

       /*String sql1 = "delete from stu where id = 2";
        long str1 = statement.executeLargeUpdate(sql1);
        System.out.println(str1);
        String sql2 = "update stu set name = '宙斯' where id = 1";
        long str2 = statement.executeLargeUpdate(sql2);
        System.out.println(str2);
        statement.addBatch(sql);
        statement.addBatch(sql1);
        int[] str3 = statement.executeBatch();
        for(int a:str3) {
            System.out.println(a);
        }*/
    }
}
