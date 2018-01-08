package org.billow.tools;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyongtao
 * @create 2018-01-08 15:28
 */
public final class StringUtils {
    private static final Log logger = LogFactory.getLog(StringUtils.class);
    private static String defaultCharset = System.getProperty("file.encoding");

    private StringUtils() {
    }

    public static String getDefaultCharset() {
        return defaultCharset;
    }

    public static void setDefaultCharset(String defaultCharset) {
        defaultCharset = defaultCharset;
    }

    public static String newString(String value, int length) {
        if (value == null) {
            return null;
        } else {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < length; ++i) {
                buffer.append(value);
            }

            return buffer.toString();
        }
    }

    public static String newString(char inChr, int length) {
        return newString(String.valueOf(inChr), length);
    }

    public static String copyString(String str, int copyTimes) {
        if (str == null) {
            return null;
        } else {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < copyTimes; ++i) {
                buffer.append(str);
            }

            return buffer.toString();
        }
    }

    public static int getBytesLength(String str) {
        return str == null ? -1 : str.getBytes().length;
    }

    public static int indexOf(String str, String subStr, int startIndex, int occurrenceTimes) {
        int foundCount = 0;
        int index = startIndex;
        if (occurrenceTimes <= 0) {
            return -1;
        } else if (str.length() - 1 < startIndex) {
            return -1;
        } else if ("".equals(subStr)) {
            return 0;
        } else {
            int substrLength;
            for (substrLength = subStr.length(); foundCount < occurrenceTimes; index += substrLength) {
                index = str.indexOf(subStr, index);
                if (index == -1) {
                    return -1;
                }

                ++foundCount;
            }

            return index - substrLength;
        }
    }

    public static int indexOf(String str, String subStr, int occurrenceTimes) {
        return indexOf(str, subStr, 0, occurrenceTimes);
    }

    public static int indexOf(String str, String subStr, int fromIndex, boolean caseSensitive) {
        return !caseSensitive ? str.toLowerCase().indexOf(subStr.toLowerCase(), fromIndex) : str.indexOf(subStr, fromIndex);
    }

    public static String replace(String str, String searchStr, String replaceStr, boolean caseSensitive) {
        int i = 0;
        if (str == null) {
            return null;
        } else if ("".equals(str)) {
            return "";
        } else if (searchStr != null && !searchStr.equals("")) {
            String newReplaceStr = replaceStr;
            if (replaceStr == null) {
                newReplaceStr = "";
            }

            StringBuilder buffer;
            int j;
            for (buffer = new StringBuilder(); (j = indexOf(str, searchStr, i, caseSensitive)) > -1; i = j + searchStr.length()) {
                buffer.append(str.substring(i, j));
                buffer.append(newReplaceStr);
            }

            buffer.append(str.substring(i, str.length()));
            return buffer.toString();
        } else {
            return str;
        }
    }

    public static String replace(String str, String searchStr, String replaceStr) {
        return replace(str, searchStr, replaceStr, true);
    }

    public static String replace(String str, char searchChar, String replaceStr) {
        return replace(str, String.valueOf(searchChar), replaceStr, true);
    }

    public static String replace(String str, int beginIndex, String replaceStr) {
        if (str == null) {
            return null;
        } else {
            String newReplaceStr = replaceStr;
            if (replaceStr == null) {
                newReplaceStr = "";
            }

            StringBuilder buffer = new StringBuilder(str.substring(0, beginIndex));
            buffer.append(newReplaceStr);
            buffer.append(str.substring(beginIndex + newReplaceStr.length()));
            return buffer.toString();
        }
    }

    /**
     * @deprecated
     */
    public static String[] split(String originalString, int splitByteLength) {
        return split(originalString, splitByteLength, defaultCharset);
    }

    public static String[] split(String originalString, int splitByteLength, String charsetName) {
        if (originalString == null) {
            return new String[0];
        } else if ("".equals(originalString)) {
            return new String[0];
        } else if (originalString.trim().equals("")) {
            return new String[]{""};
        } else if (splitByteLength <= 1) {
            return new String[]{originalString};
        } else {
            int size = originalString.length();
            ArrayList strList = new ArrayList();

            try {
                int count = 0;
                int start = 0;

                for (int i = 0; i < size; ++i) {
                    int len = String.valueOf(originalString.charAt(i)).getBytes(charsetName).length;
                    count += len;
                    if (count == splitByteLength) {
                        strList.add(originalString.substring(start, i + 1));
                        count = 0;
                        start = i + 1;
                    } else if (count > splitByteLength) {
                        strList.add(originalString.substring(start, i));
                        count = len;
                        start = i;
                    }
                }

                if (start < size) {
                    strList.add(originalString.substring(start));
                }
            } catch (UnsupportedEncodingException var9) {
                throw new IllegalArgumentException(var9.getMessage());
            }

            String[] arrReturn = new String[strList.size()];
            strList.toArray(arrReturn);
            return arrReturn;
        }
    }

    public static String[] split(String originalString, String delimiterString) {
        int index;
        String[] returnArray = null;
        int length = 0;
        if (originalString != null && delimiterString != null && !"".equals(originalString)) {
            if (!"".equals(originalString) && !"".equals(delimiterString) && originalString.length() >= delimiterString.length()) {
                String strTemp;
                for (strTemp = originalString; strTemp != null && !strTemp.equals(""); strTemp = strTemp.substring(index + delimiterString.length())) {
                    index = strTemp.indexOf(delimiterString);
                    if (index == -1) {
                        break;
                    }

                    ++length;
                }

                ++length;
                returnArray = new String[length];
                strTemp = originalString;

                for (int i = 0; i < length - 1; ++i) {
                    index = strTemp.indexOf(delimiterString);
                    returnArray[i] = strTemp.substring(0, index);
                    strTemp = strTemp.substring(index + delimiterString.length());
                }

                returnArray[length - 1] = strTemp;
                return returnArray;
            } else {
                return new String[]{originalString};
            }
        } else {
            return new String[0];
        }
    }

    public static String rightTrim(String str) {
        if (str == null) {
            return "";
        } else {
            int length = str.length();

            for (int i = length - 1; i >= 0 && str.charAt(i) == ' '; --i) {
                --length;
            }

            return str.substring(0, length);
        }
    }

    public static String leftTrim(String str) {
        if (str == null) {
            return "";
        } else {
            int start = 0;
            int i = 0;

            for (int n = str.length(); i < n && str.charAt(i) == ' '; ++i) {
                ++start;
            }

            return str.substring(start);
        }
    }

    public static String absoluteTrim(String str) {
        return replace(str, " ", "");
    }

    public static String lowerCase(String str, int beginIndex, int endIndex) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(str.substring(0, beginIndex));
        buffer.append(str.substring(beginIndex, endIndex).toLowerCase());
        buffer.append(str.substring(endIndex));
        return buffer.toString();
    }

    public static String upperCase(String str, int beginIndex, int endIndex) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(str.substring(0, beginIndex));
        buffer.append(str.substring(beginIndex, endIndex).toUpperCase());
        buffer.append(str.substring(endIndex));
        return buffer.toString();
    }

    public static String lowerCaseFirstChar(String iString) {
        if (iString != null && iString.length() >= 1) {
            return (new StringBuilder(iString.length())).append(iString.substring(0, 1).toLowerCase()).append(iString.substring(1)).toString();
        } else {
            throw new IllegalArgumentException("String must have at least one character.");
        }
    }

    public static String upperCaseFirstChar(String iString) {
        if (iString != null && iString.length() >= 1) {
            return (new StringBuilder(iString.length())).append(iString.substring(0, 1).toUpperCase()).append(iString.substring(1)).toString();
        } else {
            throw new IllegalArgumentException("String must have at least one character.");
        }
    }

    public static int timesOf(String str, String subStr) {
        int foundCount = 0;
        if ("".equals(subStr)) {
            return 0;
        } else {
            for (int fromIndex = str.indexOf(subStr); fromIndex != -1; fromIndex = str.indexOf(subStr, fromIndex + subStr.length())) {
                ++foundCount;
            }

            return foundCount;
        }
    }

    public static int timesOf(String str, char ch) {
        int foundCount = 0;

        for (int fromIndex = str.indexOf(ch); fromIndex != -1; fromIndex = str.indexOf(ch, fromIndex + 1)) {
            ++foundCount;
        }

        return foundCount;
    }

    public static Map<String, String> toMap(String str, String splitString) {
        Map<String, String> map = new HashMap();
        String[] values = split(str, splitString);
        String key = "";
        String value = "";

        for (int i = 0; i < values.length; ++i) {
            String tempValue = values[i];
            int pos = tempValue.indexOf(61);
            key = "";
            value = "";
            if (pos > -1) {
                key = tempValue.substring(0, pos);
                value = tempValue.substring(pos + splitString.length());
            } else {
                key = tempValue;
            }

            map.put(key, value);
        }

        return map;
    }

    public static String native2ascii(String str) {
        char[] ca = str.toCharArray();
        StringBuilder buffer = new StringBuilder(ca.length * 6);

        for (int x = 0; x < ca.length; ++x) {
            char a = ca[x];
            if (a > 255) {
                buffer.append("\\u").append(Integer.toHexString(a));
            } else {
                buffer.append(a);
            }
        }

        return buffer.toString();
    }

    public static String concat(Object... sources) {
        if (sources == null) {
            return "";
        } else if (sources.length == 1) {
            return String.valueOf(sources[0]);
        } else {
            StringBuilder sb = new StringBuilder();
            Object[] var5 = sources;
            int var4 = sources.length;

            for (int var3 = 0; var3 < var4; ++var3) {
                Object o = var5[var3];
                if (o != null) {
                    sb.append(o);
                }
            }

            return sb.toString();
        }
    }
}
