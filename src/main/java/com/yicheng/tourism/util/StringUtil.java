package com.yicheng.tourism.util;




import com.yicheng.tourism.base.enums.CycleEnums;
import org.apache.commons.lang.StringUtils;

import java.net.URLEncoder;
import java.util.*;

/**
 * 字符串工具类
 *
 * @author jianyang
 */
public class StringUtil {

    private static final Integer PHONE_LENGTH = 11;

    private static final Integer CARD_LENGTH = 18;

    /**
     * 字符串是否为空，包括blank
     *
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return null != str && 0 != str.trim().length() ? false : true;
    }

    /**
     * 空字符串转换为""
     *
     * @param str
     * @return
     */
    public static String null2Empty(String str) {
        return isNullOrEmpty(str) ? "" : str;
    }


    /**
     * 检查指定的字符串是否为空。
     * <ul>
     * <li>SysUtils.isEmpty(null) = true</li>
     * <li>SysUtils.isEmpty("") = true</li>
     * <li>SysUtils.isEmpty("   ") = true</li>
     * <li>SysUtils.isEmpty("abc") = false</li>
     * </ul>
     *
     * @param value 待检查的字符串
     * @return true/false
     */
    public static boolean isEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(value.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查对象是否为数字型字符串,包含负数开头的。
     */
    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1)
            return false;

        int i = 0;
        if (length > 1 && chars[0] == '-')
            i = 1;

        for (; i < length; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查指定的字符串列表是否不为空。
     */
    public static boolean areNotEmpty(String... values) {
        boolean result = true;
        if (values == null || values.length == 0) {
            result = false;
        } else {
            for (String value : values) {
                result &= !isEmpty(value);
            }
        }
        return result;
    }

    /**
     * 把通用字符编码的字符串转化为汉字编码。
     */
    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!isEmpty(unicode)) {
            for (int i = 0; i < unicode.length(); i++) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    /**
     * 过滤不可见字符
     */
    public static String stripNonValidXMLCharacters(String input) {
        if (input == null || ("".equals(input)))
            return "";
        StringBuilder out = new StringBuilder();
        char current;
        for (int i = 0; i < input.length(); i++) {
            current = input.charAt(i);
            if ((current == 0x9) || (current == 0xA) || (current == 0xD)
                    || ((current >= 0x20) && (current <= 0xD7FF))
                    || ((current >= 0xE000) && (current <= 0xFFFD))
                    || ((current >= 0x10000) && (current <= 0x10FFFF)))
                out.append(current);
        }
        return out.toString();
    }

    /**
     * 去空处理
     *
     * @param input
     * @return
     */
    public static String strTrim(String input) {
        if (StringUtil.isNullOrEmpty(input)) {
            return "";
        } else {
            return input.trim();
        }
    }

    /**
     * 方法用途: 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序），并且生成url参数串<
     *
     * @param paramsMap  要排序的Map对象
     * @param urlEncode  是否需要URLENCODE
     * @param keyToLower 是否需要将Key转换为全小写 true:key转化成小写，false:不转化
     * @return
     */
    public static String formatUrlMap(Map<String, String> paramsMap, boolean urlEncode, boolean keyToLower) {

        String buff = "";
        Map<String, String> tmpMap = paramsMap;

        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());

            //对所有传入参数按照字段名的ASCII码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });

            //构造URL 键值对的格式
            StringBuffer buf = new StringBuffer();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String value = item.getValue();
                    if (urlEncode) {
                        value = URLEncoder.encode(value, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + value);
                    } else {
                        buf.append(key + "=" + value);
                    }
                    buf.append("&");
                }
            }
            buff = buf.toString();

            if (StringUtils.isNotEmpty(buff)) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return buff;
    }


    /**
     * 遮挡手机号
     *
     * @param phone
     * @return
     */
    public static String coverPhone(String phone) {
        if (isEmpty(phone)) {
            return null;
        }
        if (phone.length() != PHONE_LENGTH) {
            return phone;
        }

        StringBuilder sb = new StringBuilder(phone);
        sb.replace(3, 7, "****");
        return sb.toString();
    }


    public static String converCard(String card) {
        if (isEmpty(card)) {
            return null;
        }
        if (card.length() != CARD_LENGTH) {
            return card;
        }
        StringBuilder str = new StringBuilder(card);
        str.replace(4, 14, "****");
        return str.toString();
    }

    private static final String ORDER_ASC_WEB = "+";
    private static final String ORDER_DESC_WEB = "-";

    private static final String ORDER_ASC_SQL = "asc";
    private static final String ORDER_DESC_SQL = "desc";

    /**
     * web order 格式转sql order
     *
     * @param order
     * @return
     */
    public static String coverOrderBy(String order) {
        String sqlOrder = null;
        if (!org.springframework.util.StringUtils.hasLength(order)) {
            return sqlOrder;
        }
        order = order.replaceAll(" ", "").replaceAll("=", "");
        if (order.indexOf(ORDER_ASC_WEB) >= 0) {
            sqlOrder = order.replace(ORDER_ASC_WEB, "").concat(" ").concat(ORDER_ASC_SQL);
        } else if (order.indexOf(ORDER_DESC_WEB) >= 0) {
            sqlOrder = order.replace(ORDER_DESC_WEB, "").concat(" ").concat(ORDER_DESC_SQL);
        } else {
            sqlOrder = order.concat(" ").concat(ORDER_ASC_SQL);
        }
        return sqlOrder;
    }


    public static String coverNewOrderBy(String orderFiled, String orderType) {
        String sqlOrder = null;
        if (!org.springframework.util.StringUtils.hasLength(orderFiled)) {
            return sqlOrder;
        }
        //0升序，1降序
        orderFiled = orderFiled.replaceAll(" ", "").replaceAll("=", "");
        if ("0".equals(orderType)) {
            sqlOrder = orderFiled.replace(ORDER_ASC_WEB, "").concat(" ").concat(ORDER_ASC_SQL);
        } else if ("1".equals(orderType)) {
            sqlOrder = orderFiled.replace(ORDER_DESC_WEB, "").concat(" ").concat(ORDER_DESC_SQL);
        } else {
            sqlOrder = orderFiled.concat(" ").concat(ORDER_ASC_SQL);
        }
        return sqlOrder;
    }


    public static String replaceCycleString(String queryCriteria) {
        queryCriteria = strTrim(queryCriteria);
        queryCriteria = queryCriteria.replace(CycleEnums.MONTH.getDesc(), CycleEnums.MONTH.getCode());
        queryCriteria = queryCriteria.replace(CycleEnums.NATURAL_MONTH.getDesc(), CycleEnums.NATURAL_MONTH.getCode());
        queryCriteria = queryCriteria.replace(CycleEnums.DAY.getDesc(), CycleEnums.DAY.getCode());
        return queryCriteria;

    }


}
