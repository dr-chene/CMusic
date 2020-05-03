package com.example.cmusic.view.playlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.chttp.CHttp;
import com.example.chttp.CallBack;
import com.example.chttp.Request;
import com.example.chttp.RequestBody;
import com.example.cjson.CJson;
import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import bean.Songs;
import bean.Tracks;
import bean.mysonglist.PlayList;

public class PlayListActivity extends AppCompatActivity {

    private Songs songs;
    private String mName;
    private String mNickname;
    private TextView name;
    private TextView nickname;
    private TextView tracks;
    private ImageView coverImg;
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private ImageView playOrder;

    public static final String TAG = "TAG3";
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list);
        initView();
    }

    private void initView() {
        name = findViewById(R.id.playlist_name_tv);
        nickname = findViewById(R.id.playlist_activity_creator_name_tv);
        tracks = findViewById(R.id.playlist_activity_tracks_tv);
        coverImg = findViewById(R.id.playlist_activity_cover_img_iv);
        recyclerView = findViewById(R.id.playlist_activity_rv);
        toolbar = findViewById(R.id.playlist_toolbar);
        playOrder = findViewById(R.id.playlist_activity_play_order);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        playOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayListActivity.this, "play order", Toast.LENGTH_SHORT).show();
            }
        });
        getSongs();
//        设置封面
    }
    private void getSongs() {
        Intent intent = getIntent();
        Long id = intent.getLongExtra("id",0);
        mName = intent.getStringExtra("name");
        mNickname = intent.getStringExtra("nickname");
        name.setText(mName);
        nickname.setText(mNickname);
        songs = new Songs();
        CHttp http = CHttp.getChHttp();
        final Request request = new Request(MainActivity.GET_PLAYLIST_DETAIL+"?id="+id);
        http.newCall(request, new CallBack() {
            @Override
            public void onSuccess(String str) {
                List<Class> clazz = new ArrayList<>();
                clazz.add(Tracks.class);
                CJson json = new CJson(clazz);
                Log.d("TAG3", "run: "+ str);
                songs = json.formJson(str,Songs.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String track = songs.getPlaylist().getTracks().size()+" Tracks - "+songs.getPlaylist().getTrackCount();
                        tracks.setText(track);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(PlayListActivity.this);
                        recyclerView.setLayoutManager(layoutManager);
                        PlayListAdapter adapter = new PlayListAdapter(songs.getPlaylist().getTracks(),PlayListActivity.this);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFailed(Exception e) {
                    e.printStackTrace();
            }
        });
        http.execute();
    }
}
