package com.dev.minhmin.gymmanager.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.fragment.FoodDetailFragment;
import com.dev.minhmin.gymmanager.model.Food;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.MethodUtils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/6/2017.
 */

public class ListFoodAdapter extends BaseAdapter implements Filterable {
    private Activity activity;
    private String date = "";

    private ArrayList<Food> listfood = new ArrayList<>();
    private ArrayList<Food> template;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private MethodUtils methodUtils = new MethodUtils();

    public ListFoodAdapter(Activity activity, ArrayList<Food> listfood) {
        this.activity = activity;
        this.listfood = listfood;

    }

    public ListFoodAdapter(Activity activity, String date, ArrayList<Food> listfood) {
        this.activity = activity;
        this.date = date;
        this.listfood = listfood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int getCount() {
        return listfood.size();
    }

    @Override
    public Object getItem(int position) {
        return listfood.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //return null;
        Viewholder viewholder;
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_list_food, parent, false);
            viewholder = new Viewholder();
            viewholder.tvName = (TextView) view.findViewById(R.id.tv_name_food_list);
            viewholder.tvNumber = (TextView) view.findViewById(R.id.tv_number_food_list);
            viewholder.ivfood = (ImageView) view.findViewById(R.id.iv_food_list);
            viewholder.ivdetails = (ImageView) view.findViewById(R.id.iv_detail_food);
            view.setTag(viewholder);
        } else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.tvName.setText(listfood.get(i).getName());
        String s = listfood.get(i).getCount() + " " + listfood.get(i).getUnit() + "-" + (listfood.get(i).getCalo() + " Calo");
        viewholder.tvNumber.setText(s);
        StorageReference mref = sref.child("food/" + listfood.get(i).getImageUrl());
        Glide.with(activity)
                .using(new FirebaseImageLoader())
                .load(mref)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(viewholder.ivfood);
        final Food f = listfood.get(i);

        viewholder.ivdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("idFood", f.getId());
                bundle.putString("date", date);
                FoodDetailFragment fragment = new FoodDetailFragment();
                fragment.setArguments(bundle);
                replaceFragment(fragment);
            }
        });


        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        ft.replace(R.id.layout_meal, fragment, ConstantUtils.FRAGMENT_TAG_FOOD_DETAIL);
        ft.commit();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults result = new FilterResults();
                final ArrayList<Food> listResult = new ArrayList<>();
                if (template == null) {
                    template = listfood;
                }
                if (charSequence != null) {
                    if (template != null && template.size() > 0) {
                        for (final Food f : template) {
                            if (f.getName().toLowerCase().contains(charSequence.toString())) {
                                listResult.add(f);
                            }
                        }
                    }
                    result.values = listResult;
                }
                return result;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listfood = (ArrayList<Food>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private class Viewholder {
        TextView tvName, tvNumber;
        ImageView ivfood, ivdetails;
    }


}
