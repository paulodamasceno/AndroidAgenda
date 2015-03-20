package com.example.paulo.agenda;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

public class AgendaService extends Service {

    private final static String TAG = "AgendaService";
    public final static String ACTION_UPDATE = "action_update";

    public AgendaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");
        String action = intent.getAction();
        if(!TextUtils.isEmpty(action)){
            if(action.equalsIgnoreCase(ACTION_UPDATE)){
                //TODO: Mandamos executar um update;
                //stopSelf(); parar execução do serviço
                stopSelf();
            }
        }


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
