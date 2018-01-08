package org.billow.tools;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.billow.business.model.AirLine;
import org.billow.business.model.extended.Param;
import org.hibernate.type.StandardBasicTypes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HQL工具类
 *
 * @author liuyongtao
 * @create 2018-01-08 15:11
 */
public class HQLTools {

    private static final Log logger = LogFactory.getLog(HQLTools.class);
    private static final String[] FORBID_STRING = new String[]{" or "};
    public static final String[] OPERATE_SIGN = new String[]{">=", "<=", "!=", "=", ">", "<"};
    private static final String[] EXCEPT_KEY = new String[]{"insert ", "update ", "delete ", "select ", "create ", "drop ", "alter ", "truncate ", "use "};
    private static int dbType = 1;
    private static String oracleDateFormat = "YYYY-MM-DD HH24:MI:SS";
    private static DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
    private static Map<String, String> ignoreNameMap = new HashMap();

    private HQLTools() {
    }

    public static void addIgnoreName(String name) {
        int pos = name.lastIndexOf(46);
        String key;
        if (pos > -1) {
            key = name.substring(pos + 1);
        } else {
            key = name;
        }

        key = key.toLowerCase();
        ignoreNameMap.put(key, "");
    }

    public static boolean isIgnoreName(String name) {
        int pos = name.lastIndexOf(46);
        String key;
        if (pos > -1) {
            key = name.substring(pos + 1);
        } else {
            key = name;
        }

        key = key.toLowerCase();
        return ignoreNameMap.containsKey(key);
    }


    public static String addPreparedConditions(Object object, String[] propertyArray, List<Param> params) {
        return addPreparedConditions(null, object, propertyArray, params);
    }

    public static String addPreparedConditions(String alias, Object object, String[] propertyArray, List<Param> params) {
        if (propertyArray == null) {
            return "";
        } else {
            StringBuilder buffer = new StringBuilder(100);

            for (int i = 0; i < propertyArray.length; ++i) {
                buffer.append(addPreparedCondition(alias, object, propertyArray[i], params));
            }

            return buffer.toString();
        }
    }

    public static String addPreparedConditions(Object object, List<Param> params) {
        return addPreparedConditions(null, object, params);
    }

