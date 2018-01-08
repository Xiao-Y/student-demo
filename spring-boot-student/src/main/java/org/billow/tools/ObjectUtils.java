package org.billow.tools;

/**
 * @author liuyongtao
 * @create 2018-01-08 15:27
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ObjectUtils {
    private static final Log logger = LogFactory.getLog(ObjectUtils.class);
    public static final String SPLIT_DELIMITER = "^";
    public static Class<Object>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static Object[] EMPTY_OBJECT_ARRAY = new Object[0];
    private static Map<String, Method[]> methodMap = new ConcurrentHashMap();

    private ObjectUtils() {
    }

    public static String getClassNameWithoutPackage(Class cl) {
        String className = cl.getName();
        int pos = className.lastIndexOf(46) + 1;
        if (pos == -1) {
            pos = 0;
        }

        return className.substring(pos);
    }

    public static void printConstructors(Class cl) {
        logger.info("    //Constructors");
        Constructor[] constructors = cl.getDeclaredConstructors();

        for (int i = 0; i < constructors.length; ++i) {
            logger.info("    " + Modifier.toString(constructors[i].getModifiers()));
            logger.info(" " + constructors[i].getName() + "(");
            Class[] paramTypes = constructors[i].getParameterTypes();

            for (int j = 0; j < paramTypes.length; ++j) {
                if (j > 0) {
                    logger.info(", ");
                }

                logger.info(paramTypes[j].getName());
            }

            logger.info(")");
            Class[] exceptions = constructors[i].getExceptionTypes();

            for (int j = 0; j < exceptions.length; ++j) {
                if (j == 0) {
                    logger.info("throws ");
                } else if (j > 0) {
                    logger.info(", ");
                }

                logger.info(exceptions[j].getName());
            }

            logger.info(";");
        }

    }

    public static void printMethods(Class cl) {
        logger.info("    //Methods");
        Method[] methods = getMethods(cl);

        for (int i = 0; i < methods.length; ++i) {
            logger.info("    " + Modifier.toString(methods[i].getModifiers()));
            logger.info(" " + methods[i].getReturnType() + " " + methods[i].getName() + "(");
            Class[] paramTypes = methods[i].getParameterTypes();

            for (int j = 0; j < paramTypes.length; ++j) {
                if (j > 0) {
                    logger.info(", ");
                }

                logger.info(paramTypes[j].getName());
            }

            logger.info(")");
            Class[] exceptions = methods[i].getExceptionTypes();

            for (int j = 0; j < exceptions.length; ++j) {
                if (j == 0) {
                    logger.info("throws ");
                } else if (j > 0) {
                    logger.info(", ");
                }

                logger.info(exceptions[j].getName());
            }

            logger.info(";");
        }

    }

    public static void printFields(Class cl) throws Exception {
        logger.info("    //Fields");
        Field[] fields = cl.getDeclaredFields();

        for (int i = 0; i < fields.length; ++i) {
            logger.info("    " + Modifier.toString(fields[i].getModifiers()));
            logger.info(" " + fields[i].getType() + " " + fields[i].getName());
            logger.info(";");
        }

    }

    public static Object invoke(String className, String methodName, Class[] argsClass, Object[] args) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class cl = Class.forName(className);
        Method method = getMethod(cl, methodName, argsClass);
        return method.invoke(cl.newInstance(), args);
    }

    public static Object invoke(Object oldObject, String methodName, Class[] argsClass, Object[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method method = getMethod(oldObject.getClass(), methodName, argsClass);
        return method.invoke(oldObject, args);
    }

    public static String[] getFieldsName(Class cl) throws Exception {
        Field[] fields = cl.getDeclaredFields();
        int size = fields.length;
        String[] fieldNames = new String[size];

        for (int i = 0; i < size; ++i) {
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }

    public static List<String> getAllFieldName(Class cl) {
        List<String> list = new LinkedList();
        Map<String, String> map = new HashMap();
        initAllFieldName(cl, list, map);
        return list;
    }

    private static void initAllFieldName(Class cl, List<String> list, Map<String, String> map) {
        Field[] fields = cl.getDeclaredFields();
        int size = fields.length;

        for (int i = 0; i < size; ++i) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (!fieldName.equals("serialVersionUID") && !map.containsKey(fieldName)) {
                map.put(fieldName, null);
                list.add(fieldName);
            }
        }

        Class superClass = cl;

        while (true) {
            superClass = superClass.getSuperclass();
            if (superClass == Object.class) {
                return;
            }

            list.addAll(getAllFieldName(superClass));
        }
    }

    private static void initClassMethod(Class cl) {
        String key = cl.getName();
        if (!methodMap.containsKey(key)) {
            Map<String, String> map = new HashMap();
            List<Method> list = new LinkedList();
            initClassMethodInner(cl, list, map);
            Method[] methods = new Method[list.size()];
            list.toArray(methods);
            methodMap.put(key, methods);
        }
    }

    private static void initClassMethodInner(Class cl, List<Method> list, Map<String, String> map) {
        Method[] methods = cl.getDeclaredMethods();
        int size = methods.length;

        for (int i = 0; i < size; ++i) {
            Method method = methods[i];
            String key = method.toString();
            if (!map.containsKey(key)) {
                map.put(key, null);
                list.add(method);
            }
        }

        Class superClass = cl;

        while (true) {
            superClass = superClass.getSuperclass();
            if (superClass == Object.class) {
                return;
            }

            initClassMethodInner(superClass, list, map);
        }
    }

    public static Method[] getMethods(Class cl) {
        initClassMethod(cl);
        return (Method[]) methodMap.get(cl.getName());
    }

    public static List<Method> getSetter(Class cl) {
        Method[] list = getMethods(cl);
        List<Method> resultList = new ArrayList();
        Method[] var7 = list;
        int var6 = list.length;

        for (int var5 = 0; var5 < var6; ++var5) {
            Method method = var7[var5];
            String methodName = method.getName();
            if (methodName.length() >= 3 && method.getParameterTypes().length == 1 && methodName.charAt(0) == 's' && methodName.charAt(1) == 'e' && methodName.charAt(2) == 't') {
                resultList.add(method);
            }
        }

        return resultList;
    }

    public static List<Method> getGetter(Class cl) {
        Method[] list = getMethods(cl);
        List<Method> resultList = new ArrayList();
        Method[] var7 = list;
        int var6 = list.length;

        for (int var5 = 0; var5 < var6; ++var5) {
            Method method = var7[var5];
            String methodName = method.getName();
            if (methodName.length() >= 3 && method.getParameterTypes().length == 0) {
                if (methodName.charAt(0) == 'g' && methodName.charAt(1) == 'e' && methodName.charAt(2) == 't') {
                    resultList.add(method);
                } else if (methodName.charAt(0) == 'i' && methodName.charAt(1) == 's') {
                    resultList.add(method);
                }
            }
        }

        return resultList;
    }

    public static Method getMethod(Class cl, String methodName, Class[] argsClass) throws SecurityException, NoSuchMethodException {
        Method[] list = getMethods(cl);
        int argsLength = 0;
        if (argsClass != null) {
            argsLength = argsClass.length;
        }

        Method[] var11 = list;
        int var10 = list.length;

        for (int var9 = 0; var9 < var10; ++var9) {
            Method method = var11[var9];
            if (method.getName().equals(methodName)) {
                Class[] types = method.getParameterTypes();
                int typeLength = types.length;
                if (typeLength == argsLength) {
                    boolean same = true;

                    for (int i = 0; i < typeLength; ++i) {
                        if (!types[i].equals(argsClass[i])) {
                            same = false;
                            break;
                        }
                    }

                    if (same) {
                        return method;
                    }
                }
            }
        }

        throw new NoSuchMethodException(methodName);
    }

    public static Method getGetterMethod(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return getGetterMethod(bean.getClass(), name);
    }

    public static Method getGetterMethod(Class cl, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = getGetterMethodIfExist(cl, name);
        if (method == null) {
            throw new NoSuchMethodException(name);
        } else {
            return method;
        }
    }

    public static Method getGetterMethodIfExist(Class cl, String name) {
        Method[] list = getMethods(cl);
        String pName = StringUtils.upperCaseFirstChar(name);
        StringBuilder sb = new StringBuilder();
        String isMethod = sb.append("is").append(pName).toString();
        sb.setLength(0);
        String getMethod = sb.append("get").append(pName).toString();
        Method method = null;
        Method[] var12 = list;
        int var11 = list.length;

        for (int var10 = 0; var10 < var11; ++var10) {
            Method loopMethod = var12[var10];
            if (loopMethod.getParameterTypes().length == 0) {
                String methodName = loopMethod.getName();
                if (methodName.equals(getMethod)) {
                    method = loopMethod;
                    break;
                }

                if (methodName.equals(isMethod)) {
                    method = loopMethod;
                    break;
                }
            }
        }

        return method;
    }

    public static Object getProperty(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = getGetterMethodIfExist(bean.getClass(), name);
        return method == null ? null : method.invoke(bean, EMPTY_OBJECT_ARRAY);
    }

    public static Method getSetterMethod(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return getSetterMethod(bean.getClass(), name);
    }

    public static Method getSetterMethod(Class cl, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = getSetterMethodIfExist(cl, name);
        if (method == null) {
            throw new NoSuchMethodException(name);
        } else {
            return method;
        }
    }

    public static Method getSetterMethodIfExist(Class cl, String name) {
        Method[] list = getMethods(cl);
        String getMethod = "set" + StringUtils.upperCaseFirstChar(name);
        Method method = null;
        Method[] var9 = list;
        int var8 = list.length;

        for (int var7 = 0; var7 < var8; ++var7) {
            Method loopMethod = var9[var7];
            if (loopMethod.getParameterTypes().length == 1) {
                String methodName = loopMethod.getName();
                if (methodName.equals(getMethod)) {
                    method = loopMethod;
                    break;
                }
            }
        }

        return method;
    }

//    private static Object tranfTypeOfParam(Object parameterValue, Class parameterType) {
//        if (parameterValue == null) {
//            return null;
//        } else {
//            if (parameterValue instanceof String) {
//                String valueString = (String) parameterValue;
//                if (valueString.equalsIgnoreCase("null")) {
//                    valueString = "";
//                }
//
//                valueString = StringUtils.rightTrim(valueString);
//                DateTime d;
//                if (parameterType == Timestamp.class) {
//                    d = DataUtils.getDateTime(valueString);
//                    if (d == null) {
//                        parameterValue = null;
//                    } else {
//                        parameterValue = new Timestamp(d.getTime());
//                    }
//                } else if (parameterType == Date.class) {
//                    d = DataUtils.getDateTime(valueString);
//                    if (d == null) {
//                        parameterValue = null;
//                    } else {
//                        parameterValue = d;
//                    }
//                } else if (parameterType == Double.class) {
//                    if (valueString != null && !valueString.equals("")) {
//                        parameterValue = Double.valueOf(DataUtils.correctNumber(valueString));
//                    } else {
//                        parameterValue = null;
//                    }
//                } else if (parameterType == Long.class) {
//                    if (valueString != null && !valueString.equals("")) {
//                        parameterValue = Long.valueOf(DataUtils.correctNumber(valueString));
//                    } else {
//                        parameterValue = null;
//                    }
//                } else if (parameterType == Integer.class) {
//                    if (valueString != null && !valueString.equals("")) {
//                        parameterValue = Integer.valueOf(DataUtils.correctNumber(valueString));
//                    } else {
//                        parameterValue = null;
//                    }
//                }
//            }
//
//            return parameterValue;
//        }
//    }

//    public static void setProperty(Object bean, String name, Object value) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//        Method method = getSetterMethodIfExist(bean.getClass(), name);
//        if (method != null) {
//            value = tranfTypeOfParam(value, method.getParameterTypes()[0]);
//
//            try {
//                method.invoke(bean, value);
//            } catch (IllegalArgumentException var5) {
//                value = tranfTypeOfParam("" + value, method.getParameterTypes()[0]);
//                method.invoke(bean, value);
//            }
//
//        }
//    }

    public static Class getGetterType(Object bean, String name) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Method method = getGetterMethodIfExist(bean.getClass(), name);
        return method == null ? null : method.getReturnType();
    }
}
