package com.example.cocaro;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class chon_kich_thuoc extends AppCompatActivity {
    Button btn3x3, btn5x5;
    ImageButton btnq1, btn1h;
    TextView tv2;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up,Down;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_kich_thuoc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr99), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainLayout = findViewById(R.id.backgr99);
        an1 = (AnimationDrawable) mainLayout.getBackground();
        an1.setEnterFadeDuration(1000);
        an1.setExitFadeDuration(2000);
        an1.start();
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);
        btn3x3 = findViewById(R.id.btn3x3);
        btn5x5 = findViewById(R.id.btn5x5);
        btnq1 = findViewById(R.id.btnq1);
        btn1h = findViewById(R.id.btn1h);
        tv2 = findViewById(R.id.tv2);
        String modee = getIntent().getBundleExtra("data_1").getString("mode_1");
        btn3x3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn3x3.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn3x3.startAnimation(Down);
                    Intent it2 = new Intent(chon_kich_thuoc.this, act_chon_muc_do.class);
                    Bundle bd2 = new Bundle();
                    bd2.putString("mode_2",modee);
                    bd2.putString("grid_2","x33");
                    it2.putExtra("data_2",bd2);
                    startActivity(it2);
                }
                return true;
            }
        });
        btn5x5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn5x5.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn5x5.startAnimation(Down);
                    Intent it2 = new Intent(chon_kich_thuoc.this, act_chon_muc_do.class);
                    Bundle bd2 = new Bundle();
                    bd2.putString("mode_2",modee);
                    bd2.putString("grid_2","x55");
                    it2.putExtra("data_2",bd2);
                    startActivity(it2);
                }
                return true;
            }
        });
        btnq1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnq1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnq1.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
        btn1h.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn1h.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn1h.startAnimation(Down);
                    Intent it10 = new Intent(chon_kich_thuoc.this, MainActivity.class);
                    startActivity(it10);
                }
                return true;
            }
        });
    }
}