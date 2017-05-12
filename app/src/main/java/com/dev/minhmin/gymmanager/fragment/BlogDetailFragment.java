package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListBlogAdapter;
import com.dev.minhmin.gymmanager.model.Blog;

import java.util.ArrayList;

/**
 * Created by Minh min on 5/11/2017.
 */

public class BlogDetailFragment extends Fragment {

    private String key;
    private ArrayList<Blog> listBlogs = new ArrayList<>();
    private ListBlogAdapter adapter;
    private ListView lvBlogs;

    public static BlogDetailFragment newInstance() {
        BlogDetailFragment fragment = new BlogDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_blog_detail, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getArguments();
        if (b != null) {
            key = b.getString("key");
        }
    }
}
