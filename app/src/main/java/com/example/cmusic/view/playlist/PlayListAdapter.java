package com.example.cmusic.view.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import java.util.List;

import bean.Tracks;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder> {

    private List<Tracks> tracks;
    private Context context;

    public PlayListAdapter(List<Tracks> tracks, Context context) {
        this.tracks = tracks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.orderNumber.setText(""+(position+1));
        holder.songName.setText(tracks.get(position).getName());
        holder.songTime.setText(getTime(tracks.get(position).getDt()));
        holder.song.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.changeIds(context,tracks.get(position).getId());
                Toast.makeText(context, "假装开始播放歌曲"+tracks.get(position).getName(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, "play", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracks == null?0:tracks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumber;
        TextView songName;
        TextView songTime;
        FrameLayout song;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.playlist_order_number_tv);
            songName = itemView.findViewById(R.id.playlist_song_name_tv);
            songTime = itemView.findViewById(R.id.playlist_song_time_tv);
            song = itemView.findViewById(R.id.play_list_play_item);
        }
    }
    private String getTime(int time) {
        time /=1000;
        int m = time/60;
        int s = time-60*m;
        String M;
        if (m < 10) {
            M = ""+0+m;
        }else M = ""+m;
        String S;
        if (s < 10) {
            S = "" +0+s;
        }else S = ""+s;
        return M + ":"+S;
    }
}
