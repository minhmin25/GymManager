package com.dev.minhmin.gymmanager.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev.minhmin.gymmanager.R;
import com.dev.minhmin.gymmanager.activity.MainActivity;
import com.dev.minhmin.gymmanager.model.Exercise;
import com.dev.minhmin.gymmanager.utils.ConstantUtils;
import com.dev.minhmin.gymmanager.utils.OnBackPressedListener;
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

public class ExerciseDetailFragment extends Fragment implements OnBackPressedListener {

    private Button btAdd;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference sref = storage.getReference();
    private Exercise exercise = new Exercise();
    private TextView title, instruction, calo;
    private ImageView image;
    private int position;
    private String part = "", reference = "";

    public static ExerciseDetailFragment newInstance() {
        ExerciseDetailFragment fragment = new ExerciseDetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        MainActivity.stateExercise = ConstantUtils.FRAGMENT_EXERCISE_DETAIL;
        ((MainActivity) getActivity()).updateTitle(MainActivity.page, MainActivity.stateExercise);
        ((MainActivity) getActivity()).updateActionbar(true, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        title = (TextView) viewGroup.findViewById(R.id.tv_exercise_title);
        calo = (TextView) viewGroup.findViewById(R.id.tv_calo_burn);
        instruction = (TextView) viewGroup.findViewById(R.id.tv_exercise_instruction);
        image = (ImageView) viewGroup.findViewById(R.id.iv_exercise_detail_image);
        btAdd = (Button) viewGroup.findViewById(R.id.bt_add_exercise);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (reference != null) {
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl(reference);
            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    exercise = dataSnapshot.getValue(Exercise.class);
                    title.setText(exercise.getName());
                    calo.setText(exercise.getCalo() + "");
                    instruction.setText(exercise.getContent());
                    StorageReference ref = sref.child("exercise/" + exercise.getImageUrl().get(1));
                    Glide.with(getActivity())
                            .using(new FirebaseImageLoader())
                            .load(ref)
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

        DatabaseReference dref = FirebaseDatabase.getInstance().getReference();
        dref.child("Exercise").child(part).child(position + "").addListenerForSingleValueEvent(new ValueEventListener() {
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
            reference = bundle.getString("exerciseRef");
            if (reference == null) {
                part = bundle.getString("part");
                position = bundle.getInt("position");
            }

        }
    }

    @Override
    public void doBack() {
        if (MainActivity.page == 2) {
            getActivity().getFragmentManager().popBackStack();
            MainActivity.stateWorkout = ConstantUtils.FRAGMENT_WORKOUT_EXERCISE;
            ((MainActivity) getActivity()).updateTitle(MainActivity.page, MainActivity.stateWorkout);
//            Fragment fragment = getActivity().getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_WORKOUT_EXERCISE);
//            getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
        } else if (MainActivity.page == 4) {
            MainActivity.stateExercise = ConstantUtils.FRAGMENT_LIST_EXERCISE;
            ((MainActivity) getActivity()).updateTitle(MainActivity.page, MainActivity.stateExercise);
//            Fragment fragment = getActivity().getFragmentManager().findFragmentByTag(ConstantUtils.FRAGMENT_TAG_LIST_EXERCISE);
//            getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
            getActivity().getFragmentManager().popBackStack(ListExerciseFragment.class.getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
