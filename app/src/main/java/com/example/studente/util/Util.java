package com.example.studente.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.studente.R;

public class Util  {

    public static GetValue loadSetting(Context context,String fileName,String key1,String key2){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,0);
        String value1 = sharedPreferences.getString(key1,"");
        String value2 = sharedPreferences.getString(key2,"");
        GetValue value = new GetValue();
        value.setValue1(value1);
        value.setValue2(value2);
        return value;
    }
    public static void setStting(Context context,String fileName,String key1,String value1,String key2,String value2){
        SharedPreferences sharedPreferences = context.getSharedPreferences(fileName,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key1,value1);
        editor.putString(key2,value2);
        editor.commit();
    }
}
