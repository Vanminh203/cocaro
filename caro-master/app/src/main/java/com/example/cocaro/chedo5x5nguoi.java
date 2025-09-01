package com.example.cocaro;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class chedo5x5nguoi extends AppCompatActivity {
    String ten1, ten2;
    ConstraintLayout backgr;
    AnimationDrawable an1;
    Animation Up,Down;
    MediaPlayer s1, s2, s3, s4;
    ImageButton obtn, xbtn, rsbtn, hbtn, btnve, btnchoilai;
    TextView tvnc;
    //0: x; 1: o; 2: empty;
    int[] gameState ={2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2,3},{1,2,3,4},{5,6,7,8},{6,7,8,9},{10,11,12,13},{11,12,13,14},{15,16,17,18},{16,17,18,19},{20,21,22,23},{21,22,23,24},
            {0,5,10,15},{5,10,15,20},{1,6,11,16},{6,11,16,21},{2,7,12,17},{7,12,17,22},{3,8,13,18},{8,13,18,23},{4,9,14,19},{9,14,19,24},
            {5,11,17,23},{0,6,12,18},{6,12,18,24},{1,7,13,19},{3,7,11,15},{4,8,12,16},{8,12,16,20},{9,13,17,21}};
    int activePlayer = 0;
    boolean gameActive = true;
    ArrayList<Integer> lichsu1 = new ArrayList<>();
    ArrayList<ImageView> lichsu2 = new ArrayList<>();
    public void dropIn(View view){
        obtn = findViewById(R.id.obtn1);
        xbtn = findViewById(R.id.xbtn1);
        tvnc = findViewById(R.id.tvnc1);
        rsbtn = findViewById(R.id.rsbtn1);
        hbtn = findViewById(R.id.hbtn2);
        btnchoilai = findViewById(R.id.btnchoilai1);
        backgr = findViewById(R.id.backgr87);
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        s1 = MediaPlayer.create(this,R.raw.click);
        s2 = MediaPlayer.create(this,R.raw.win);
        s3 = MediaPlayer.create(this,R.raw.click_play);
        s4 = MediaPlayer.create(this,R.raw.draw);
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive){
            s3.start();
            lichsu1.add(tappedCounter);
            lichsu2.add(counter);
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0){
                counter.setImageResource(R.drawable.x1);
                xbtn.setVisibility(View.GONE);
                obtn.setVisibility(View.VISIBLE);
                tvnc.setText(ten2+" 's turn");
                activePlayer = 1;
            }else {
                counter.setImageResource(R.drawable.o1);
                obtn.setVisibility(View.GONE);
                xbtn.setVisibility(View.VISIBLE);
                tvnc.setText(ten1+" 's turn");
                activePlayer = 0;
            }
            for (int[] winningPosition: winningPositions){
                if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[2]]==gameState[winningPosition[3]] && gameState[winningPosition[0]]!=2){
                    gameActive = false;
                    if (activePlayer==1){
                        tvnc.setText(ten1+" win");
                        obtn.setImageResource(R.drawable.victory);
                        s2.start();
                    }
                    else {
                        tvnc.setText(ten2+" win");
                        xbtn.setImageResource(R.drawable.victory);
                        s2.start();
                    }
                }
            }
            if (lichsu1.size()==25){
                tvnc.setText("HÒA");
                obtn.setVisibility(View.GONE);
                xbtn.setVisibility(View.VISIBLE);
                xbtn.setImageResource(R.drawable.draw);
                s4.start();
            }
        }
        rsbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    rsbtn.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    rsbtn.startAnimation(Down);
                    if (lichsu1.isEmpty() && gameActive){
                        Toast.makeText(chedo5x5nguoi.this, "Chưa đi nước cờ nào !", Toast.LENGTH_SHORT).show();
                    } else if (!gameActive) {
                        Toast.makeText(chedo5x5nguoi.this, "Đã có người chiến thắng, thử chơi lại đi !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        gameState[lichsu1.get(lichsu1.size()-1)]=2;
                        ImageView counter1 = lichsu2.get(lichsu2.size()-1);
                        counter1.setImageResource(R.drawable.png1);
                        lichsu1.remove(lichsu1.size()-1);
                        lichsu2.remove(lichsu2.size()-1);
                        if (activePlayer == 0){
                            activePlayer = 1;
                            xbtn.setVisibility(View.GONE);
                            obtn.setVisibility(View.VISIBLE);
                            tvnc.setText(ten2+" 's turn");
                        }
                        else {
                            activePlayer = 0;
                            obtn.setVisibility(View.GONE);
                            xbtn.setVisibility(View.VISIBLE);
                            tvnc.setText(ten1+" 's turn");
                        }
                    }
                }
                return true;
            }
        });
