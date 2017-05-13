package com.dev.minhmin.gymmanager.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Butalo on 10/05/2017.
 */

public class ExerciseDetailFragment extends Fragment {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference sref = storage.getReference();
    Activity activity;
    Exercise exercise = new Exercise();
    TextView title, instruction, calo;
    ImageView image;
    private int position;
    private String part;

    public static ExerciseDetailFragment newInstance() {
        ExerciseDetailFragment fragment = new ExerciseDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        title = (TextView) getView().findViewById(R.id.tv_exercise_title);
        calo = (TextView) getView().findViewById(R.id.tv_calo_burn);
        instruction = (TextView) getView().findViewById(R.id.tv_exercise_instruction);
        image = (ImageView) getView().findViewById(R.id.iv_exercise_image);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Exercise").child(part).child(position + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                exercise = dataSnapshot.getValue(Exercise.class);
//                for (DataSnapshot i : dataSnapshot.getChildren()) {
//                    exercise = i.getValue(Exercise.class);
//
//                }
                title.setText(exercise.getName());
                calo.setText(exercise.getCalo());
                instruction.setText(exercise.getContent());
                StorageReference mref = sref.child("exercise/" + exercise.getImageUrl().get(1));
                Glide.with(activity)
                        .using(new FirebaseImageLoader())
                        .load(mref)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
            part = bundle.getString("part");
        }
    }
}
