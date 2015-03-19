package com.example.paulo.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.paulo.agenda.model.Contato;
import com.example.paulo.agenda.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 11/03/2015.
 */

public class contactDAO {

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String ADDRESS = "address";
    private final static String PHONE = "phone";
    private final static String CELLPHONE = "cellphone";
    private final static String EMAIL = "email";
    private final static String PHOTO = "photo";
    private final static String LATITUDE = "latitude";
    private final static String LONGITUDE = "longitude";

    private final static String TABLE = "contact";

    private Context context;
    public DBManager dbManager;

    public contactDAO(Context context) {
        this.context = context;
        dbManager = new DBManager(context);
    }

    public boolean insert(Contato contato) {
        SQLiteDatabase database = dbManager.getDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contato.getName());
        contentValues.put(ADDRESS, contato.getAddress());
        contentValues.put(PHONE, contato.getPhone());
        contentValues.put(CELLPHONE, contato.getCellphone());
        contentValues.put(EMAIL, contato.getEmail());
        contentValues.put(PHOTO, contato.getPhoto());
        contentValues.put(LATITUDE, contato.getLatitude());
        contentValues.put(LONGITUDE, contato.getLongitude());

        long success = database.insert(TABLE, null, contentValues);

        if (success == -1) {
            return false;
        }
        return true;
    }

    public List<Contato> getContatos() {


        Contato contato = null;
        List<Contato> contatos = new ArrayList<Contato>();

        SQLiteDatabase database = dbManager.getDatabase();

        String orderBy = "name ASC";

        Cursor cursor = database.query(TABLE, null, null, null, null, null, orderBy);

        if (cursor.getCount() == 0) {
            return null;
        }

        while (cursor.moveToNext()) {
            contato = new Contato();

            contato.setId(getCursorTable(ID, cursor));
            contato.setName(getCursorTable(NAME, cursor));
            contato.setAddress(getCursorTable(ADDRESS, cursor));
            contato.setPhone(getCursorTable(PHONE, cursor));
            contato.setCellphone(getCursorTable(CELLPHONE, cursor));
            contato.setEmail(getCursorTable(EMAIL, cursor));
            contato.setPhoto(getCursorTable(PHOTO, cursor));
            contato.setLatitude(Integer.parseInt(getCursorTable(LATITUDE, cursor)));
            contato.setLongitude(Integer.parseInt(getCursorTable(LONGITUDE, cursor)));
            contatos.add(contato);
        }
        return contatos;
    }

    public Contato getContatoEspecifico(String id) {

        Contato contato = null;
        SQLiteDatabase database = dbManager.getDatabase();

        String where = "id = ?";
        String[] args = {id};

        Cursor cursor = database.query(TABLE, null, where, args, null, null, null);

        if (cursor.getCount() == 0) {
            return null;
        }

        while (cursor.moveToNext()) {
            contato = new Contato();
            contato.setId(getCursorTable(ID, cursor));
            contato.setName(getCursorTable(NAME, cursor));
            contato.setAddress(getCursorTable(ADDRESS, cursor));
            contato.setPhone(getCursorTable(PHONE, cursor));
            contato.setCellphone(getCursorTable(CELLPHONE, cursor));
            contato.setEmail(getCursorTable(EMAIL, cursor));
            contato.setPhoto(getCursorTable(PHOTO, cursor));
            contato.setLatitude(Integer.parseInt(getCursorTable(LATITUDE, cursor)));

        }

        return contato;
    }


    public Boolean updateContato(Contato contato) {
        SQLiteDatabase database = dbManager.getDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, contato.getName());
        contentValues.put(ADDRESS, contato.getAddress());
        contentValues.put(PHONE, contato.getPhone());
        contentValues.put(CELLPHONE, contato.getCellphone());
        contentValues.put(EMAIL, contato.getEmail());
        contentValues.put(PHOTO, contato.getPhoto());
        contentValues.put(LATITUDE, contato.getLatitude());
        contentValues.put(LONGITUDE, contato.getLongitude());

        String where = "id = ?";
        String[] args = {String.valueOf(contato.getId())};

        long success = database.update(TABLE, contentValues, where, args);

        if (success == -1) {
            return false;
        }
        return true;
    }


    public Boolean delete(int id){

        String where = "id = ?";
        String[] args = {String.valueOf(id)};

        SQLiteDatabase database = dbManager.getDatabase();
        long success = database.delete(TABLE,where, args);

        if (success == -1) {
            return false;
        }

        return  true;
    }

    public String getCursorTable(String nameColumn, Cursor cursor) {
        String valor = cursor.getString(cursor.getColumnIndex(nameColumn));
        return valor;
    }


}
