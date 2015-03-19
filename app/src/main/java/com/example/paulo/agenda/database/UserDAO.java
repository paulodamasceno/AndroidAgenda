package com.example.paulo.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.paulo.agenda.model.User;

/**
 * Created by Paulo on 11/03/2015.
 */
public class UserDAO {

    private final static String ID = "id";
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
    private final static String NICK = "nick";
    private final static String TABLE = "user";

    private Context context;
    public DBManager dbManager;

    public UserDAO(Context context) {
        this.context = context;
        dbManager = new DBManager(context);
    }

    public boolean insert(User user){
        SQLiteDatabase database = dbManager.getDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, user.getUsername());
        contentValues.put(PASSWORD, user.getPassword());
        contentValues.put(NICK, user.getNick());

        long success = database.insert(TABLE,null,contentValues);

        if(success == -1){
            return false;
        }
        return true;
    }

    public User getUser(String username, String password){


        User user = null;
        SQLiteDatabase database = dbManager.getDatabase();

        String where = " username = ? and password = ?";
        String[] params = {username, password};

        Cursor cursor = database.query(TABLE,null, where, params,null, null,null);

        while(cursor.moveToNext()){
            user = new User();

            /*user.setId(cursor.getInt(cursor.getColumnIndex(ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
            user.setNick(cursor.getString(cursor.getColumnIndex(NICK)));*/


            user.setId(getCursorTable(ID,cursor));
            user.setUsername(getCursorTable(USERNAME, cursor));
            user.setPassword(getCursorTable(PASSWORD, cursor));
            user.setNick(getCursorTable(NICK, cursor));
        }
        return user;
    }

    public String getCursorTable(String nameColumn, Cursor cursor){
        String valor = cursor.getString(cursor.getColumnIndex(nameColumn));
        return valor;
    }

}
