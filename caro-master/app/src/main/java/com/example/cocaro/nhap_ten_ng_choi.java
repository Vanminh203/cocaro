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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class nhap_ten_ng_choi extends AppCompatActivity {
    EditText edt1, edt2;
    ImageButton btnnext, btn3h, btnq2;
    TextView tvten,tvboqua;
    String ten1, ten2;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up,Down;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhap_ten_ng_choi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainLayout = findViewById(R.id.backgr1);
        an1 = (AnimationDrawable) mainLayout.getBackground();
        an1.setEnterFadeDuration(1000);
        an1.setExitFadeDuration(2000);
        an1.start();
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnnext = findViewById(R.id.btnnext);
        btn3h = findViewById(R.id.btn3h);
        btnq2 = findViewById(R.id.btnq2);
        tvten = findViewById(R.id.tvten);
        tvboqua = findViewById(R.id.tvboqua);
        String modee = getIntent().getBundleExtra("data_2").getString("mode_2");
        String gridd = getIntent().getBundleExtra("data_2").getString("grid_2");
//        edt1.setText(modee);check intent
        Intent it3 = new Intent(nhap_ten_ng_choi.this, chon_background.class);
        Bundle bd3 = new Bundle();
        if (Objects.equals(modee, "may")){
            edt1.setVisibility(View.VISIBLE);
            edt2.setVisibility(View.GONE);
        }
        if (Objects.equals(modee, "nguoi")){
            tvten.setVisibility(View.GONE);
            edt1.setHint("NHẬP TÊN NGƯỜI CHƠI 1");
            edt2.setHint("NHẬP TÊN NGƯỜI CHƠI 2");
        }
        btnnext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnnext.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnnext.startAnimation(Down);
                    if (edt1.getText().toString().isEmpty()){
                        ten1 = edt1.getText().toString();
                        ten2 = edt2.getText().toString();
//                    edt1.setText(ten1+"ngu");
                        bd3.putString("mode_3",modee);
                        bd3.putString("grid_3",gridd);
                        bd3.putString("ten_331","Player 1");
                        bd3.putString("ten_332","Player 2");
                        it3.putExtra("data_3",bd3);
                        startActivity(it3);
//                } else if (edt2.getText().toString().isEmpty()) {
//                    Toast.makeText(nhap_ten_ng_choi.this, "Nhập tên người chơi 2 !!! ", Toast.LENGTH_SHORT).show();
                    }else {
                        ten1 = edt1.getText().toString();
                        ten2 = edt2.getText().toString();
                        bd3.putString("mode_3",modee);
                        bd3.putString("grid_3",gridd);
                        bd3.putString("ten_331",ten1);
                        bd3.putString("ten_332",ten2);
                        it3.putExtra("data_3",bd3);
                        startActivity(it3);
                    }
                }
                return true;
            }
        });
        btnq2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnq2.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnq2.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
        btn3h.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btn3h.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btn3h.startAnimation(Down);
                    Intent it11 = new Intent(nhap_ten_ng_choi.this, MainActivity.class);
                    startActivity(it11);
                }
                return true;
            }
        });
    }
}