//        rsbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (lichsu1.isEmpty() && gameActive){
//                    Toast.makeText(chedo5x5nguoi.this, "Chưa đi nước cờ nào !", Toast.LENGTH_SHORT).show();
//                } else if (!gameActive) {
//                    Toast.makeText(chedo5x5nguoi.this, "Đã có người chiến thắng, thử chơi lại đi !!!", Toast.LENGTH_SHORT).show();
//                } else {
//                    gameState[lichsu1.get(lichsu1.size()-1)]=2;
//                    ImageView counter1 = lichsu2.get(lichsu2.size()-1);
//                    counter1.setImageResource(R.drawable.png1);
//                    lichsu1.remove(lichsu1.size()-1);
//                    lichsu2.remove(lichsu2.size()-1);
//                    if (activePlayer == 0){
//                        activePlayer = 1;
//                        xbtn.setVisibility(View.GONE);
//                        obtn.setVisibility(View.VISIBLE);
//                        tvnc.setText(ten2+" 's turn");
//                    }
//                    else {
//                        activePlayer = 0;
//                        obtn.setVisibility(View.GONE);
//                        xbtn.setVisibility(View.VISIBLE);
//                        tvnc.setText(ten1+" 's turn");
//                    }
//                }
//            }
//        });
        btnchoilai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnchoilai.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnchoilai.startAnimation(Down);
                    for (int i =0; i<lichsu1.size();i++){
                        gameState[lichsu1.get(i)]=2;
                        ImageView anh = lichsu2.get(i);
                        anh.setImageResource(R.drawable.png1);
                    }
                    lichsu1.clear();
                    lichsu2.clear();
                    activePlayer=0;
                    obtn.setVisibility(View.GONE);
                    xbtn.setVisibility(View.VISIBLE);
                    tvnc.setText(ten1+" 's turn");
                    obtn.setImageResource(R.drawable.o);
                    obtn.setVisibility(View.GONE);
                    xbtn.setImageResource(R.drawable.back1);
                    gameActive = true;
                }
                return true;
            }
        });
//        btnchoilai.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i =0; i<lichsu1.size();i++){
//                    gameState[lichsu1.get(i)]=2;
//                    ImageView anh = lichsu2.get(i);
//                    anh.setImageResource(R.drawable.png1);
//                }
//                lichsu1.clear();
//                lichsu2.clear();
//                activePlayer=0;
//                obtn.setVisibility(View.GONE);
//                xbtn.setVisibility(View.VISIBLE);
//                tvnc.setText(ten1+" 's turn");
//                obtn.setImageResource(R.drawable.o);
//                obtn.setVisibility(View.GONE);
//                xbtn.setImageResource(R.drawable.back1);
//                gameActive = true;
//            }
//        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chedo5x5nguoi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr87), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        obtn = findViewById(R.id.obtn1);
        obtn.setVisibility(View.GONE);
        rsbtn = findViewById(R.id.rsbtn1);
        hbtn = findViewById(R.id.hbtn2);
        btnve = findViewById(R.id.btnve1);
        backgr = findViewById(R.id.backgr87);
        tvnc = findViewById(R.id.tvnc1);
        ten1 = getIntent().getBundleExtra("bd99").getString("tenn1");
        ten2 = getIntent().getBundleExtra("bd99").getString("tenn2");
        tvnc.setText(ten1+" 's TURN");
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        s1 = MediaPlayer.create(this,R.raw.click);
        s2 = MediaPlayer.create(this,R.raw.win);
        s3 = MediaPlayer.create(this,R.raw.click_play);
        s4 = MediaPlayer.create(this,R.raw.lose);
        int bck1 = Integer.parseInt(getIntent().getBundleExtra("bd99").getString("bck").toString());
        if (bck1==99){
            an1 = (AnimationDrawable) backgr.getBackground();
            an1.setEnterFadeDuration(1000);
            an1.setExitFadeDuration(2000);
            an1.start();
        }
        if (bck1==1){
            backgr.setBackgroundResource(R.drawable.nen1);
        }
        if (bck1==2){
            backgr.setBackgroundResource(R.drawable.nen2);
        }
        if (bck1==3){
            backgr.setBackgroundResource(R.drawable.nen3);
        }
        if (bck1==4){
            backgr.setBackgroundResource(R.drawable.nen4);
        }
        if (bck1==5){
            backgr.setBackgroundResource(R.drawable.nen5);
        }
        if (bck1==6){
            backgr.setBackgroundResource(R.drawable.nen6);
        }
        if (bck1==7){
            backgr.setBackgroundResource(R.drawable.nen7);
        }
        if (bck1==8){
            backgr.setBackgroundResource(R.drawable.nen8);
        }
        if (bck1==9){
            backgr.setBackgroundResource(R.drawable.nen9);
        }
        if (bck1==10){
            backgr.setBackgroundResource(R.drawable.nen10);
        }
        if (bck1==11){
            backgr.setBackgroundResource(R.drawable.nen11);
        }
        if (bck1==12){
            backgr.setBackgroundResource(R.drawable.nen12);
        }
        btnve.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    btnve.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    btnve.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });
//        btnve.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        hbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    hbtn.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    hbtn.startAnimation(Down);
                    Intent it30 = new Intent(chedo5x5nguoi.this, MainActivity.class);
                    startActivity(it30);
                }
                return true;
            }
        });
//        hbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it30 = new Intent(chedo5x5nguoi.this, MainActivity.class);
//                startActivity(it30);
//            }
//        });
    }
}