package com.example.ekzhu.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by ekzhu on 13.07.2017.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    /** Query Url */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsItem> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        // Call the fetchNewsData method from QueryUtils and return the list of news articles
        List<NewsItem> newsItems = QueryUtils.fetchNewsData(mUrl, getContext());
        return newsItems;
    }
}
