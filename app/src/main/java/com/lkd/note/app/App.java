package com.lkd.note.app;

import android.app.Application;

import com.lkd.note.utils.DataKeeper;


/**
 * 应用入口
 */
public class App extends Application {

    public App() {
    }

    private static Application instance;
    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    public static void init(Application application) {
        instance = application;
        DataKeeper.init(instance);
    }
}
