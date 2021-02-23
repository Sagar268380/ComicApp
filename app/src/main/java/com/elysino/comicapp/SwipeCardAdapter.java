package com.elysino.comicapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class SwipeCardAdapter extends BaseAdapter {

    ArrayList<ComicData> card_list;
    Context context;


    public SwipeCardAdapter(Context context,  ArrayList<ComicData> card_list) {
        this.card_list = card_list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return this.card_list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, final View contentView, ViewGroup parent){

        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        ComicData comicData=card_list.get(position);
        ImageView itemImage = (view.findViewById(R.id.item_image));

        Glide.with(context)
                .load(comicData.getImg())
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(itemImage);
        TextView tvName=(view.findViewById(R.id.item_name));
        tvName.setText(comicData.getSafeTitle());

        Log.d("adapter", "getView: "+comicData.getSafeTitle());

        TextView tvAlt=(view.findViewById(R.id.item_alt));
        tvAlt.setText(comicData.getTranscript());

        TextView tvDate=(view.findViewById(R.id.txtDate));
        tvDate.setText("#"+comicData.getNum()+" - "+comicData.getDay() +"/"+comicData.getMonth()+"/"+comicData.getYear());

        return view;
    }
}
