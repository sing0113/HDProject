package com.hand.hjmall.utils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;


/**
 * 
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *  判断对象是否为null<br>
 * @author 14358
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class EmptyUtil {

    /**
     * 
     * 功能描述: 判断对象是否为 null<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNull(Object object) {
        return null == object;
    }

    /**
     * 
     * 功能描述: 判断数组是否为 null 或空<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Object[] object) {
        return isNull(object) || object.length < 1;
    }

    /**
     * 
     * 功能描述: 判断集合是否为 null 或空<br>
     * 〈功能详细描述〉
     *
     * @param collection
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.isEmpty();
    }

    /**
     * 
     * 功能描述: 判断 map 是否为 null 或空<br>
     * 〈功能详细描述〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.size() < 1;
    }

    /**
     * 
     * 功能描述: 判断字符串是否为 null 或空<br>
     * 〈功能详细描述〉
     *
     * @param text
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(String text) {
        return isNull(text) || text.length() < 1;
    }

    /**
     * 
     * 功能描述: 判断对象是否有值<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNotNull(Object object) {
        return !isEmpty(object);
    }

    /**
     * 
     * 功能描述: 判断数组是否有值<br>
     * 〈功能详细描述〉
     *
     * @param ediNtf
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
 

    /**
     * 
     * 功能描述: 判断集合是否有值<br>
     * 〈功能详细描述〉
     *
     * @param collection
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 
     * 功能描述: 判断 map 是否有值<br>
     * 〈功能详细描述〉
     *
     * @param map
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 
     * 功能描述: 判断 String 是否有值<br>
     * 〈功能详细描述〉
     *
     * @param text
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }
    
    /**
     * 
     * 功能描述: 判断 Timestamp 是否有值<br>
     * 〈功能详细描述〉
     *
     * @param text
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isNotEmpty(Timestamp text) {
        return !isEmpty(text);
    }
    
    /**
     * 
     * 功能描述: 判断时间戳是否为 null<br>
     * 〈功能详细描述〉
     *
     * @param text
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Timestamp text) {
        return isNull(text);
    }

    /**
     * 
     * 功能描述: 参数进行判断的方法<br>
     * 〈功能详细描述〉
     *
     * @param object
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isEmpty(Object object) {
        if (object instanceof Collection) {
            return isEmpty((Collection<?>) object);
        } else if (object instanceof Map) {
            return isEmpty((Map<?, ?>) object);
        }
        return isNull(object) || "".equals(object);
    }
}
