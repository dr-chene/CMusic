package com.example.cmusic.view.play;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.cmusic.R;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView cover;
    private ImageView shaffle;
    private ImageView repeat;
    private ImageView play;
    private ImageView last;
    private ImageView next;
    private TextView lyricNow;
    private TextView lyricNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
    }

    private void initView() {
        cover = findViewById(R.id.play_cover_iv);
        shaffle = findViewById(R.id.play_play_order_shaffle_iv);
        repeat = findViewById(R.id.play_play_order_repeat_iv);
        play = findViewById(R.id.play_play_btn);
        last = findViewById(R.id.play_last_song_iv);
        next = findViewById(R.id.play_next_song_iv);
        lyricNow = findViewById(R.id.play_lyric_now_tv);
        lyricNext = findViewById(R.id.play_lyric_next_tv);
        toolbar = findViewById(R.id.play_toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        shaffle.setOnClickListener(this);
        repeat.setOnClickListener(this);
        play.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_play_order_shaffle_iv:
                break;
            case R.id.play_play_order_repeat_iv:
                break;
            case R.id.play_play_btn:
                break;
            case R.id.play_last_song_iv:
                break;
            case R.id.play_next_song_iv:
                break;
        }
    }
}
