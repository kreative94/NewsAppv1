package com.example.karelle.newsappv1;

public class Article {

    private String mTitle;
    private String mDate;
    private String mCategory;
    private String mAuthor;
    private String mUrl;

    public Article(String title, String date, String category, String authorName, String url){
        mTitle = title;
        mDate = date;
        mCategory = category;
        mAuthor = authorName;
        mUrl = url;
    }

    public String getmTitle(){
        return mTitle;
    }

    public String getmDate(){return mDate;}

    public String getmCategory(){
        return mCategory;
    }

    public String getmAuthor(){
        return mAuthor;
    }

    public String getmUrl(){
        return mUrl;
    }

}
