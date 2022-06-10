package com.codepath.apps.restclienttemplate;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.facebook.stetho.Stetho;

public class TwitterApp extends Application {

    MyDatabase myDatabase;

    public static TwitterClient getRestClient(Context context) {
        return (TwitterClient) TwitterClient.getInstance(TwitterClient.class, context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myDatabase = Room.databaseBuilder(this, MyDatabase.class,
                MyDatabase.NAME).fallbackToDestructiveMigration().build();
        Stetho.initializeWithDefaults(this);
    }

    public MyDatabase getMyDatabase() {
        return myDatabase;
    }
}