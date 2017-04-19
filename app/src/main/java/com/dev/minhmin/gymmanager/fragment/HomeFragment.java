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
 * Created by Minh min on 4/19/2017.
 */

public class HomeFragment extends Fragment {

    private ArrayList<Blog> listBlogs = new ArrayList<>();
    private int[] image = new int[]{R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4};
    private ListView lvBlog;
    private ListBlogAdapter adapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getListData();
        lvBlog = (ListView) getView().findViewById(R.id.lv_blog);
        adapter = new ListBlogAdapter(getActivity(), listBlogs);
        lvBlog.setAdapter(adapter);
    }

    private void getListData() {
        for (int i = 0; i < 7; i++) {
            Blog b = new Blog("Title " + i, "Content " + i, "Intro " + i, "url " + i, image[i % 4]);
            listBlogs.add(b);
        }
    }
}
