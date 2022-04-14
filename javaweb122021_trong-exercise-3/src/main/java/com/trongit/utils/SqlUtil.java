package com.trongit.utils;

import com.trongit.annotation.searchbuilding.LikeField;
import com.trongit.annotation.searchbuilding.OperatorField;
import com.trongit.annotation.searchbuilding.SearchObject;
import com.trongit.builder.SpecialQueryBuilder;

import java.lang.reflect.Field;

public class SqlUtil {
    public static String buildQuery(Object obj, SpecialQueryBuilder... specialQueryBuilder) {
        try {
            String select = "";
            StringBuilder join = new StringBuilder("");
            StringBuilder where = new StringBuilder("\nwhere 1=1");
            Class<?> tClass = obj.getClass();
            if (tClass.isAnnotationPresent(SearchObject.class)) {
                SearchObject searchObject = tClass.getAnnotation(SearchObject.class);
                String tableName = searchObject.alias().isEmpty() ? searchObject.value() : searchObject.alias();
                if (tableName.isEmpty())
                    throw new RuntimeException("Require Table Name !!!");
                select += String.format("select * from %s ", searchObject.alias().isEmpty() ? tableName : searchObject.value() + " as " + tableName);
                Field[] fields = tClass.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    isLikeField(field, where, tableName, obj);
                    isOperatorField(field, where, tableName, obj);
                }
                if (specialQueryBuilder.length == 1) {
                    specialQueryBuilder[0].getJoins().forEach(join::append);
                    specialQueryBuilder[0].getWheres().forEach(join::append);
                }
                where.append("\n").append(searchObject.groupBy() ? "group by " + tableName + "." + "id" : "");
            }
            System.out.println(select + join + where);
            System.out.println("==============================");
            return select + join + where;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void isLikeField(Field field, StringBuilder where, String tableName, Object obj) {
        try {
            if (field.isAnnotationPresent(LikeField.class)) {
                LikeField likeField = field.getAnnotation(LikeField.class);
                String nameField = likeField.name().isEmpty() ? field.getName().toLowerCase() : likeField.name();
                if (ValidateUtil.isValid(field.get(obj)))
                    where.append(String.format("\nand %s.%s like '%s'", tableName, nameField, field.get(obj)));
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static void isOperatorField(Field field, StringBuilder where, String tableName, Object obj) {
        try {
            if (field.isAnnotationPresent(OperatorField.class)) {
                OperatorField operatorField = field.getAnnotation(OperatorField.class);
                String nameField = operatorField.name().isEmpty() ? field.getName().toLowerCase() : operatorField.name();
                if (ValidateUtil.isValid(field.get(obj))) {
                    if (field.getType().toString().equals("class java.lang.String")) {
                        where.append(String.format("\nand %s.%s = '%s'", tableName, nameField, field.get(obj)));
                    }
                    if (field.getType().toString().equals("class java.lang.Integer") || field.getType().toString().equals("class java.lang.Long")
                            || field.getType().toString().equals("class java.lang.Double") || field.getType().toString().equals("class java.lang.Float")) {
                        where.append(String.format("\nand %s.%s = %s", tableName, nameField, field.get(obj)));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
