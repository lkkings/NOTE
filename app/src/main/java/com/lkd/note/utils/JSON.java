package com.lkd.note.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * FastJSON封装类 防止解析时异常
 */
public class JSON {
	private static final String TAG = "JSON";

	/**
	 * 判断json格式是否正确
	 */
	public static boolean isJsonCorrect(String s) {
//		LLog.i(TAG, "isJsonCorrect  <<<<     " + s + "     >>>>>>>");
		if (s == null || s.equals("[]") 
				|| s.equals("{}") || s.equals("") || s.equals("[null]") || s.equals("{null}") || s.equals("null")) {
			return false;
		}
		return true;
	}

	/**
	 * 获取有效的json
	 */
	public static String getCorrectJson(String json) {
		return isJsonCorrect(json) ? json : "";
	}
	

	public static JSONObject parseObject(Object obj) {
		return parseObject(toJSONString(obj));
	}
	/**json转JSONObject
	 * @param json
	 * @return
	 */
	public static JSONObject parseObject(String json) {
		try {
			return com.alibaba.fastjson.JSON.parseObject(getCorrectJson(json));
		} catch (Exception e) {
		}
		return null;
	}

	/**JSONObject转实体类
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(JSONObject object, Class<T> clazz) {
		return parseObject(toJSONString(object), clazz);
	}
	/**json转实体类
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseObject(String json, Class<T> clazz) {
		try {
			return com.alibaba.fastjson.JSON.parseObject(getCorrectJson(json), clazz);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static String toJSONString(Object obj) {
		if (obj instanceof String) {
			return (String) obj;
		}
		try {
			return com.alibaba.fastjson.JSON.toJSONString(obj);
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * @param json
	 * @return
	 */
	public static JSONArray parseArray(String json) {
		try {
			return com.alibaba.fastjson.JSON.parseArray(getCorrectJson(json));
		} catch (Exception e) {

		}
		return null;
	}
	/**
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> parseArray(String json, Class<T> clazz) {
		try {
			return com.alibaba.fastjson.JSON.parseArray(getCorrectJson(json), clazz);
		} catch (Exception e) {

		}
		return null;
	}

	/**格式化，显示更好看
	 * @param object
	 * @return
	 */
	public static String format(Object object) {
		try {
			return com.alibaba.fastjson.JSON.toJSONString(object, true);
		} catch (Exception e) {

		}
		return null;
	}

	/**判断是否为JSONObject
	 * @param obj instanceof String ? parseObject
	 * @return
	 */
	public static boolean isJSONObject(Object obj) {
		if (obj instanceof JSONObject) {
			return true;
		}
		if (obj instanceof String) {
			try {
				JSONObject json = parseObject((String) obj);
				return json != null && json.isEmpty() == false;
			} catch (Exception e) {

			}
		}
		
		return false;
	}
	/**判断是否为JSONArray
	 * @param obj instanceof String ? parseArray
	 * @return
	 */
	public static boolean isJSONArray(Object obj) {
		if (obj instanceof JSONArray) {
			return true;
		}
		if (obj instanceof String) {
			try {
				JSONArray json = parseArray((String) obj);
				return json != null && json.isEmpty() == false;
			} catch (Exception e) {

			}
		}
		
		return false;
	}

}
