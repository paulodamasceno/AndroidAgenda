package com.example.paulo.agenda;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.paulo.agenda.model.User;
import com.google.gson.Gson;

/**
 * Created by Paulo on 12/03/2015.
 */
public class Helper {

    public static void saveUserPreference(Context context, User user){
        SharedPreferences sharedPreferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString("user",json);

        editor.commit();
    }


    public static User getUserPreference(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("login",Context.MODE_PRIVATE);

        String json = sharedPreferences.getString("user", null);

        if(TextUtils.isEmpty(json)){
            return null;
        }

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);

        return user;
    }


}
