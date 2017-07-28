package com.example.ekzhu.newsapp;

/**
 * Created by ekzhu on 13.07.2017.
 */

public class NewsItem {

    private String mTitle;
    private String mSectionName;
    private String mWebURL;
    private String mPublicationDate;

    /**
     * Create a new NewsItem object
     */
    public NewsItem(String title, String sectionName, String webURL, String publicationDate) {
        mTitle = title;
        mSectionName = sectionName;
        mWebURL = webURL;
        mPublicationDate = publicationDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSectionName() {
        return mSectionName;
    }

    public String getWebURL() {
        return mWebURL;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }


}
