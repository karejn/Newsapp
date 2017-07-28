package com.example.ekzhu.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ekzhu on 13.07.2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<NewsItem> mNews;
    private Context mContext;
    private String mURL;

    public static final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView newsTitle;
        public TextView newsSection;
        public TextView newsPublicationDate;
        public TextView newsPublicationTime;
        private Context context;

        public ViewHolder(Context context, View itemView) {

            super(itemView);
            this.context = context;
            itemView.setOnClickListener(this);

            newsTitle = (TextView) itemView.findViewById(R.id.title_text_view);
            newsSection = (TextView) itemView.findViewById(R.id.section_text_view);
            newsPublicationDate = (TextView) itemView.findViewById(R.id.date_text_view);
            newsPublicationTime = (TextView) itemView.findViewById(R.id.time_text_view);
        }

        // Handles the row being being clicked
        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            NewsItem newsItem = mNews.get(position);

            mURL = newsItem.getWebURL();

            Uri newsURI = Uri.parse(mURL);
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsURI);
            context.startActivity(websiteIntent);
        }
    }

    public NewsAdapter(Context context, List<NewsItem> newsItems) {
        mContext = context;
        mNews = newsItems;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View listView = inflater.inflate(R.layout.list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(mContext, listView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {

        NewsItem newsItem = mNews.get(position);

        TextView newsTitleTextView = viewHolder.newsTitle;
        TextView newsSectionTextView = viewHolder.newsSection;
        TextView newsPublicationDateTextView = viewHolder.newsPublicationDate;
        TextView newsPublicationTimeTextView = viewHolder.newsPublicationTime;

        newsTitleTextView.setText(newsItem.getTitle());
        newsSectionTextView.setText(newsItem.getSectionName());
        newsPublicationDateTextView.setText(convertDateFormat(newsItem.getPublicationDate()));
        newsPublicationTimeTextView.setText(convertTimeFormat(newsItem.getPublicationDate()));
    }

    // Convert json DateTime to Date and Time
    public String convertDateFormat(String input) {
        input = input.substring(0, input.length() - 1);
        String oldFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String newFormat = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(oldFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(newFormat);
        Date date = null;
        String output = "";
        try {
            date = inputFormat.parse(input);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "DateTime parse exception: " + e);
        }
        return output;
    }

    public String convertTimeFormat(String input) {
        input = input.substring(0, input.length() - 1);
        String oldFormat = "yyyy-MM-dd'T'HH:mm:ss";
        String newFormat = "HH:mm";
        SimpleDateFormat inputFormat = new SimpleDateFormat(oldFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(newFormat);
        Date date = null;
        String output = "";
        try {
            date = inputFormat.parse(input);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "DateTime parse exception: " + e);
        }
        return output;
    }

    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public void addAll(List<NewsItem> newsItemList) {
        mNews.clear();
        mNews.addAll(newsItemList);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mNews.clear();
        notifyDataSetChanged();
    }


}
