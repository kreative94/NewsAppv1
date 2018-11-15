package com.example.karelle.newsappv1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter {
    ArticleAdapter(Activity context, ArrayList<Article> articles){
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView;
        listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Article currentArticle = (Article) getItem(position);

        //Article TitleTextView
        TextView titleTextView = listItemView.findViewById(R.id.title_textview);
        titleTextView.setText(currentArticle.getmTitle());

        //Date TextView
        TextView dateTextView = listItemView.findViewById(R.id.date_textview);
        dateTextView.setText(currentArticle.getmDate());
        //Category TextView
        TextView categoryTextView = listItemView.findViewById(R.id.category_textview);
        //Author TextView
        TextView authorTextView = listItemView.findViewById(R.id.author_textview);

        categoryTextView.setText(currentArticle.getmCategory());
        authorTextView.setText(currentArticle.getmAuthor());

        return listItemView;
    }
}
