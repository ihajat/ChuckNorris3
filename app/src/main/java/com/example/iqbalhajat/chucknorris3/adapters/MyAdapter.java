package com.example.iqbalhajat.chucknorris3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.iqbalhajat.chucknorris3.models.CardJoke;
import com.example.iqbalhajat.chucknorris3.R;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context c;
    ArrayList<CardJoke> jokes;

    /*
    CONSTRUCTOR
     */
    public MyAdapter(Context c, ArrayList<CardJoke> spaceships) {
        this.c = c;
        this.jokes = spaceships;
    }

    //INITIALIE VH
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    //BIND DATA
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.jokeTxt.setText(showHtml(jokes.get(position).getJoke()));
        holder.titleTxt.setText(jokes.get(position).getTitle());

    }

    /*
    TOTAL ITEMS
     */
    @Override
    public int getItemCount() {
        return jokes.size();

    }

    /*
    ADD DATA TO ADAPTER
     */
    public void add(CardJoke s) {
        jokes.add(s);
        notifyDataSetChanged();
    }

    /*
    CLEAR DATA FROM ADAPTER
     */
    public void clear() {
        jokes.clear();
        notifyDataSetChanged();
    }

    /*
    VIEW HOLDER CLASS
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView jokeTxt,titleTxt;

        public MyHolder(View itemView) {
            super(itemView);
            this.jokeTxt = (TextView) itemView.findViewById(R.id.jokeTxt);
            this.titleTxt = (TextView) itemView.findViewById(R.id.titleTxt);

        }
    }

    /**
     *  Create a displayable styled text of the string.
     *
     *  @returns a spanned object where the HTML tags in the string have been interpreted
     */
    @SuppressWarnings("deprecation")
    public static Spanned showHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

}