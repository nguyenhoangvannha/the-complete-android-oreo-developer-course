package com.example.nhvn.tinhte.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhvn.tinhte.R;
import com.example.nhvn.tinhte.model.TinhTePost;

import java.util.ArrayList;

/**
 * Created by nguye on 1/30/2018.
 */

public class TinhTePostAdapter extends BaseAdapter {
    private ArrayList<TinhTePost> tinhTePosts;
    private Context context;
    public TinhTePostAdapter(ArrayList<TinhTePost> tinhTePosts, Context context) {
        this.tinhTePosts = tinhTePosts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tinhTePosts.size();
    }

    @Override
    public Object getItem(int i) {
        return tinhTePosts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView = null;
        if(view == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.newfeed_item, viewGroup, false);
            ViewHolder holder = new ViewHolder();
            holder.postImageView = rowView.findViewById(R.id.postImageView);
            holder.avatarImageView = rowView.findViewById(R.id.avatarImageView);
            holder.usernameTextView = rowView.findViewById(R.id.usernameTextView);
            holder.timeTextView = rowView.findViewById(R.id.timeTextView);
            holder.collectTextView = rowView.findViewById(R.id.collectionTextView);
            holder.titleTextView = rowView.findViewById(R.id.titleTextView);
            rowView.setTag(holder);
        } else{
            rowView = view;
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.avatarImageView.setImageBitmap(tinhTePosts.get(i).getAvatarImage());
        holder.postImageView.setImageBitmap(tinhTePosts.get(i).getPostImage());
        holder.usernameTextView.setText(tinhTePosts.get(i).getOwner());
        holder.timeTextView.setText(tinhTePosts.get(i).getTime());
        holder.collectTextView.setText(tinhTePosts.get(i).getCollect());
        holder.titleTextView.setText(tinhTePosts.get(i).getTitle());
        return rowView;
    }
    class ViewHolder{
        ImageView postImageView;
        ImageView avatarImageView;
        TextView usernameTextView;
        TextView timeTextView;
        TextView collectTextView;
        TextView titleTextView;
    }
}
