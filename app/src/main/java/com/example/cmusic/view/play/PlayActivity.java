package com.example.cmusic.view.play;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.chttp.CHttp;
import com.example.chttp.CallBack;
import com.example.chttp.Request;
import com.example.cjson.CJson;
import com.example.cmusic.R;
import com.example.cmusic.view.main.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.song.Song;
import bean.song.SongBean;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private long id;
    private SongBean songBean;
    private Toolbar toolbar;
    private ImageView cover;
    private ImageView shaffle;
    private ImageView repeat;
    private ImageView play;
    private ImageView last;
    private ImageView next;
    private TextView lyricNow;
    private TextView lyricNext;
    private TextView totalTime;
    private TextView leftTime;
    private TextView songName;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        initView();
    }


    private void initView() {
        songName = findViewById(R.id.play_song_name_tv);
        cover = findViewById(R.id.play_cover_iv);
        shaffle = findViewById(R.id.play_play_order_shaffle_iv);
        repeat = findViewById(R.id.play_play_order_repeat_iv);
        play = findViewById(R.id.play_play_btn);
        last = findViewById(R.id.play_last_song_iv);
        next = findViewById(R.id.play_next_song_iv);
        lyricNow = findViewById(R.id.play_lyric_now_tv);
        lyricNext = findViewById(R.id.play_lyric_next_tv);
        seekBar = findViewById(R.id.play_seek_bar);
        totalTime = findViewById(R.id.play_time_sum_tv);
        leftTime = findViewById(R.id.play_time_left_tv);
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
        loadSong();
    }

    private void loadSong() {
        id = MainActivity.ids;
//        Toast.makeText(this, "id"+id, Toast.LENGTH_SHORT).show();
        CHttp http = CHttp.getChHttp();
        Request request = new Request(MainActivity.GET_SONG_DETAIL+"?ids="+id);
        http.newCall(request, new CallBack() {
            @Override
            public void onSuccess(String str) {
//                Log.d("TAG5", "onSuccess: "+str);
                List<Class> clazz = new ArrayList<>();
                clazz.add(Song.class);
                CJson json = new CJson(clazz);
                songBean = json.formJson(str,SongBean.class);
//                Log.d("TAG5", "onSuccess: "+(songBean == null));
                Song song = songBean.getSongs().get(0);
                songName.setText(song.getName());
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
        http.execute();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_play_order_shaffle_iv:
                Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_play_order_repeat_iv:
                Toast.makeText(this, "循环播放", Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_play_btn:
                Toast.makeText(this, "播放/暂停", Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_last_song_iv:
                Toast.makeText(this, "上一首", Toast.LENGTH_SHORT).show();
                break;
            case R.id.play_next_song_iv:
                Toast.makeText(this, "下一首", Toast.LENGTH_SHORT).show();
                break;
        }
    }
//    private MusicService musicService;
//    private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
//    private ServiceConnection sc = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            musicService = ((MusicService.MyBinder)service).getService();
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            musicService = null;
//        }
//    };
//    private void prepareMusicService() {
//        bindServiceConnection();
//        seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
//        seekBar.setMax(MusicService.mediaPlayer.getDuration());
//    }
//    private void bindServiceConnection() {
//        Intent intent = new Intent(this,MusicService.class);
//        startService(intent);
//        bindService(intent,sc,this.BIND_AUTO_CREATE);
//    }
//    public Handler handler = new Handler();
//    public Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
////            if (MusicService.mediaPlayer.isPlaying()) {
////
////            }
//            totalTime.setText(time.format(MusicService.mediaPlayer.getDuration()));
//            leftTime.setText(time.format(MusicService.mediaPlayer.getDuration() - MusicService.mediaPlayer.getCurrentPosition()));
//            seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
//            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                @Override
//                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    if (fromUser) {
//                        MusicService.mediaPlayer.seekTo(seekBar.getProgress());
//                    }
//                }
//
//                @Override
//                public void onStartTrackingTouch(SeekBar seekBar) {
//
//                }
//
//                @Override
//                public void onStopTrackingTouch(SeekBar seekBar) {
//
//                }
//            });
//            handler.postDelayed(runnable,100);
//        }
//    };
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (isApplicationBroughtToBackground()) {
//            MusicService.isReturnTo = 1;
//        }
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        MusicService.isReturnTo = 1;
//    }
//
//    @Override
//    protected void onResume() {
//        verifyStoragePermission(this);
//        seekBar.setProgress(MusicService.mediaPlayer.getCurrentPosition());
//        seekBar.setMax(MusicService.mediaPlayer.getDuration());
//        handler.post(runnable);
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        unbindService(sc);
//        super.onDestroy();
//    }
//
//    private void quit() {
//        handler.removeCallbacks(runnable);
//        unbindService(sc);
//        try {
//            finish();
//            System.exit(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void changeStop() {
//        seekBar.setProgress(0);
//    }
//
////    private void changePlay() {
////        if (MusicService.mediaPlayer.isPlaying()) {
////        }
////    }
//
//    @RequiresApi(api = Build.VERSION_CODES.Q)
//    private Boolean isApplicationBroughtToBackground() {
//        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
//        if (!tasks.isEmpty()) {
//            ComponentName topActivity = tasks.get(0).topActivity;
//            if (!topActivity.getPackageName().equals(getPackageName())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static final int REQUEST_EXTERNAL_STORAGE = 1;
//    private static String[] PERMISSIONS_STORAGE = {
//            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.WRITE_EXTERNAL_STORAGE
//    };
//    public static void verifyStoragePermission(Activity activity) {
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_STORAGE,
//                    REQUEST_EXTERNAL_STORAGE
//            );
//        }
//    }
}
