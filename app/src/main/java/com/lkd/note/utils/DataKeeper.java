/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.lkd.note.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 数据存储工具类
 */
public class DataKeeper {


	private DataKeeper() {}

	private static Context context;
	//获取context，获取存档数据库引用
	public static void init(Context context_) {
		context = context_;
		//判断SD卡存在


	}
	



	/**使用SharedPreferences保存
	 */
	public static void save(SharedPreferences sdf, String key, String value) {
		if (sdf == null || !StringUtil.isNotEmpty(key, false) || !StringUtil.isNotEmpty(value, false)) {
			return;
		}
		sdf.edit().remove(key).putString(key, value).apply();
	}

	public static String get(String prefsName,String key){
		return  context.getSharedPreferences(prefsName,Context.MODE_PRIVATE).getString(key,null);
	}

}