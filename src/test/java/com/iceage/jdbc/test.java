package com.iceage.jdbc;

import com.iceage.jdbc.dao.StudentDao;
import com.iceage.jdbc.po.Student;
import com.iceage.jdbc.po.Tec;

/**
 * @CLassName test
 * @Intro
 * @Description
 * @Author Wang Ziming
 * @Date 2019/4/23 10:29
 **/
public class test {
   public static void main(String[] args) {
        StudentDao dao = new StudentDao();
        Student stu = new Student();
        Tec tc = new Tec();
        tc.setClassName("322");
        System.out.println(dao.queryStudent(tc));
    }
}
