package com.mmd.pms.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 配置文件帮助类
 */
public class PropertyUtil {

    /**
     * 配置文件实例
     */
    private static final String PROP_FILE_NAME = "pms.properties";

    /**
     * 私有化当前对象实例
     */
    private static PropertyUtil propertyUtil = new PropertyUtil();

    private PropertyUtil() {}

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader(PROP_FILE_NAME);


    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 获取当前对象实例
     */
    public static PropertyUtil getInstance() {
        return propertyUtil;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null){
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }

}
