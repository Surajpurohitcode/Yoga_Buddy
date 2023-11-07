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

import com.example.yogabuddy.MusicPlayActivity;
import com.example.yogabuddy.R;
import com.example.yogabuddy.model.SongsModel;

import java.util.ArrayList;

public class SongsPlayListAdpter extends RecyclerView.Adapter<SongsPlayListAdpter.ViewHolder> {

    ArrayList<SongsModel> arrayList;
    Context context;

    public SongsPlayListAdpter(ArrayList<SongsModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SongsPlayListAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_songs_list_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongsPlayListAdpter.ViewHolder holder, int position) {

        holder.songTitle.setText(arrayList.get(position).getSongTitle());
        holder.songImage.setImageResource(arrayList.get(position).getSongImg());
        holder.songDescription.setText(arrayList.get(position).getSongDescription());
        holder.songTime.setText(arrayList.get(position).getSongTime());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicPlayActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("songtitle",arrayList.get(position).getSongTitle());
                intent.putExtra("songdes",arrayList.get(position).getSongDescription());
                intent.putExtra("songimage",arrayList.get(position).getSongImg());
                intent.putExtra("songtime",arrayList.get(position).getSongTime());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        ImageView songImage;
        TextView songTitle,songDescription,songTime;

        LinearLayout card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            songImage = itemView.findViewById(R.id.songs_imageview);
            songTitle = itemView.findViewById(R.id.song_title);
            songDescription = itemView.findViewById(R.id.song_description);
            songTime = itemView.findViewById(R.id.song_time);
            card = itemView.findViewById(R.id.play_list_card);

        }
    }
}
