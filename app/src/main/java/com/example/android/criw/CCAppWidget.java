package com.example.android.criw;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;


/**
 * Implementation of App Widget functionality.
 */
public class CCAppWidget extends AppWidgetProvider {

    private PendingIntent service = null;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        final Calendar TIME = Calendar.getInstance();
        TIME.set(Calendar.MINUTE, 0);
        TIME.set(Calendar.SECOND, 0);
        TIME.set(Calendar.MILLISECOND, 0);

        final Intent i = new Intent(context, com.example.android.criw.UpdateWidgetService.class);

        if (service == null)
        {
            service = PendingIntent.getService(context, 0, i, PendingIntent.FLAG_CANCEL_CURRENT);
        }

        m.setRepeating(AlarmManager.RTC, TIME.getTime().getTime(), 1000 * 30, service);
    }

    @Override
    public void onDisabled(Context context)
    {
        final AlarmManager m = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        m.cancel(service);
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

}


