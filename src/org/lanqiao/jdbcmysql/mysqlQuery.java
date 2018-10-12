package org.lanqiao.jdbcmysql;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class mysqlQuery {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, UnsupportedEncodingException {
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
        Statement sta = conn.createStatement();
        //执行静态sql,查询语句,查询一条记录
        String sql = "select * from stu where id = 1";
        ResultSet res =	sta.executeQuery(sql);
        boolean b =  res.next();//判断是否存在下一条记录
        System.out.println(b);
        //int id = res.getInt(1);//根据列的索引来获取列值
        //System.out.println(id);
        int id1 = res.getInt("id");//根据字段名来获取该字段对应的值
        System.out.println(id1);
        String name =  res.getString(2);
        System.out.println(name);
        int age = res.getInt(3);
        System.out.println(age);
        String sex = res.getString("sex");
        System.out.println(sex);
        res.last();
        System.out.println("-----------------------------------------------------------");
        //执行静态sql,查询语句,查询多条记录
        String sql1 = "select * from stu ";
        ResultSet res1 =sta.executeQuery(sql1);
        while (res1.next()){
            int sid = res1.getInt("id");
            String sname =  res1.getString("name");
            int sage = res1.getInt("age");
            String ssex = res1.getString("sex");
            String password1 = res1.getString("password");
            System.out.println("-------------------------------------");
            //对于用户的密码验证 当用户在登陆界面输入密码之后，使用Java代码进行一次md5的加密，加密后与数据库中的加密密码进行比较
            //用户从界面输入密码
            String userpaw = "12345";
            MessageDigest messageDigest = MessageDigest.getInstance("md5");//获取摘要对象
            messageDigest.update(userpaw.getBytes("utf-8"));//更新摘要
            String ss = new BigInteger(1,messageDigest.digest()).toString(16);//获取加密后的字符串
            System.out.println(sid + "--"+ sname +"--" +sage +"--" +ssex +"--"+password1 + "--"+ss);
        }
    }
}
