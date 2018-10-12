package org.lanqiao.jdbcmysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class reslesSouce {
    public static void  main(String[] args){
        String mysqlDriver = "com.mysql.jdbc.Driver";
        Connection conn = null;
        Statement  statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName(mysqlDriver);
            String url = "jdbc:mysql:///test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "root";
            //得到 Connection对象
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            String sql = "select * from stu";
            resultSet = statement.executeQuery(sql);
            //创建一个List对象
            List<Student> studentList = new ArrayList<>();
            //将表中记录封装为对象
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setSex(resultSet.getString("sex"));
                student.setPassword(resultSet.getString("password"));
                //将每次得到的对象放到List中
                studentList.add(student);
            }
            //使用for循环，遍历List
            for(Student str:studentList){
                System.out.println(str);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
