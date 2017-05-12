package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.adapter.ListBlogAdapter;
import com.dev.minhmin.gymmanager.model.Blog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Minh min on 4/19/2017.
 */

public class HomeFragment extends Fragment {

    private ArrayList<Blog> listBlogs = new ArrayList<>();
    private ListView lvBlog;
    private ListBlogAdapter adapter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View viewGroup = inflater.inflate(R.layout.fragment_home, container, false);
        lvBlog = (ListView) viewGroup.findViewById(R.id.lv_blog);
        adapter = new ListBlogAdapter(getActivity(), listBlogs);
        lvBlog.setAdapter(adapter);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Blog").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listBlogs.clear();
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Blog b = i.getValue(Blog.class);
                    listBlogs.add(b);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lvBlog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("key", listBlogs.get(i).getId());
                BlogDetailFragment fragment = new BlogDetailFragment().newInstance();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.layout_main, fragment);
        ft.commit();
    }
}
