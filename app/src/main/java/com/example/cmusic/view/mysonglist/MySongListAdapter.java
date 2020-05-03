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

import bean.SongList;
import bean.Songs;
import bean.Tracks;
import bean.mysonglist.MySongList;
import bean.mysonglist.PlayList;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class MySongListAdapter extends RecyclerView.Adapter<MySongListAdapter.ViewHolder> {

    private MySongList songs;
    private Context context;

    public MySongListAdapter(Context context, MySongList songs) {
        this.songs = songs;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my_song_list_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList album = songs.getPlaylist().get(position);
        holder.songsName.setText(album.getName());
        holder.creatorName.setText(album.getCreator().getNickname());
        Log.d("TAG1", "onBindViewHolder: ");
        holder.track.setText(album.getTrackCount()+" Tracks - 2020");
    }

    @Override
    public int getItemCount() {
        return songs == null?0:songs.getPlaylist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songsName;
        TextView creatorName;
        TextView track;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songsName = itemView.findViewById(R.id.my_song_list_name_tv);
            creatorName = itemView.findViewById(R.id.my_song_list_creator_name_tv);
            track = itemView.findViewById(R.id.my_song_list_track_tv);
        }
    }
}
