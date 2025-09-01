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
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class chon_background extends AppCompatActivity {
    ImageButton im1, im2, im3, im4, im5, im6, im7, im8, im9, im10, im11, im12, quayve1, home1;
    String modee,gridd,ten1,ten2;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up,Down;
    Button btnboqua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_background);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr98), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainLayout = findViewById(R.id.backgr98);
        an1 = (AnimationDrawable) mainLayout.getBackground();
        an1.setEnterFadeDuration(1000);
        an1.setExitFadeDuration(2000);
        an1.start();
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);
        btnboqua = findViewById(R.id.btnboqua);
//        tvbc = findViewById(R.id.tvbc);
        modee = getIntent().getBundleExtra("data_3").getString("mode_3");
        gridd = getIntent().getBundleExtra("data_3").getString("grid_3");
        ten1 = getIntent().getBundleExtra("data_3").getString("ten_331");
        ten2 = getIntent().getBundleExtra("data_3").getString("ten_332");

//        tvbc.setText(ten2);


        im1 = findViewById(R.id.imageButton01);
        im2 = findViewById(R.id.imageButton02);
        im3 = findViewById(R.id.imageButton03);
        im4 = findViewById(R.id.imageButton04);
        im5 = findViewById(R.id.imageButton05);
        im6 = findViewById(R.id.imageButton06);
        im7 = findViewById(R.id.imageButton07);
        im8 = findViewById(R.id.imageButton08);
        im9 = findViewById(R.id.imageButton09);
        im10 = findViewById(R.id.imageButton10);
        im11 = findViewById(R.id.imageButton11);
        im12 = findViewById(R.id.imageButton12);
        quayve1 = findViewById(R.id.quayve1);
        home1 = findViewById(R.id.home1);
        btnboqua.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnboqua.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnboqua.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","99");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","99");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","99");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","99");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        quayve1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    quayve1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    quayve1.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
        home1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    home1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    home1.startAnimation(Down);
                    Intent it88 = new Intent(chon_background.this, MainActivity.class);
                    startActivity(it88);
                }
                return true;
            }
        });
        im1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im1.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im1.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","1");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","1");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","1");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","1");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im2.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im2.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","2");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","2");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","2");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","2");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im3.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im3.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","3");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","3");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","3");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","3");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im4.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im4.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","4");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","4");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","4");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","4");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im5.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im5.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","5");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","5");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","5");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","5");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im6.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im6.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","6");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","6");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","6");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","6");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im7.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im7.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im7.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","7");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","7");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","7");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","7");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im8.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im8.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im8.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","8");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","8");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","8");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","8");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im9.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im9.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im9.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","9");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","9");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","9");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","9");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im10.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im10.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im10.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","10");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","10");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","10");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","10");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im11.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im11.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","11");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","11");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","11");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","11");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
        im12.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    im12.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    im12.startAnimation(Down);
                    if (modee.equals("nguoi")){
                        if (gridd.equals("x33")){
                            Intent mode3x3nguoi = new Intent(chon_background.this, act2vs2.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","12");
                            mode3x3nguoi.putExtra("bd99",bd99);
                            startActivity(mode3x3nguoi);
                        }
                        else if (gridd.equals("x55")){
                            Intent mode5x5nguoi = new Intent(chon_background.this, chedo5x5nguoi.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("tenn2",ten2);
                            bd99.putString("bck","12");
                            mode5x5nguoi.putExtra("bd99",bd99);
                            startActivity(mode5x5nguoi);
                        }
                    }
                    if (modee.equals("may")){
                        if (gridd.equals("x33")){
                            Intent mode3x3may = new Intent(chon_background.this, chedo3x3may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","12");
                            mode3x3may.putExtra("bd99",bd99);
                            startActivity(mode3x3may);

                        } else if (gridd.equals("x55")) {
                            Intent mode5x5may = new Intent(chon_background.this, chedo5x5may.class);
                            Bundle bd99 = new Bundle();
                            bd99.putString("tenn1",ten1);
                            bd99.putString("bck","12");
                            mode5x5may.putExtra("bd99",bd99);
                            startActivity(mode5x5may);
                        }
                    }
                }
                return true;
            }
        });
    }
}