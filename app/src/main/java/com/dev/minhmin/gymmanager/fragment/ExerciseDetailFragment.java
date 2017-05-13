package com.dev.minhmin.gymmanager.fragment;

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
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
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

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private Exercise exercise = new Exercise();
    private TextView title, instruction, calo;
    private ImageView image;
    private int position;
    private String part, ref;

    public static ExerciseDetailFragment newInstance() {
        ExerciseDetailFragment fragment = new ExerciseDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ((MainActivity) getActivity()).updateActionbar(ConstantUtils.TITLE_EXERCISE_DETAIL, true, false, false);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        title = (TextView) viewGroup.findViewById(R.id.tv_exercise_title);
        calo = (TextView) viewGroup.findViewById(R.id.tv_calo_burn);
        instruction = (TextView) viewGroup.findViewById(R.id.tv_exercise_instruction);
        image = (ImageView) viewGroup.findViewById(R.id.iv_exercise_detail_image);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!ref.equals("")) {
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl(ref);
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    exercise = dataSnapshot.getValue(Exercise.class);
                    title.setText(exercise.getName());
                    calo.setText(exercise.getCalo() + "");
                    instruction.setText(exercise.getContent());
                    StorageReference mref = sref.child("exercise/" + exercise.getImageUrl().get(1));
                    Glide.with(getActivity())
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
            return;
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("Exercise").child(part).child(position + "").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                exercise = dataSnapshot.getValue(Exercise.class);
                title.setText(exercise.getName());
                calo.setText(exercise.getCalo() + "");
                instruction.setText(exercise.getContent());
                StorageReference mref = sref.child("exercise/" + exercise.getImageUrl().get(1));
                Glide.with(getActivity())
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
            ref = bundle.getString("exerciseRef");
            if (!ref.equals("")) return;
            position = bundle.getInt("position");
            part = bundle.getString("part");

        }
    }
}
