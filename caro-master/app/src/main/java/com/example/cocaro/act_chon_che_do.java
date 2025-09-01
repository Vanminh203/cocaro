package com.example.cocaro;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class act_chon_che_do extends AppCompatActivity {
    Button btn1, btn2;
    ImageButton btnqv, btnh1;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up,Down;
    ImageView iv1, iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_act_chon_che_do);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr97), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainLayout = findViewById(R.id.backgr97);
        an1 = (AnimationDrawable) mainLayout.getBackground();
        an1.setEnterFadeDuration(1000);
        an1.setExitFadeDuration(2000);
        an1.start();

        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);

        btn1 = findViewById(R.id.btnnguoi);
        btn2 = findViewById(R.id.btnmay);
        btnqv = findViewById(R.id.btnqv);
        btnh1 = findViewById(R.id.btnh1);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn1.startAnimation(Up);
                    iv1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn1.startAnimation(Down);
                    iv1.startAnimation(Down);
                    Intent it1 = new Intent(act_chon_che_do.this, chon_kich_thuoc.class);
                    Bundle bd1 = new Bundle();
                    bd1.putString("mode_1","nguoi");
                    it1.putExtra("data_1",bd1);
                    startActivity(it1);
                }
                return true;
            }
        });
        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn2.startAnimation(Up);
                    iv2.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn2.startAnimation(Down);
                    iv2.startAnimation(Down);
                    Intent it1 = new Intent(act_chon_che_do.this, chon_kich_thuoc.class);
                    Bundle bd1 = new Bundle();
                    bd1.putString("mode_1","may");
                    it1.putExtra("data_1",bd1);
                    startActivity(it1);
                }
                return true;
            }
        });

        btnqv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnqv.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnqv.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
        btnh1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnh1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnh1.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
    }
}