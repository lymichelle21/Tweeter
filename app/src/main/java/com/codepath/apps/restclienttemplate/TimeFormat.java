package com.codepath.apps.restclienttemplate;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.util.Locale;

public class TimeFormat {
    public static String TAG = "TimeFormat";
    private static final int SECOND_MILLIS = 1000;
    private static final int MINUTE_MILLIS = 60 * SECOND_MILLIS;
    private static final int HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        try {
            long time = sf.parse(rawJsonDate).getTime();
            long now = System.currentTimeMillis();

            Log.d(TAG, "Curr time" + now);
            Log.d(TAG, "Tweet time" + time);
            Log.d(TAG,"Time Difference "+(now-time));

            final long diff = now - time;
            if (diff < MINUTE_MILLIS) {
                Log.d(TAG, "just now");
                return "just now";
            } else if (diff < 2 * MINUTE_MILLIS) {
                Log.d(TAG, "a min ago");
                return "a minute ago";
            } else if (diff < 50 * MINUTE_MILLIS) {
                Log.d(TAG, "a min ago");
                return diff / MINUTE_MILLIS + " m";
            } else if (diff < 90 * MINUTE_MILLIS) {
                Log.d(TAG, "an hour ago");
                return "an hour ago";
            } else if (diff < 24 * HOUR_MILLIS) {
                Log.d(TAG, "yeet");
                return diff / HOUR_MILLIS + " h";
            } else if (diff < 48 * HOUR_MILLIS) {
                Log.d(TAG, "yeet");
                return "yesterday";
            } else {
                Log.d(TAG, "yeet");
                return diff / DAY_MILLIS + " d";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }
}
