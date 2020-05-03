package com.example.cmusic.view.play;

import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Created by dr-chene on @date 2020/5/3
 */
public class MusicService extends Service {

    public IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    public static int isReturnTo = 0;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    public static ObjectAnimator animator;
    public MusicService() {
        initMediaPlayer();
    }

    private void initMediaPlayer() {
        try {
            //file_path 需要自定义
            String file_path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    +"";
            mediaPlayer.setDataSource(file_path);
            mediaPlayer.prepare();
            mediaPlayer.setLooping(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int flag = 0;
    public static String which = "";
    public void playOrPause() {
        flag++;
        if (flag >= 1000 ) {
            flag = 2;
        }
        which = "pause";
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();

        }
    }

    public void stop() {
        which = "stop";
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
                mediaPlayer.seekTo(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }

    @Nullable
    @Override

    public IBinder onBind(Intent intent) {
        return binder;
    }
}
