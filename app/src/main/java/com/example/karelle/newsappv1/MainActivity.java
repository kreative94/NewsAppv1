package com.example.karelle.newsappv1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Article>> {

    //URL for articles
    private static final String REQUEST_URL = "https://content.guardianapis.com/search?show-tags=contributor&q=children%20AND%20education&api-key=8c219b3c-c246-46ad-a471-a345b108fb26";

    //the Article adapter
    private ArticleAdapter mAdapter;

    //The empty textview
    TextView emptyTextState;

    //the id for the article loader
    private static final int articleLoaderId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add ListView
        ListView articleListView = findViewById(R.id.list);

        emptyTextState = findViewById(R.id.empty_text_view);
        articleListView.setEmptyView(emptyTextState);

        //Create new list of articles with mAdapter
        mAdapter = new ArticleAdapter(this, new ArrayList<Article>());

        //Set mAdapter to ArticleListView
        articleListView.setAdapter(mAdapter);

        articleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Article currentArticle = (Article) mAdapter.getItem(position);

                Uri articleUri = Uri.parse(currentArticle.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, articleUri);

                startActivity(websiteIntent);
            }
        });

        ConnectivityManager conMger = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = conMger.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(articleLoaderId, null, this);
        } else {
            View progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);

            emptyTextState.setText(R.string.no_internet_connection);
        }
    }
    //End of onCreate

    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("limit", "10");

        return new ArticleLoader(this, uriBuilder.toString());
    }

   @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        View theProgressBarView = findViewById(R.id.progress_bar);
        theProgressBarView.setVisibility(View.GONE);

        emptyTextState.setText(R.string.no_articles);

        mAdapter.clear();

        if (articles != null && !articles.isEmpty()) {
            mAdapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        mAdapter.clear();
    }

}
