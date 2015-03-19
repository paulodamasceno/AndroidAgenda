package com.example.paulo.agenda.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Paulo on 11/03/2015.
 */
public class DBManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda.db";
    private static final int DATABASE_VERSION = 1;

    private final  String DATABASE_PATH;
    private final String DATABASE_DIRECTORY;
    private static final String TAG = "DBManager";

    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

        DATABASE_DIRECTORY = "/data/data/" + context.getPackageName() + "/databases/";
        DATABASE_PATH = DATABASE_DIRECTORY + DATABASE_NAME;

        boolean dbexist = checkDatabase();

        if(dbexist){
            openDatabase();
        }else {
            createDatabase();
        }
    }

    private void createDatabase() {

        try{
            copyDatabase();
            openDatabase();
        }catch (IOException e){
            Log.e(TAG,e.getMessage(),e);
        }
    }

    private void copyDatabase() throws IOException {
        File f = new File(DATABASE_DIRECTORY);

        if(!f.exists()) {
            f.mkdirs();
            f.createNewFile();


            InputStream myinput = context.getAssets().open("database/"+DATABASE_NAME);
            OutputStream myoutput = new FileOutputStream(DATABASE_PATH);

            byte[] buffer = new byte[1024];
            int length;

            while((length = myinput.read(buffer)) > 0){
                myoutput.write(buffer,0,length);
            }

            myoutput.flush();
            myoutput.close();
            myinput.close();

        }
    }

    private void openDatabase() {
        database = SQLiteDatabase.openDatabase(DATABASE_PATH,null, SQLiteDatabase.OPEN_READWRITE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    private boolean checkDatabase(){
        boolean checkdb = false;
        try {
            File dbfile = new File(DATABASE_PATH);
            checkdb = dbfile.exists();
        }catch (SQLiteException e){
            Log.e(TAG, e.getMessage(), e);
        }

        return checkdb;
    }

    public  void close(){
        if(database != null){
            database.close();
        }
        super.close();
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }
}
