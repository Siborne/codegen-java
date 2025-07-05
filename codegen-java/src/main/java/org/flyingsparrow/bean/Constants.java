package org.flyingsparrow.bean;

import org.flyingsparrow.utils.PropertiesUtils;

/**
 * 常量类
 *
 * @author Siborne
 */
public class Constants {
    public static Boolean IGNORE_TABLE_PERFIX;

    public static String SUFFIX_BEAN_PARAM;

    static {
        IGNORE_TABLE_PERFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM = PropertiesUtils.getProperty("suffix.bean.param");
    }
}
