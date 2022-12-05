package com.lkd.note.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.lkd.note.app.App;
import com.lkd.note.model.User;
import com.lkd.note.utils.JSON;
import com.lkd.note.utils.StringUtil;

/**
 * 用户存储的封装采用单例模式避免不必要的损耗
 */
public class DataManager {
    private Context context;
    private DataManager(Context context) {
        this.context = context;
    }

    private static DataManager instance;
    public static DataManager getInstance() {
        if (instance == null) {
            synchronized (DataManager.class) {
                if (instance == null) {
                    instance = new DataManager(App.getInstance());
                }
            }
        }
        return instance;
    }

    //用户 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    private final String PATH_USER = "PATH_USER";


    public final String KEY_CURRENT_USER = "KEY_CURRENT_USER";
    public final String KEY_LAST_USER = "KEY_LAST_USER";
    public final String USER_PASSWORD = "USER_PASSWORD";


    public User getUser(String username){
        SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
        if (sdf == null) {
            return null;
        }
        return JSON.parseObject(sdf.getString(username,null),User.class);
    }


    /**
     * 获取当前用户
     */
    public String getCurrentUserNickname() {
        User user = getCurrentUser();
        return user == null ? "" : user.getNickname();
    }



    /**
     * 获取当前用户的手机号
     */
    public String getCurrentUserPhone() {
        User user = getCurrentUser();
        return user == null ? "" : user.getPhone();
    }
    /**
     * 获取当前用户
     */
    public User getCurrentUser() {
        SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
        return sdf == null ? null : JSON.parseObject(sdf.getString(KEY_CURRENT_USER,null),User.class);
    }

    public void saveUserPass(User user){
        SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
        if (sdf == null) {
            return;
        }
        SharedPreferences.Editor editor = sdf.edit();
        editor.remove(USER_PASSWORD).putString(USER_PASSWORD,JSON.toJSONString(user));
        editor.apply();
    }

    public User getUserPass() {
        SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
        return sdf == null ? null : JSON.parseObject(sdf.getString(USER_PASSWORD,null),User.class);
    }



    /**
     * 保存当前用户,只在登录或注销时调用
     */
    public void saveCurrentUser(User user) {
        SharedPreferences sdf = context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE);
        if (sdf == null) {
            return;
        }
        SharedPreferences.Editor editor = sdf.edit();
        editor.remove(KEY_LAST_USER).putString(KEY_CURRENT_USER,JSON.toJSONString(user));
        editor.apply();
        saveUser(sdf, user);
    }

    /**
     * 保存用户
     */
    public void saveUser(User user) {
        saveUser(context.getSharedPreferences(PATH_USER, Context.MODE_PRIVATE), user);
    }

    public void saveUser(SharedPreferences sdf, User user) {
        if (sdf == null || user == null) {
            return;
        }
        String key = StringUtil.getTrimedString(user.getPhone());
        sdf.edit().remove(key).putString(key, JSON.toJSONString(user)).apply();
    }

    /**
     * 删除用户
     */
    public void removeUser(SharedPreferences sdf, long userId) {
        if (sdf == null) {
            return;
        }
        sdf.edit().remove(StringUtil.getTrimedString(userId)).apply();
    }

    /**
     * 设置当前用户手机号
     */
    public void setCurrentUserPhone(String phone) {
        User user = getCurrentUser();
        if (user == null) {
            user = new User();
        }
        user.setPhone(phone);
        saveUser(user);
    }

}
