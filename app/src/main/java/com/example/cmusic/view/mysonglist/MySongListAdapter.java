package com.example.cmusic.view.mysonglist;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmusic.R;
import com.example.cmusic.view.playlist.PlayListActivity;

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
        View view = LayoutInflater.from(context).inflate(R.layout.my_song_list_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final PlayList album = songs.getPlaylist().get(position);
        holder.songsName.setText(album.getName());
        holder.creatorName.setText(album.getCreator().getNickname());
        Log.d("TAG1", "onBindViewHolder: ");
        holder.track.setText(album.getTrackCount()+" Tracks - 2020");
        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"play",Toast.LENGTH_SHORT).show();
            }
        });
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context,"detail",Toast.LENGTH_SHORT).show();
                String name = album.getName();
                String nickname = album.getCreator().getNickname();
                long id = album.getId();
                Intent intent = new Intent(context, PlayListActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("nickname",nickname);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs == null?0:songs.getPlaylist().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView play;
        ConstraintLayout detail;
        TextView songsName;
        TextView creatorName;
        TextView track;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songsName = itemView.findViewById(R.id.my_song_list_name_tv);
            creatorName = itemView.findViewById(R.id.my_song_list_creator_name_tv);
            track = itemView.findViewById(R.id.my_song_list_track_tv);
            detail = itemView.findViewById(R.id.my_song_list_album_detail);
            play = itemView.findViewById(R.id.my_song_list_album_play_iv);
        }
    }
}
