package com.example.yogabuddy.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yogabuddy.ExerciseActivity;
import com.example.yogabuddy.R;
import com.example.yogabuddy.model.ExerciseCardModel;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ExerciseAdpter extends RecyclerView.Adapter<ExerciseAdpter.ViewHolder> {

    ArrayList<ExerciseCardModel> arrayList;

    Context context;

    public ExerciseAdpter(ArrayList<ExerciseCardModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exercise_card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseAdpter.ViewHolder holder, int position) {
        holder.exerciseTitle.setText(arrayList.get(position).getExerciseTitle());
        holder.exerciseDes.setText(arrayList.get(position).getExerciseDes());
        holder.exerciseTime.setText(arrayList.get(position).getExerciseTime());
        holder.exerciseKcal.setText(arrayList.get(position).getExerciseKcal());
        holder.exerciseImg.setImageResource(arrayList.get(position).getExerciseImg());

        holder.exerciseCard.setOnClickListener(view -> {
            Intent intent = new Intent(context, ExerciseActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("yogaTitle",arrayList.get(position).getExerciseTitle());
            intent.putExtra("yoga_info",arrayList.get(position).getExerciseDes());
            intent.putExtra("yogaMinutes",arrayList.get(position).getExerciseTime());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseTitle,exerciseDes,exerciseTime,exerciseKcal;
        ImageView exerciseImg;

        LinearLayout exerciseCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            exerciseTitle = itemView.findViewById(R.id.exercise_title);
            exerciseDes = itemView.findViewById(R.id.exercise_des);
            exerciseTime = itemView.findViewById(R.id.exercise_time);
            exerciseKcal = itemView.findViewById(R.id.exercise_kcal);
            exerciseCard = itemView.findViewById(R.id.exercise_card);
            exerciseImg = itemView.findViewById(R.id.exercise_image);

        }
    }
}
