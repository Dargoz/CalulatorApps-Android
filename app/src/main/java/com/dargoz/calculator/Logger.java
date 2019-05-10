package com.dargoz.calculator;

import android.util.Log;

public class Logger {

    public static boolean debuggable = true;

    public static void d(String tag, String data){
        if(debuggable) {
            Log.d(tag, data);
        }
    }

    public static void i(String tag, String data){
        Log.d("DRG","jalan");
        if(debuggable) {
            Log.i(tag, data);
        }
    }

    public static void e(String tag, String data){
        if(debuggable) {
            Log.e(tag, data);
        }
    }

    public static void e(String tag, String data, Throwable t){
        if(debuggable) {
            Log.e(tag, data, t);
        }
    }

    public static void w(String tag, String data){
        if(debuggable) {
            Log.w(tag, data);
        }
    }

    public static void wtf(String tag, String data){
        if(debuggable) {
            Log.wtf(tag, data);
        }
    }

    public static void isDebugEnabled(boolean flag) {
        debuggable = flag;
    }
}
