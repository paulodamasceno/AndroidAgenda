package com.example.paulo.agenda.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paulo on 11/03/2015.
 */
public class User {
    @SerializedName("_id")
    private String id;
    private String username;
    private String password;
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
