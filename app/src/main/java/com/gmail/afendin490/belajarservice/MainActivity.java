package com.gmail.afendin490.belajarservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.
        OnClickListener {
    //membuat objek EditText dan Button
        EditText editWaktu;
        Button tombolMain, tombolStop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi objek EditText dan Button menggunakan ID nya masing-masing,
        //kemudian men-setnya menggunakan method setOnClickListener
        editWaktu = (EditText) findViewById(R.id.et_waktu);
        tombolMain = (Button) findViewById(R.id.bt_play);
        tombolStop = (Button) findViewById(R.id.bt_stop);
        tombolMain.setOnClickListener(this);
        tombolStop.setOnClickListener(this);
    }

    // memanggil method untuk memainkan dan menghentikan mp3, method dipanggil menggunakan switch-case
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_play :
                callPlay();
                break;
            case R.id.bt_stop :
                stopPlay();
                break;
        }
    }

    //method yang digunakan untuk memainkan mp3
    public void callPlay(){
        //variabel untuk menyimpan waktu jeda
        int detik = Integer.parseInt(editWaktu.getText().toString());
        Intent intent = new Intent(MainActivity.this, MyService.class);

        //objek untuk pending waktu play
        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0 , intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //objek AlarmManager untuk membuat waktu countdown dan stopwatch didalam sistem service
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if (alarmManager !=null){
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+
                    (detik*1000),pendingIntent);
            Toast.makeText(getApplicationContext(),"Song Play After "
            +detik+" seconds !", Toast.LENGTH_LONG).show();
        }
    }

    //method yang digunakan untuk menghentikan play mp3
    public void stopPlay(){
        stopService(new Intent(MainActivity.this, MyService.class));
    }
}


