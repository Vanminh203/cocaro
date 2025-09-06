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

import java.util.Objects;

public class act_chon_muc_do extends AppCompatActivity {
    Button btnde, btnkho;
    ImageButton btnqv, btnh1; // Sửa tên biến theo XML
    TextView tv2;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up,Down;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // SỬA: Dùng đúng layout file
        setContentView(R.layout.activity_chon_muc_do); // hoặc activity_chon_muc_do nếu file tên là activity_chon_muc_do.xml

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr97), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainLayout = findViewById(R.id.backgr97); // Sửa ID theo XML mới
        an1 = (AnimationDrawable) mainLayout.getBackground();
        an1.setEnterFadeDuration(1000);
        an1.setExitFadeDuration(2000);
        an1.start();

        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);

        btnde = findViewById(R.id.btnde);
        btnkho = findViewById(R.id.btnkho);
        btnqv = findViewById(R.id.btnqv); // SỬA: Dùng đúng ID
        btnh1 = findViewById(R.id.btnh1); // SỬA: Dùng đúng ID
        tv2 = findViewById(R.id.textView); // SỬA: TextView có ID là textView trong XML

        // SỬA: Lấy đúng bundle data
        Bundle bundle = getIntent().getBundleExtra("data_2"); // Phải là data_2
        String modee = "";
        String gridd = "";
        if (bundle != null) {
            modee = bundle.getString("mode_2", "");
            gridd = bundle.getString("grid_2", "");
        }

        final String finalMode = modee;
        final String finalGrid = gridd;

        btnde.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnde.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnde.startAnimation(Down);
                    Intent it2 = new Intent(act_chon_muc_do.this, nhap_ten_ng_choi.class);
                    Bundle bd2 = new Bundle();
                    bd2.putString("mode_2", finalMode);
                    bd2.putString("grid_2", finalGrid);
                    bd2.putString("level_2", "easy"); // Thêm level
                    it2.putExtra("data_2", bd2);
                    startActivity(it2);
                }
                return true;
            }
        });

        btnkho.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnkho.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnkho.startAnimation(Down);
                    Intent it2 = new Intent(act_chon_muc_do.this, nhap_ten_ng_choi.class);
                    Bundle bd2 = new Bundle();
                    bd2.putString("mode_2", finalMode);
                    bd2.putString("grid_2", finalGrid);
                    bd2.putString("level_2", "hard"); // Thêm level
                    it2.putExtra("data_2", bd2);
                    startActivity(it2);
                }
                return true;
            }
        });

        btnqv.setOnTouchListener(new View.OnTouchListener() { // SỬA: Dùng btnqv
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

        btnh1.setOnTouchListener(new View.OnTouchListener() { // SỬA: Dùng btnh1
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnh1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnh1.startAnimation(Down);
                    Intent it10 = new Intent(act_chon_muc_do.this, MainActivity.class);
                    startActivity(it10);
                }
                return true;
            }
        });
    }
}
