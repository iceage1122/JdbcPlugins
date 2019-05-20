package com.iceage.jdbc.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @CLassName Tec
 * @Intro
 * @Description
 * @Author Wang Ziming
 * @Date 2019/4/23 11:05
 **/
@Data
@Table(name = "teacher")
public class Tec {
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
    @Column(name = "class")
    private String className;
}
