package com.ang.frontui.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 格式判断
 * Description:
 *
 * @author: Administrator
 * @CreateDate: 2016年6月16日
 * @version: V1.0
 */
public class ValidateUtils {

    private static Logger logger = LoggerFactory.getLogger(ValidateUtils.class);

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true ：为空 false 不为空
     */
    public static boolean isEmpty(String str) {
        boolean b = true;
        if (str == null || "".equals(str)) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str 字符串
     * @return true ： 是数字 false ：不是数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str 字符串
     * @return true ： 是数字 false ：不是数字
     */
    public static boolean isNumeric(Object o) {
        String str = String.valueOf(o);
        return isNumeric(str);
    }

    /**
     * 判断字符串是否为金额
     *
     * @param str
     * @return
     */
    public static boolean isAmount(String str) {
        if (str.startsWith("0")) {
            return false;
        }
        return isNumeric(str);

    }


    /**
     * 判断字符串数组是否为空
     *
     * @param str 字符串
     * @return true ：为空 false 不为空
     */
    public static boolean isEmpty(String[] str) {
        boolean b = true;
        if (str == null || str.length == 0) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }


    public static boolean checkIpAddress(String address) {
        String ipFormat = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
        Pattern pattern = Pattern.compile(ipFormat);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean isEmpty(List<? extends Object> list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isEmpty(Map<? extends Object, ? extends Object> map) {
        if (map == null || map.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }


}

