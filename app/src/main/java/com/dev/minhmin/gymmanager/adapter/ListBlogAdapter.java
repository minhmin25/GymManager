package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Blog;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Minh min on 4/19/2017.
 */

public class ListBlogAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Blog> listBlogs = new ArrayList<>();

    public ListBlogAdapter(Activity activity, ArrayList<Blog> listBlogs) {
        this.activity = activity;
        this.listBlogs = listBlogs;
    }

    @Override
    public int getCount() {
        return listBlogs.size();
    }

    @Override
    public Object getItem(int i) {
        return listBlogs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_list_blog, viewGroup, false);
            viewholder = new Viewholder();
            viewholder.tvTitle = (TextView) view.findViewById(R.id.tv_blog_title);
            viewholder.tvIntro = (TextView) view.findViewById(R.id.tv_blog_intro);
            viewholder.tvReadBlog = (TextView) view.findViewById(R.id.tv_read_blog);
            viewholder.ivImage = (ImageView) view.findViewById(R.id.iv_blog_image);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }

        viewholder.tvTitle.setText(listBlogs.get(i).getTitle());
        viewholder.tvIntro.setText(listBlogs.get(i).getIntro());
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("blog").child(listBlogs.get(i).getImageUrl());
        Glide.with(activity)
                .using(new FirebaseImageLoader())
                .load(ref)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewholder.ivImage);
//        viewholder.ivImage.setImageResource(listBlogs.get(i).getThumbUrl());
        viewholder.tvReadBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private class Viewholder {
        TextView tvTitle, tvIntro, tvReadBlog;
        ImageView ivImage;
    }
}
