package com.example.paulo.agenda;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.List;

/**
 * Created by Paulo on 18/03/2015.
 */
public class GeocoderTask extends AsyncTask<Location,Void,List<Address>> {

    private Context context;
    private GeocoderTaskListner listner;

    public GeocoderTask(Context context, GeocoderTaskListner listner) {
        this.context = context;
        this.listner = listner;
    }

    @Override
    protected List<Address> doInBackground(Location... params) {
        Location location = params[0];

        Geocoder geocoder = new Geocoder(context);
        try {
            List<Address> result  =  geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            return result;
        } catch (IOException e) {
            Log.e("GEOCODER", e.getMessage(), e);
        }


        return null;
    }

    @Override
    protected void onPostExecute(List<Address> addresses) {
        super.onPostExecute(addresses);
        if(listner != null){
            listner.onAddressResult(addresses);
        }
    }

    public interface GeocoderTaskListner{
        public void onAddressResult(List<Address>result);
    }
}
