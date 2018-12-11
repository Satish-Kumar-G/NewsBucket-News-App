package com.dcoders.satishkumar.g.newsbucket;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;

import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.IMAGE;
import static com.dcoders.satishkumar.g.newsbucket.FullDetailActivity.TITLE;
import static com.dcoders.satishkumar.g.newsbucket.R.*;

/**
 * Implementation of App Widget functionality.
 */
public class NewsBucketWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        String title=null,imagelink=null;
        SharedPreferences sharedPreferences=context.getSharedPreferences("SATISH",Context.MODE_PRIVATE);
        if (sharedPreferences!=null)
        {
            title=sharedPreferences.getString(TITLE,"Please Visit The App");
            imagelink=sharedPreferences.getString(IMAGE,null);
        }

        CharSequence widgetText = context.getString(string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), layout.news_bucket_widget);
        views.setTextViewText(R.id.widget_textView, title);
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(id.myWidget,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

