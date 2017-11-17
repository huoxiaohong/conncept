package ztools.vms.actions;

/*
 * Copyright (c) 1996, 2011, JCM and/or its affiliates. All rights reserved.
 */

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.velocity.context.Context;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Describe class <code>AbsAction</code> here.
 * 
 * @author Zhong Lizhi<mailto:zlz.3907@gmail.com>
 * @create Jul 1, 2013
 */
abstract public class AbsAction {
    public static final String PARAM_MENU_FLAG = "menuPoint";

    public Map<String, String> customHeader = new HashMap<>();

    protected enum MenuPage {
        HOME, DB, TABLE, FIELD, VALUE
    }

    /**
     * 初始化全局变量.
     * 
     * @param request a <code>HttpServletRequest</code> value
     * @param context a <code>Context</code> value
     */
    public final static void initContext(HttpServletRequest request, Context context) {
        // context.put("imageHost", Configure.getDatabaseConfigure().getImageHost());
        // boolean isAdmin = Configure.ADMIN_SERVLET_PATH.equals(request
        // .getServletPath());
        // context.put("isAdmin", isAdmin);
        // HttpSession session = request.getSession();
        // context.put("session", session);
        try {
            Class<?> c = Class.forName("org.apache.shiro.SecurityUtils");
            context.put("Security", c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 日期转换工具，将一个长整形的数字转换为指定格式的日期字符串.
     * 
     * @param m a <code>long</code> value
     * @param format a <code>String</code> value
     * @return a <code>String</code> value
     */
    public final static String parseDate(long m, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(m));
    }

    public final static void singleFieldCheck(HttpServletRequest request, Context context) {
        String type = request.getParameter("type");
        String value = request.getParameter("value");

        JSONObject responseJson = new JSONObject();
        try {
            if (isEmpty(type, value)) {
                responseJson.put("warning", "你没有输入任何信息！");
            } else {
                Class<?> c;
                try {
                    c = parseClass(type);
                    parseValue(value, c);
                } catch (Exception e) {
                    e.printStackTrace();
                    responseJson.put("warning", "你输入的字符类型不匹配！");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        context.put("responseText", responseJson);
    }

    public final static Object parseValue(String value, Class<?> type) throws Exception {
        Object p = null;

        // value = iXmlProcess.read(value);
        if (type.equals(String.class)) {
            p = null != value ? value : "";
        } else if (type.equals(Double.class) || "double".equals(type.getSimpleName())) {
            p = null != value ? Double.parseDouble(value) : 0.00d;
        } else if (type.equals(Float.class) || "float".equals(type.getSimpleName())) {
            p = null != value ? Float.parseFloat(value) : 0.0f;
        } else if (type.equals(Long.class) || "long".equals(type.getSimpleName())) {
            p = null != value ? Long.parseLong(value) : 0l;
        } else if (type.equals(Integer.class) || "int".equals(type.getSimpleName())) {
            p = null != value ? Integer.parseInt(value) : 0;
        } else if (type.equals(Short.class) || "short".equals(type.getSimpleName())) {
            p = null != value ? Short.parseShort(value) : 0;
        } else if (type.equals(Boolean.class) || "boolean".equals(type.getSimpleName())) {
            p = null != value ? Boolean.parseBoolean(value) : false;
        } else if (type.equals(Date.class)) {
            p = DateFormat.getDateInstance(0, Locale.getDefault()).parse(value);
        } else if (type.equals(Character.class) || "char".equals(type.getSimpleName())) {
            if (null != value && value.length() > 0) {
                p = value.charAt(0);
            } else {
                p = '0';
            }
        } else if (type.equals(Byte.class) || "byte".equals(type.getSimpleName())) {
            p = null != value ? Byte.parseByte(value) : 0;
        }
        return p;
    }

    private static Class<?> parseClass(String name) {
        Class<?> ret = null;
        if (!isEmpty(name)) {
            try {
                if (1 == name.split("[.]").length) {
                    ret = Class.forName("java.lang." + name);
                } else {
                    ret = Class.forName(name);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public String addHeader(String key, String value) {
        return customHeader.put(key, value);
    }

    public Map<String, String> getHeader() {
        return customHeader;
    }

    protected static boolean isEmpty(Object... objects) {
        for (Object obj : objects) {
            if (null == obj || "".equals(obj)) {
                return true;
            }
        }
        return false;
    }

    abstract public void setContext(HttpServletRequest request, Context context);

}
