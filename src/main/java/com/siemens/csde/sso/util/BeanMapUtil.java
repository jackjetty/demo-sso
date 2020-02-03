package com.siemens.csde.sso.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.DozerBeanMapper;

/**
 * BeanMapUtil 简单封装Dozer, 实现深度转换Bean<->Bean的Mapper.实现:
 * 1. 持有Mapper的单例. 2. 返回值类型转换. 3. 批量转换Collection中的所有对象. 4. 区分创建新的B对象与将对象A值复制到已存在的B对象两种函数.
 *
 * @author z004267r
 * @version 1.0-SNAPSHOT
 * @date 8/23/2019 3:28 PM
 **/
public class BeanMapUtil {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    //private static DozerBeanMapper dozer = new DozerBeanMapper(Lists.newArrayList("dozer/dozer-mapper.xml"));
    private static DozerBeanMapper dozer = new DozerBeanMapper();
    /**
     * 基于Dozer转换对象的类型.
     *
     * @param source source
     * @param destinationClass destinationClass
     * @return T
     * @author z004267r
     * @date 8/23/2019 3:29 PM
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     *
     * @param sourceList sourceList
     * @param destinationClass destinationClass
     * @return java.util.List<T>
     * @author z004267r
     * @date 8/23/2019 3:29 PM
     */
    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     *
     * @param source source
     * @param destinationObject destinationObject
     * @return void
     * @author z004267r
     * @date 8/23/2019 3:29 PM
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}