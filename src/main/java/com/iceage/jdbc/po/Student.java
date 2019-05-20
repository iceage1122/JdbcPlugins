package com.iceage.jdbc.po;

import lombok.Data;

import javax.persistence.Table;

/**
 * @CLassName Student
 * @Intro
 * @Description
 * @Author Wang Ziming
 * @Date 2019/4/23 9:27
 **/
@Data
//@Table(name = "student")
public class Student {
    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * age
     */
    private Integer age;

    /**
     * add
     */
    private String add;
}
