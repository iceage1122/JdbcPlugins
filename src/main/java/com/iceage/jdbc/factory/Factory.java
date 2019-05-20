package com.iceage.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @CLassName Factory
 * @Intro
 * @Description
 * @Author Wang Ziming
 * @Date 2019/4/23 9:45
 **/
public class Factory {

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "etoak");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
