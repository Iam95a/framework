package com.chen.framework.helper;

import com.chen.framework.constant.ConfigConstant;
import com.chen.framework.util.PropsUtil;

import java.util.Properties;

public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);


    public static String getJdbcDriver() {
        return CONFIG_PROPS.getProperty(ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return CONFIG_PROPS.getProperty(ConfigConstant.JDBC_URL);
    }

    public static String getJdbcPassword() {
        return CONFIG_PROPS.getProperty(ConfigConstant.JDBC_PASSWORD);
    }

    public static String getJdbcUsername() {
        return CONFIG_PROPS.getProperty(ConfigConstant.JDBC_USERNAME);
    }

    public static String getAppBasePackage() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssetPath() {
        return CONFIG_PROPS.getProperty(ConfigConstant.APP_ASSET_PATH);
    }

}
