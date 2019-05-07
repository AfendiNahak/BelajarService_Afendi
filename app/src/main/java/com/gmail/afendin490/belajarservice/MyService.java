package com.gmail.afendin490.belajarservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    //deklarasi objek media player
    MediaPlayer mediaPlayer;
    public MyService() {
    }

    //membuat intent dengan return null
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //membuat method untuk mengambil media player berupa mp3
    @Override
    public void onCreate(){
        mediaPlayer = MediaPlayer.create(this, R.raw.perfect);
        mediaPlayer.setLooping(true);
    }

    //method untuk memainkan mp3
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        return START_STICKY;
    }

    //method untuk menghentikan play mp3
    @Override
    public void onDestroy(){
        mediaPlayer.stop();
    }
}
