package com.example.android.criw;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

public class UpdateWidgetService extends Service {
    String[] abc = null;
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        buildUpdate();
        Toast.makeText(this.getApplicationContext(), "widget Added 6T", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
//        return START_STICKY (Should be used)
    }

    private void buildUpdate()
    {


        DownloadContent dc = new DownloadContent();
//        dc.execute();

//        dc.execute().get();
        try {
            abc = dc.execute().get();

        }catch (Exception e)
        {

            Log.e("LOG TAG>>>>>>>>>1", e.toString());
        }


        if(abc != null){

            int i = (new Random().nextInt(abc.length));
            Log.w("LOG TAG LENGTH>>>>>>>>>1",String.valueOf(abc.length));
            Log.w("LOG TAG>>>>>>>>>1",abc[i]);

        }
        else
            Log.w("LOG TAG>>>>>>>>>1","EMPTY");


        //String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa", new java.util.Date()).toString();

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.ccapp_widget);
        if(abc!= null)
            {
                int i = (new Random().nextInt(abc.length));
                view.setTextViewText(R.id.appwidget_text, abc[i]+String.valueOf(i));
            }
        else
                view.setTextViewText(R.id.appwidget_text, "No Data");

        // Push update for this widget to the home screen
        ComponentName thisWidget = new ComponentName(this, CCAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(thisWidget, view);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
