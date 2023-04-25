package com.projecte.kaiju.helpers;

import android.content.Context;

public class GlobalInfo {
    private String FB_DB = "https://kaiju-f8483-default-rtdb.europe-west1.firebasedatabase.app/";

    private static GlobalInfo instance = new GlobalInfo();

    /*private GlobalInfo(){

    }*/

    public static GlobalInfo getInstance(){
        return instance;
    }

    public String getFB_DB() {
        return FB_DB;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        GlobalInfo.context = context;
    }

    private static Context context = null;

}
