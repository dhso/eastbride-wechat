/*
 * Copyright (C) 2013, Apexes Network Technology. All rights reserved.
 *
 *       http://www.apexes.net
 *
 */
package frame.sdk.fetion.client;

import java.lang.reflect.Method;

import frame.sdk.fetion.user.User;
import frame.sdk.fetion.util.StringHelper;
import frame.sdk.fetion.util.XmlElement;

/**
 *
 * @author HeDYn <hedyn@foxmail.com>
 */
public final class UserHelper {
    
    public static Object toUser(XmlElement xml) {
        String className = StringHelper.firstToUpperCase(xml.getName());
        className = "frame.sdk.fetion.user." + className;
        try {
            Class<?> clazz = Class.forName(className);
            Object user = clazz.newInstance();
            for (String name : xml.getAttributeNames()) {
                String methodName = StringHelper.firstToUpperCase(name);
                Method get = clazz.getMethod("get" + methodName);
                Class<?> c = get.getReturnType();
                Method set = clazz.getMethod("set" + methodName, c);
                Object value;
                if (c == int.class) {
                    value = xml.getIntAttribute(name);
                } else if (c == long.class) {
                    value = xml.getLongAttribute(name);
                } else if (c.isEnum()) {
                    int i = xml.getIntAttribute(name);
                    Method m = c.getMethod("valueOf", int.class);
                    value = m.invoke(user, i);
                } else {
                    value = xml.getAttribute(name);
                }
                set.invoke(user, value);
            }
            return user;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static XmlElement toXml(User user) {
        XmlElement el = new XmlElement();
        Class<? extends User> clazz = user.getClass();
        el.setName(StringHelper.firstToLowerCase(clazz.getSimpleName()));
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")) {
                String name = methodName.substring(3, methodName.length());
                try {
                    Method get = clazz.getMethod("get" + name);
                    Class<?> c = get.getReturnType();
                    Object value = get.invoke(user);
                    if (c.isEnum()) {
                        Method m = c.getMethod("getValue");
                        value = m.invoke(value);
                    }
                    if (value != null) {
                        el.setAttribute(StringHelper.firstToLowerCase(name), value);
                    }
                } catch (Exception ex) {
                }
            }
        }
        return el;
    }
    
}
