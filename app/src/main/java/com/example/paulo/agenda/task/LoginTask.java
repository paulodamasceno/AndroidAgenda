package com.example.paulo.agenda.task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.paulo.agenda.model.User;
import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.Buffer;

/**
 * Created by Paulo on 16/03/2015.
 */
public class LoginTask extends AsyncTask<User, Void, User> {

    private LoginTaskListner listner;

    public  LoginTask(LoginTaskListner listner){
        this.listner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected User doInBackground(User... params) {

        User user = params[0];
        Gson gson = new Gson();
        String json = gson.toJson(user);


        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.0.10:3000/user/authenticate");
        post.addHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(json,"UTF-8"));
            HttpResponse response = httpClient.execute(post);
            if(response.getStatusLine().getStatusCode() == 200){
                InputStream inputStream = response.getEntity().getContent();
                String result = convertInputString(inputStream);
            }
        } catch (UnsupportedEncodingException e) {
            Log.e("LoginTask", e.getMessage(),e);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    private String convertInputString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);

        if(listner != null){
            listner.onResult(user);
        }
    }

    public interface LoginTaskListner {
        public void onResult(User user);
    }
}
