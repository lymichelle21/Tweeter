package com.codepath.apps.restclienttemplate.models;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Parcel
public class Tweet {

    private static final String TAG = "Tweet";
    public String body;
    public String createdAt;
    public User user;
    public String tweet_URL;

    public Tweet() {
    }

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        if (jsonObject.has("full_text")) {
            tweet.body = jsonObject.getString("full_text");
        } else {
            tweet.body = jsonObject.getString("text");
        }
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        if (!jsonObject.getJSONObject("entities").has("media")) {
            tweet.tweet_URL = "";
        } else {
            tweet.tweet_URL = jsonObject.getJSONObject("entities").getJSONArray("media").getJSONObject(0).getString("media_url_https");
        }
        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            tweets.add(fromJson(jsonArray.getJSONObject(i)));
        }
        return tweets;
    }

}
