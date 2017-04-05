package org.billow.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据操作工具类
 * 
 * @author liuyongtao
 * 
 * @date 2016年12月2日 下午2:45:41
 */
public class ToolsUtils {

	/**
	 * 从一个List中获取指定属性的值
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param list
	 *            数据集
	 * @param field
	 *            指定字段
	 * @return 指定字段的结果
	 * 
	 * @date 2016年12月2日 下午2:45:37
	 */
	@SuppressWarnings("unchecked")
	public static <T, E> List<T> getListByFieldValue(List<E> list, String field) {
		List<T> fields = new ArrayList<>();
		for (E e : list) {
			Class<? extends Object> clazz = e.getClass();
			try {
				Field f = clazz.getDeclaredField(field);
				// 设置些属性是可以访问的
				f.setAccessible(true);
				T object = (T) f.get(e);
				fields.add(object);
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		return fields;
	}

	/**
	 * 去掉右边的空白
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param str
	 * @return
	 * 
	 * @date 2016年11月25日 下午5:35:34
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != ' ') {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(List list) {
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String[] items) {
		if (items != null && items.length > 0) {
			return true;
		}
		return false;
	}
	//
	// public static void main(String[] args) {
	// User u1 = new User();
	// u1.setAge(12);
	// User u2 = new User();
	// u2.setAge(13);
	// User u3 = new User();
	// u3.setAge(14);
	// List<User> users = new ArrayList<>();
	// users.add(u1);
	// users.add(u2);
	// users.add(u3);
	//
	// List<Integer> userIds = ToolsUtils.getListByFieldValue(users, "age");
	// Integer[] articleIds = new Integer[userIds.size()];
	// userIds.toArray(articleIds);
	// System.out.println(Arrays.toString(articleIds));
	// String join = StringUtils.join(userIds, ",");
	// System.out.println(join);
	// }
}
