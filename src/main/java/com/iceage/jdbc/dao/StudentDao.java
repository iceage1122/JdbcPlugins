package com.iceage.jdbc.dao;

import com.iceage.jdbc.factory.Factory;
import com.iceage.jdbc.po.Student;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @CLassName StudentDao
 * @Intro
 * @Description
 * @Author Wang Ziming
 * @Date 2019/4/23 9:51
 **/
public class StudentDao {

    List<Object> objList = new ArrayList<>();

    Connection connection = null;

    PreparedStatement statement = null;

    ResultSet rs = null;

    public List<?> queryStudent(Object entity){

        Class<?> entityClass = entity.getClass();

        Table table = entityClass.getAnnotation(Table.class);
        String className;
        if (table != null){
            className = table.name();
        } else {
            className = entityClass.getSimpleName().toLowerCase();
        }
        String sql = "select * from " + className;
        StringBuffer sb = new StringBuffer(" where 1 = 1 ");
        try {
            //表对应字段 用于返回
            Map<String,String> rowMapper = new HashMap<>(16);
            //字段对应表 用于查询
            Map<String,String> queryMapper = new HashMap<>(16);
            Field[] fields = entityClass.getDeclaredFields();

            for (Field field: fields) {
                String fieldName = field.getName();
                if (field.isAnnotationPresent(Column.class)){
                    Column annotation = field.getAnnotation(Column.class);
                    String sqlName = annotation.name();
                    rowMapper.put(sqlName,fieldName);
                    queryMapper.put(fieldName,sqlName);
                } else {
                    rowMapper.put(fieldName,fieldName);
                    queryMapper.put(fieldName,fieldName);
                }
                field.setAccessible(true);
                //从entity里的field拿到对应的值
                Object isField = field.get(entity);
                if (isField != null) {
                    sb.append(" and " + queryMapper.get(fieldName) + " = " + isField);
                }
            }

            sql = sql + sb.toString();

            connection = Factory.getConnection();

            statement = connection.prepareStatement(sql);

            rs = statement.executeQuery();

            //得到源数据中字段列的数量
            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()){
                Object instance = entityClass.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    //当前位置列名
                    String name = rs.getMetaData().getColumnName(i);
                    Field field = entityClass.getDeclaredField(rowMapper.get(name));
                    field.setAccessible(true);
                    field.set(instance,rs.getObject(name));
                }
                objList.add(instance);
            }
            return objList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


}
