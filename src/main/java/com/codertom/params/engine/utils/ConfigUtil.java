package com.codertom.params.engine.utils;

import com.codertom.params.engine.common.Constants;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 参数配置工具类
 */
public class ConfigUtil {

    private static volatile Properties PROPERTIES;
    private static Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    /**
     * 获取整体的配置参数
     * @return
     */

    public static Properties getProperties(){
        if (PROPERTIES==null){
            synchronized (ConfigUtil.class){
                if (PROPERTIES==null){
                    String confName = System.getProperty(Constants.ENGINE_CONFNAMEKEY);
                    if (!Strings.nullToEmpty(confName).trim().isEmpty()){
                        confName = System.getenv(Constants.ENGINE_CONFNAMEKEY);
                        if (!Strings.nullToEmpty(confName).trim().isEmpty()){
                            confName = Constants.ENGINE_CONFNAME_DEFAULT;
                        }
                    }
                    PROPERTIES = loadProperties(confName);
                }
            }
        }
        return PROPERTIES;
    }

    /**
     * 参数加载
     * @param confName
     * @return
     */
    private static Properties loadProperties(String confName){
        Properties properties = new Properties();
        if (!Strings.nullToEmpty(confName).trim().isEmpty()){
            // 先加载可能带有绝对路径的配置
            File f = new File(confName);
            if (f.exists()) {
                try (FileInputStream input = new FileInputStream(confName)) {
                    properties.load(input);
                } catch (Exception e) {
                    logger.warn("Failed to load " + confName + "from local (ingore this file): " + e.getMessage(), e);
                }
                return properties;
            }

            //当前目录不存在的话，那么在ClassPath中找
            InputStream inputClassPath = ConfigUtil.class.getClassLoader().getResourceAsStream(confName);
            if(inputClassPath!=null){
                try {
                    properties.load(inputClassPath);
                } catch (IOException e) {
                    logger.warn("Failed to load " + confName + "from classpath (ingore this file): " + e.getMessage(), e);
                }
                return properties;
            }

            // 如果path的文件不存在,那么加载带有前缀的文件目录
            confName = Constants.ENGINE_CONFPATH_DEFAULT + confName;
            File defFile = new File(confName);
            if (defFile.exists()) {
                try (FileInputStream input = new FileInputStream(confName)) {
                    properties.load(input);
                } catch (Exception e) {
                    logger.warn("Failed to load " + confName + " from defaulf prefix path (ingore this file): " + e.getMessage(), e);
                }
                return properties;
            }
        }
        return null;
        }

    /**
     * 根据key获取参数
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return getProperty(key, null);
    }

    /**
     * 根据key和默认值获取参数
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value != null && value.length() > 0) {
            return value;
        }
        Properties properties = getProperties();
        if(properties.getProperty(key, null)==null){
            return defaultValue;
        }
        return properties.getProperty(key, null);
    }

    /**
     * 根据String类型的key获取Int类型的参数
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getIntByStringFromParameters(String key,int defaultValue) {
        Properties prop = getProperties();
        String valStr = prop.getProperty(key, null);
        if (valStr != null && !"".equals(valStr)) {
            try {
                Integer value = Integer.parseInt(valStr);
                return value;
            } catch (Exception e) {
                logger.warn("parsing String to int error " + valStr +" will be return default value"+ e.getMessage(), e);
            }
        }
        return defaultValue;
    }




}
