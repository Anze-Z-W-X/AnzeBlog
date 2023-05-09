package com.anze.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){}

    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V res = null;
        try {
            res = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, res);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return res;
    }

    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz){
        return list.stream().map(o -> copyBean(o, clazz)).collect(Collectors.toList());
    }
}
