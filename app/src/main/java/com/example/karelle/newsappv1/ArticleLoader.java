package com.example.karelle.newsappv1;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

public class ArticleLoader extends AsyncTaskLoader<List<Article>> {

    private String mUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Article> loadInBackground() {
        if (mUrl == null){
            return null;
        }

        List<Article> articles = QueryUtils.fetchArticleData(mUrl);
        return articles;
    }
}