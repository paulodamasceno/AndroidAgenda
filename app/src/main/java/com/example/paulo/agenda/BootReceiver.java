package com.example.paulo.agenda;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
    public BootReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Atualizando Agenda",Toast.LENGTH_LONG).show();
        Intent intentOpen = new Intent("com.example.paulo.agenda.action.ACTION_NEW");
        intentOpen.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intentOpen);
    }
}