    public static String addPreparedConditions(String alias, Object object, List<Param> params) {
        if (object == null) {
            return "";
        } else {
            StringBuilder buffer = new StringBuilder(100);
            Class cl = object.getClass();
            if (cl != null && cl.getSuperclass() != null) {
                String className = ObjectUtils.getClassNameWithoutPackage(cl);
                String superName = ObjectUtils.getClassNameWithoutPackage(cl.getSuperclass());
                if ((className + "Base").equals(superName)) {
                    cl = cl.getSuperclass();
                }
            }

            List methodList = ObjectUtils.getGetter(cl);

            for (int i = 0; i < methodList.size(); ++i) {
                Method method = (Method) methodList.get(i);
                String fieldName = method.getName().substring(3);
                if (fieldName.equals("Id")) {//主键拼接
                    Object o = null;
                    try {
                        o = method.invoke(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    Class<?> pkClass = o.getClass();
                    List pkMethodList = ObjectUtils.getGetter(pkClass);
                    for (int j = 0; j < pkMethodList.size(); ++j) {
                        Method pkMethod = (Method) pkMethodList.get(j);
                        String pkFieldName = pkMethod.getName().substring(3);
                        pkFieldName = StringUtils.lowerCaseFirstChar(pkFieldName);
                        String sql = addPreparedCondition(alias, o, pkFieldName, params);
                        buffer.append(sql);
                    }
                } else if (!fieldName.endsWith("_ForQuery") && !fieldName.startsWith("Object_")) {
                    fieldName = StringUtils.lowerCaseFirstChar(fieldName);
                    String sql = addPreparedCondition(alias, object, fieldName, params);
                    buffer.append(sql);
                }
            }

            return buffer.toString();
        }
    }

    public static String addPreparedCondition(Object object, String propertyName, List<Param> params) {
        return addPreparedCondition(null, object, propertyName, params);
    }

    private static String addPreparedCondition(String alias, Object object, String pName, List<Param> params) {
        StringBuilder buffer = new StringBuilder();
        String value = "";
        Class type = null;
        StringBuilder buf = new StringBuilder(pName.length());
        Object obj = null;

        Method method;
        try {
            buf.append(pName.substring(0, 1).toUpperCase());
            buf.append(pName.substring(1));
            method = ObjectUtils.getGetterMethodIfExist(object.getClass(), buf.toString());
            if (method != null) {
                type = method.getReturnType();
                obj = method.invoke(object, ObjectUtils.EMPTY_OBJECT_ARRAY);
                if (obj == null) {
                    value = null;
                } else {
                    value = String.valueOf(obj);
                }
            }
        } catch (IllegalAccessException var14) {
            throw new IllegalArgumentException("Illegal access exception");
        } catch (InvocationTargetException var15) {
            throw new IllegalArgumentException("Invocation target exception");
        }

        if (value == null && type != null && type != String.class) {
            try {
                buf.append("_ForQuery");
                method = ObjectUtils.getGetterMethodIfExist(object.getClass(), buf.toString());
                if (method != null) {
                    obj = method.invoke(object, ObjectUtils.EMPTY_OBJECT_ARRAY);
                    if (obj == null) {
                        value = null;
                    } else {
                        value = String.valueOf(obj);
                    }
                }
            } catch (IllegalAccessException var12) {
                throw new IllegalArgumentException("Illegal access exception");
            } catch (InvocationTargetException var13) {
                throw new IllegalArgumentException("Illegal access exception");
            }
        }

        if (value != null && value.length() > 0) {
            value = StringUtils.replace(value, '*', "%");
            value = StringUtils.replace(value, "%%", "%");
            value = StringUtils.replace(value, "??", "?");
            String fieldName = "";
            if (alias != null && alias.trim().length() > 0) {
                fieldName = alias + '.' + pName;
            } else {
                fieldName = pName;
            }

            if (type != Date.class && type != Timestamp.class && type != java.sql.Date.class) {
                if (type == String.class) {
                    buffer.append(convertPreparedString(fieldName, value, params));
                } else {
                    buffer.append(convertPreparedNumber(fieldName, value, params));
                }
            } else {
                try {
                    value = dateFormate.format(obj);
                } catch (IllegalArgumentException var11) {
                    ;
                }

                buffer.append(convertPreparedDate(fieldName, value, params));
            }
        }

        return buffer.toString();
    }

    public static String getWherePartForGetCount(String wherePart) {
        String sqlForGetCount = wherePart;
        int pos = StringUtils.indexOf(wherePart.toLowerCase(), " order by ", 0, false);
        if (pos > 0) {
            String orderbyStr = wherePart.substring(pos);
            sqlForGetCount = wherePart.substring(0, pos);
            int rightCount = StringUtils.timesOf(orderbyStr, ')');
            if (rightCount > 0) {
                int leftCount = StringUtils.timesOf(orderbyStr, '(');
                if (rightCount > leftCount) {
                    sqlForGetCount = sqlForGetCount + StringUtils.newString(')', rightCount - leftCount);
                }
            }
        }

        return sqlForGetCount;
    }

    public static String convertPreparedString(String name, String value, List<Param> params) {
        checkParamValue(value);
        if (value != null && !value.equals("")) {
            StringBuilder buffer = new StringBuilder();
            String retValue = StringUtils.replace(value, "*", "%");
            retValue = StringUtils.replace(retValue, "%%", "%");
            retValue = StringUtils.replace(retValue, "??", "?");
            retValue = StringUtils.replace(retValue, "'", "''");
            int index;
            if (!isIgnoreName(name) && retValue.indexOf(44) > -1) {
                String[] values = StringUtils.split(retValue, ",");
                buffer.append(" AND (").append(name).append(" IN (");
                int i = 0;

                for (index = values.length; i < index; ++i) {
                    buffer.append("?");
                    if (i < index - 1) {
                        buffer.append(',');
                    }

                    params.add(new Param(values[i], StandardBasicTypes.STRING));
                }

                buffer.append(")) ");
            } else if (retValue.indexOf(63) <= -1 && retValue.indexOf(37) <= -1) {
                String valueStart = "";
                String valueEnd = "";
                index = retValue.indexOf(58);
                if (!isIgnoreName(name) && index > -1) {
                    valueStart = retValue.substring(0, index).trim();
                    valueEnd = retValue.substring(index + 1).trim();
                    buffer.append(" AND (").append(name).append(" BETWEEN ").append("?").append(" AND ").append("?").append(") ");
                    params.add(new Param(valueStart, StandardBasicTypes.STRING));
                    params.add(new Param(valueEnd, StandardBasicTypes.STRING));
                } else if (hasOperateSign(retValue)) {
                    String[] operateSignAndTrueValue = splitOperateSignAndTrueValue(retValue);
                    String operateSign = operateSignAndTrueValue[0];
                    String trueValue = operateSignAndTrueValue[1];
                    buffer.append(" AND (").append(name).append(" ").append(operateSign).append(" ? ").append(") ");
                    params.add(new Param(trueValue, StandardBasicTypes.STRING));
                } else {
                    buffer.append(" AND (").append(name).append(" = ").append("?").append(") ");
                    params.add(new Param(retValue, StandardBasicTypes.STRING));
                }
            } else {
                buffer.append(" AND (").append(name).append(" LIKE ").append("?").append(") ");
                params.add(new Param(retValue, StandardBasicTypes.STRING));
            }

            return buffer.toString();
        } else {
            return "";
        }
    }


    public static String convertPreparedDate(String name, String value, List<Param> params) {
        checkParamValue(value);
        StringBuilder buffer = new StringBuilder();
        if (value != null && !value.equals("")) {
            int n;
            if (!isIgnoreName(name) && value.indexOf(44) > -1) {
                String[] values = StringUtils.split(value, ",");
                if (dbType == 2) {
                    buffer.append(" AND (date(").append(name).append(") IN (");
                } else {
                    buffer.append(" AND (").append(name).append(" IN (");
                }

                int i = 0;

                for (n = values.length; i < n; ++i) {
                    String dateValue = values[i].trim();
                    dateValue = StringUtils.replace(dateValue, "'", "");
                    dateValue = dateValue.trim();
                    if (dbType == 1) {
                        dateValue = StringUtils.replace(dateValue, "/", "-");
                        buffer.append("to_date('").append("?").append("', '").append(oracleDateFormat).append("')");
                    } else if (dbType == 2) {
                        buffer.append("date('").append("?").append('\'');
                    } else {
                        buffer.append('\'').append("?").append('\'');
                    }

                    if (i < n - 1) {
                        buffer.append(',');
                    }

                    params.add(new Param(dateValue, StandardBasicTypes.DATE));
                }

                buffer.append(")) ");
            } else {
                String valueStart = "";
                String valueEnd = "";
                String retValue = StringUtils.replace(value.trim(), "'", "");
                retValue = retValue.trim();
                n = StringUtils.timesOf(retValue, ':');
                boolean isBetween = false;
                if (!isIgnoreName(name) && n > 0 && n % 2 == 1) {
                    isBetween = true;
                    int index = StringUtils.indexOf(retValue, ":", 0, n / 2 + 1);
                    valueStart = retValue.substring(0, index);
                    valueEnd = retValue.substring(index + 1);
                    if (n == 1 && valueStart.length() < 3) {
                        isBetween = false;
                    }
                }

                if (isBetween) {
                    if (dbType == 1) {
                        buffer.append(" AND (").append(name).append(" BETWEEN to_date(").append("?").append(", '").append(oracleDateFormat).append("') AND to_date(").append("?").append(", '").append(oracleDateFormat).append("')) ");
                        params.add(new Param(valueStart, StandardBasicTypes.STRING));
                        params.add(new Param(valueEnd, StandardBasicTypes.STRING));
                    } else if (dbType == 2) {
                        buffer.append(" AND ( date(").append(name).append(") BETWEEN date(").append("?").append(") AND date(").append("?").append(")) ");
                        params.add(new Param(valueStart, StandardBasicTypes.STRING));
                        params.add(new Param(valueEnd, StandardBasicTypes.STRING));
                    } else {
                        buffer.append(" AND (").append(name).append(" BETWEEN ").append("?").append(" AND ").append("?").append(") ");
                        params.add(new Param(valueStart, StandardBasicTypes.STRING));
                        params.add(new Param(valueEnd, StandardBasicTypes.STRING));
                    }
                } else if (hasOperateSign(retValue)) {
                    String[] operateSignAndTrueValue = splitOperateSignAndTrueValue(retValue);
                    String operateSign = operateSignAndTrueValue[0];
                    String trueValue = operateSignAndTrueValue[1];
                    if (dbType == 1) {
                        buffer.append(" AND (").append(name).append(operateSign).append("to_date(").append("?").append(", '").append(oracleDateFormat).append("')) ");
                    } else if (dbType == 2) {
                        buffer.append(" AND ( date(").append(name).append(')').append(operateSign).append("date(").append("?").append(")) ");
                    } else {
                        buffer.append(" AND (").append(name).append(operateSign).append(" ").append("?").append(") ");
                    }

                    params.add(new Param(trueValue, StandardBasicTypes.STRING));
                } else if (dbType == 1) {
                    buffer.append(" AND (").append(name).append(">=to_date(").append("?").append(", '").append(oracleDateFormat).append("') AND ").append(name).append("<=to_date(").append("?").append(", '").append(oracleDateFormat).append("')) ");
                    params.add(new Param((new DateTime(retValue)).toString(13) + " 00:00:00", StandardBasicTypes.STRING));
                    params.add(new Param((new DateTime(retValue)).toString(13) + " 23:59:59", StandardBasicTypes.STRING));
                } else if (dbType == 2) {
                    buffer.append(" AND (date(").append(name).append(") = date(").append("?").append(")) ");
                    params.add(new Param(retValue, StandardBasicTypes.STRING));
                } else {
                    buffer.append(" AND (").append(name).append(" = ").append(retValue).append(") ");
                    params.add(new Param(retValue, StandardBasicTypes.STRING));
                }
            }

            return buffer.toString();
        } else {
            return "";
        }
    }


    public static String convertPreparedNumber(String name, String value, List<Param> params) {
        StringBuilder strReturn = new StringBuilder();
        checkParamValue(value);
        if (value != null && !value.equals("")) {
            int n;
            if (!isIgnoreName(name) && value.indexOf(44) > -1) {
                String[] values = StringUtils.split(value, ",");
                strReturn.append(" AND (").append(name).append(" IN (");
                int i = 0;

                for (n = values.length; i < n; ++i) {
                    strReturn.append('\'').append("?").append('\'');
                    if (i < n - 1) {
                        strReturn.append(',');
                    }

                    params.add(new Param(new BigDecimal(values[i]), StandardBasicTypes.BIG_DECIMAL));
                }

                strReturn.append(")) ");
            } else {
                String valueStart = "";
                String valueEnd = "";
                n = value.indexOf(58);
                if (!isIgnoreName(name) && n > -1) {
                    valueStart = value.substring(0, n);
                    valueEnd = value.substring(n + 1);
                    strReturn.append(" AND (").append(name).append(" BETWEEN ").append("?").append(" AND ").append("?").append(") ");
                    params.add(new Param(new BigDecimal(valueStart), StandardBasicTypes.BIG_DECIMAL));
                    params.add(new Param(new BigDecimal(valueEnd), StandardBasicTypes.BIG_DECIMAL));
                } else if (hasOperateSign(value)) {
                    String regEx = "[-+]?[0-9]*\\.?[0-9]+";
                    Pattern p = Pattern.compile(regEx);
                    Matcher m = p.matcher(value);
                    String operate = m.replaceAll("");
                    strReturn.append(" AND (").append(name + " ").append(operate).append(" ? ").append(") ");
                    params.add(new Param(new BigDecimal(value.replace(operate, "")), StandardBasicTypes.BIG_DECIMAL));
                } else {
                    strReturn.append(" AND (").append(name).append(" = ").append("?").append(") ");
                    if (!"true".equalsIgnoreCase(value) && !"false".equalsIgnoreCase(value)) {
                        params.add(new Param(new BigDecimal(value), StandardBasicTypes.BIG_DECIMAL));
                    } else {
                        params.add(new Param(value, StandardBasicTypes.STRING));
                    }
                }
            }

            return strReturn.toString();
        } else {
            return "";
        }
    }

    private static void checkParamValue(String value) {
        if (value != null && !value.equals("")) {
            String retValue = value.toLowerCase();

            for (int i = 0; i < FORBID_STRING.length; ++i) {
                if (retValue.indexOf(FORBID_STRING[i]) != -1) {
                    throw new IllegalArgumentException("Illegal query string " + FORBID_STRING[i]);
                }
            }

        }
    }

    private static boolean hasOperateSign(String value) {
        boolean result = false;
        String newValue = StringUtils.absoluteTrim(value);

        for (int i = 0; i < OPERATE_SIGN.length; ++i) {
            if (newValue.indexOf(OPERATE_SIGN[i]) != -1) {
                result = true;
                break;
            }
        }

        return result;
    }

    private static String[] splitOperateSignAndTrueValue(String value) {
        String newValue = StringUtils.leftTrim(value);
        newValue = StringUtils.rightTrim(newValue);
        String operateSign;
        String trueValue;
        if (newValue.length() >= 2) {
            if (hasOperateSign(newValue.substring(1, 2))) {
                operateSign = newValue.substring(0, 2);
                trueValue = newValue.substring(2);
            } else {
                operateSign = newValue.substring(0, 1);
                trueValue = newValue.substring(1);
            }
        } else {
            operateSign = newValue;
            trueValue = "";
        }

        return new String[]{operateSign, trueValue};
    }

    public static void validateSql(String sql) {
        if (sql != null) {
            if (sql.indexOf(59) != -1) {
                String[] tempSqls = StringUtils.split(sql, ";");
                boolean multiSql = false;

                for (int i = 1; i < tempSqls.length; ++i) {
                    String tempSql = tempSqls[i].trim().toLowerCase();

                    for (int j = 0; j < EXCEPT_KEY.length; ++j) {
                        if (tempSql.startsWith(EXCEPT_KEY[j])) {
                            multiSql = true;
                            logger.warn("Mabye SQL Injection. The sql is [" + sql + "]");
                            break;
                        }
                    }
                }

                if (multiSql) {
                    throw new IllegalArgumentException("SQL can't include semicolon.");
                }
            }

        }
    }

    public static void main(String[] args) {
        List<Param> params = new ArrayList<>();
        AirLine airLine = new AirLine();
        airLine.setName("2222");
        String conditions = HQLTools.addPreparedConditions(null, airLine, params);
        System.out.println(conditions);
        System.out.println(params.get(0).getType().getName());
        System.out.println(params.get(0).getValue());
    }
}
