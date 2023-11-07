package com.example.yogabuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.yogabuddy.adpter.ExerciseAdpter;
import com.example.yogabuddy.databinding.ActivityExerciseDetailsBinding;
import com.example.yogabuddy.model.ExerciseCardModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExerciseDetailsActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    ArrayList<ExerciseCardModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityExerciseDetailsBinding binding = ActivityExerciseDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        String exerciseName = getIntent().getStringExtra("exerciseName");

        binding.toolbar.setTitle(exerciseName);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.exerciseList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        ExerciseAdpter adpter = new ExerciseAdpter(arrayList,getApplicationContext());
        binding.exerciseList.setAdapter(adpter);


        if (exerciseName.equals("Basic Yoga Exercise")){

            arrayList.add(new ExerciseCardModel("Cat-Cow Pose","This pose helps to warm up the spine and improve flexibility.","2 minutes","15 calories",R.drawable.catcow));
            arrayList.add(new ExerciseCardModel("Downward-Facing Dog","This pose is a great way to stretch the hamstrings, calves, and shoulders.","2 minutes","15 calories",R.drawable.downward_facing_dog));
            arrayList.add(new ExerciseCardModel("Child's Pose","This pose is a great way to relax and stretch the spine.","2 minutes","10 calories",R.drawable.child_poses));
            arrayList.add(new ExerciseCardModel("Mountain Pose","This pose is a good foundation for other standing poses.","2 minutes","10 calories",R.drawable.mountain_poses));
            arrayList.add(new ExerciseCardModel("Tree Pose","This pose helps to improve balance and coordination.","2 minutes","10 calories",R.drawable.tree_poses));

        }

        if (exerciseName.equals("Full Body Exercise")){

            arrayList.add(new ExerciseCardModel("Cat-Cow Pose","This pose helps to warm up the spine and improve flexibility.","2 minutes","15 calories",R.drawable.catcow));
            arrayList.add(new ExerciseCardModel("Downward-Facing Dog","This pose is a great way to stretch the hamstrings, calves, and shoulders.","2 minutes","15 calories",R.drawable.downward_facing_dog));
            arrayList.add(new ExerciseCardModel("Child's Pose","This pose is a great way to relax and stretch the spine.","2 minutes","10 calories",R.drawable.child_poses));
            arrayList.add(new ExerciseCardModel("Bridge Pose","Strengthens the back of the body, including the hamstrings, glutes, and lower back.","2 minutes","10 calories",R.drawable.bridge));
            arrayList.add(new ExerciseCardModel("Mountain Pose","This pose is a good foundation for other standing poses.","2 minutes","10 calories",R.drawable.mountain_poses));
            arrayList.add(new ExerciseCardModel("Tree Pose","This pose helps to improve balance and coordination.","2 minutes","10 calories",R.drawable.tree_poses));
            arrayList.add(new ExerciseCardModel("Warrior II Pose","Strengthens the legs, glutes, and core.","2 minutes","20 calories",R.drawable.warrior_ll));
            arrayList.add(new ExerciseCardModel("Triangle Pose","Stretches the hamstrings, hip flexors, and groin.","2 minutes","20 calories",R.drawable.triangleforward_l));
            arrayList.add(new ExerciseCardModel("Legs-Up-the-Wall Pose","Improves circulation and reduces fatigue.","5 minutes","25 calories",R.drawable.legs_up_wall));
            arrayList.add(new ExerciseCardModel("Corpse Pose","Relaxes the body and mind.","5 minutes","10 calories",R.drawable.corpse));

        }

        if (exerciseName.equals("Upper Body")){

            arrayList.add(new ExerciseCardModel("Cat-Cow Pose","This pose helps to warm up the spine and improve flexibility.","2 minutes","15 calories",R.drawable.catcow));
            arrayList.add(new ExerciseCardModel("Chaturanga","Strengthens the triceps, shoulders, and chest.","2 minutes","20 calories",R.drawable.chaturanga_poses));
            arrayList.add(new ExerciseCardModel("Downward-Facing Dog","Opens the chest and strengthens the back.","2 minutes","20 calories",R.drawable.downward_facing_dog));
            arrayList.add(new ExerciseCardModel("Child's Pose","This pose is a great way to relax and stretch the spine.","2 minutes","10 calories",R.drawable.child_poses));
            arrayList.add(new ExerciseCardModel("Upward-Facing Dog","This pose is a good foundation for other standing poses.","2 minutes","10 calories",R.drawable.upwarddog));
            arrayList.add(new ExerciseCardModel("Bridge Pose","Strengthens the back of the body, including the hamstrings, glutes, and lower back.","2 minutes","10 calories",R.drawable.bridge));

        }

        if (exerciseName.equals("Lower body")){

            arrayList.add(new ExerciseCardModel("Standing Forward Bend","Stretches the hamstrings and calves.","2 minutes","10 calories",R.drawable.forwardbend));
            arrayList.add(new ExerciseCardModel("Wide-Legged Forward Bend","Stretches the hamstrings, inner thighs, and groin.","2 minutes","10 calories",R.drawable.wide_legged_forward_bend));
            arrayList.add(new ExerciseCardModel("Warrior I Pose","Strengthens the legs, glutes, and core.","4 minutes","20 calories",R.drawable.warrior_ll));
            arrayList.add(new ExerciseCardModel("Warrior II Pose","Strengthens the legs, glutes, and core.","2 minutes","20 calories",R.drawable.warriorfirst));
            arrayList.add(new ExerciseCardModel("Triangle Pose","Stretches the hamstrings, hip flexors, and groin.","2 minutes","20 calories",R.drawable.triangleforward_l));
            arrayList.add(new ExerciseCardModel("Chair Pose","Strengthens the legs, glutes, and core.","2 minutes","15 calories",R.drawable.chair));
            arrayList.add(new ExerciseCardModel("Pigeon Pose","Stretches the hips, glutes, and hamstrings.","2 minutes","20 calories",R.drawable.pigeon_king_full));

        }


    }
}