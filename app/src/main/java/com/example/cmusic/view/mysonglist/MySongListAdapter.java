package com.example.cmusic.view.mysonglist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmusic.R;

import java.util.List;

import bean.Songs;
import bean.Tracks;
import bean.mysonglist.MySongList;
import bean.mysonglist.PlayList;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class MySongListAdapter extends RecyclerView.Adapter<MySongListAdapter.ViewHolder> {

    private Songs songs;
    private Context context;

    public MySongListAdapter(Context context,Songs songs) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.playlist_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tracks song = songs.getPlaylist().getTracks().get(position);
        holder.orderNumber.setText(""+(position+1));
        holder.songName.setText(song.getName());
        Log.d("TAG1", "onBindViewHolder: "+song.getDt());
        holder.songTime.setText(getTime(song.getDt()));
    }

    @Override
    public int getItemCount() {
        return songs == null?0:songs.getPlaylist().getTracks().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber;
        TextView songName;
        TextView songTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.playlist_order_number_tv);
            songName = itemView.findViewById(R.id.playlist_song_name_tv);
            songTime = itemView.findViewById(R.id.playlist_song_time_tv);
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
