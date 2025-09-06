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

public class chedo5x5may extends AppCompatActivity {
    String ten1;
    ConstraintLayout backgr;
    Animation Up,Down;
    MediaPlayer s1, s2, s3, s4, s5;
    AnimationDrawable an1;
    ImageButton obtn, xbtn, rsbtn, hbtn, btnve, btnchoilai;
    TextView tvnc;
    ImageView i0,i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24;
    //0: x; 1: o; 2: empty;
    int[] gameState ={2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2,3},{1,2,3,4},{5,6,7,8},{6,7,8,9},{10,11,12,13},{11,12,13,14},{15,16,17,18},{16,17,18,19},{20,21,22,23},{21,22,23,24},
            {0,5,10,15},{5,10,15,20},{1,6,11,16},{6,11,16,21},{2,7,12,17},{7,12,17,22},{3,8,13,18},{8,13,18,23},{4,9,14,19},{9,14,19,24},
            {5,11,17,23},{0,6,12,18},{6,12,18,24},{1,7,13,19},{3,7,11,15},{4,8,12,16},{8,12,16,20},{9,13,17,21}};
    int activePlayer = 0;
    boolean gameActive = true;
    ArrayList<Integer> lichsu1 = new ArrayList<>();
    ArrayList<Integer> listtag = new ArrayList<>();// khai bao array list
    ArrayList<ImageView> listimg = new ArrayList<>();
    ArrayList<ImageView> lichsu2 = new ArrayList<>();

    public void checkwin(){
        for (int[] winningPosition: winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[2]]==gameState[winningPosition[3]] && gameState[winningPosition[0]]!=2){
                gameActive = false;
                if (activePlayer==1){
                    tvnc.setText(ten1+" win");
                    obtn.setImageResource(R.drawable.victory);
                    s2 = MediaPlayer.create(this,R.raw.win);
                    s2.start();
                }
                else {
                    tvnc.setText("Bot win");
                    xbtn.setImageResource(R.drawable.lose);
                    s4 = MediaPlayer.create(this,R.raw.lose);
                    s4.start();
                }
            }
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    public void dropIn(View view){
        obtn = findViewById(R.id.obtn4);
        xbtn = findViewById(R.id.xbtn4);
        tvnc = findViewById(R.id.tvnc4);
        rsbtn = findViewById(R.id.rsbtn4);
        hbtn = findViewById(R.id.hbtn5);
        btnchoilai = findViewById(R.id.btnchoilai4);
        backgr = findViewById(R.id.backgr88);
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        s1 = MediaPlayer.create(this,R.raw.click);
        s2 = MediaPlayer.create(this,R.raw.win);
        s3 = MediaPlayer.create(this,R.raw.click_play);
        s4 = MediaPlayer.create(this,R.raw.lose);
        s5 = MediaPlayer.create(this,R.raw.draw);

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            s3.start();
            lichsu1.add(tappedCounter);
            lichsu2.add(counter);
            counter.setImageResource(R.drawable.x1);
            xbtn.setVisibility(View.GONE);
            obtn.setVisibility(View.VISIBLE);
            tvnc.setText("Bot's turn");
            gameState[tappedCounter]=1;
            activePlayer = 1;
            checkwin();
            if (!gameActive){
                return;
            }
            listtag.remove(Integer.valueOf(tappedCounter+""));
            if (listtag.isEmpty()){
                gameActive=false;
                tvnc.setText("HÒA");
                xbtn.setVisibility(View.GONE);
                obtn.setVisibility(View.VISIBLE);
                obtn.setImageResource(R.drawable.draw);
                s5.start();
                return;
            }
            activePlayer=1;
//            int irandom = listtag.get(listtag.size());
            int irandom = (int) Math.floor(Math.random()*(listtag.size()));
//            tvnc.setText(irandom+"");
            int Bot = listtag.get(irandom);

            listimg.get(Bot).setImageResource(R.drawable.o1);

            obtn.setVisibility(View.GONE);
            xbtn.setVisibility(View.VISIBLE);
            lichsu1.add(Bot);
            lichsu2.add(listimg.get(Bot));
            tvnc.setText(ten1+" 's turn");
            gameState[Bot]=0;
            activePlayer = 0;
            checkwin();
            if (!gameActive){
                return;
            }
            listtag.remove(Integer.valueOf(Bot+""));
            if (listtag.isEmpty()){
                gameActive=false;
                tvnc.setText("HÒA");
                xbtn.setVisibility(View.GONE);
                obtn.setVisibility(View.VISIBLE);
                obtn.setImageResource(R.drawable.draw);
                s5.start();
                return;
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
                        Toast.makeText(chedo5x5may.this, "Chưa đi nước cờ nào !", Toast.LENGTH_SHORT).show();
                    } else if (!gameActive) {
                        Toast.makeText(chedo5x5may.this, "Đã có người chiến thắng, thử chơi lại từ đầu đi !!!", Toast.LENGTH_SHORT).show();
                    } else {
                        gameState[lichsu1.get(lichsu1.size()-1)]=2;
                        gameState[lichsu1.get(lichsu1.size()-2)]=2;
                        ImageView counter1 = lichsu2.get(lichsu2.size()-1);
                        ImageView counter2 = lichsu2.get(lichsu2.size()-2);
                        counter1.setImageResource(R.drawable.png1);
                        counter2.setImageResource(R.drawable.png1);
                        lichsu1.remove(lichsu1.size()-2);
                        lichsu1.remove(lichsu1.size()-1);
                        lichsu2.remove(lichsu2.size()-2);
                        lichsu2.remove(lichsu2.size()-1);
                        activePlayer = 0;
                        obtn.setVisibility(View.GONE);
                        xbtn.setVisibility(View.VISIBLE);
                        tvnc.setText(ten1+" 's turn");
                    }
                }
                return true;
            }
        });

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
                    listtag.clear();
                    for (int j= 0; j<25;j++){
                        listtag.add(j);
                    }
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
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chedo5x5may);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr88), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for (int j= 0; j<25;j++){
            listtag.add(j);
        }//them cac tag vao array list
        obtn = findViewById(R.id.obtn4);
        obtn.setVisibility(View.GONE);
        rsbtn = findViewById(R.id.rsbtn4);
        hbtn = findViewById(R.id.hbtn5);
        btnve = findViewById(R.id.btnve4);
        backgr = findViewById(R.id.backgr88);
        tvnc = findViewById(R.id.tvnc4);
        i0 = findViewById(R.id.d1);
        i1 = findViewById(R.id.d2);
        i2 = findViewById(R.id.d3);
        i3 = findViewById(R.id.d4);
        i4 = findViewById(R.id.d5);
        i5 = findViewById(R.id.d6);
        i6 = findViewById(R.id.d7);
        i7 = findViewById(R.id.d8);
        i8 = findViewById(R.id.d9);
        i9 = findViewById(R.id.d10);
        i10 = findViewById(R.id.d11);
        i11 = findViewById(R.id.d12);
        i12 = findViewById(R.id.d13);
        i13 = findViewById(R.id.d14);
        i14 = findViewById(R.id.d15);
        i15 = findViewById(R.id.d16);
        i16 = findViewById(R.id.d17);
        i17 = findViewById(R.id.d18);
        i18 = findViewById(R.id.d19);
        i19 = findViewById(R.id.d20);
        i20 = findViewById(R.id.d21);
        i21 = findViewById(R.id.d22);
        i22 = findViewById(R.id.d23);
        i23 = findViewById(R.id.d24);
        i24 = findViewById(R.id.d25);
        listimg.add(i0);listimg.add(i1);listimg.add(i2);listimg.add(i3);listimg.add(i4);listimg.add(i5);listimg.add(i6);listimg.add(i7);listimg.add(i8);
        listimg.add(i9);listimg.add(i10);listimg.add(i11);listimg.add(i12);listimg.add(i13);listimg.add(i14);listimg.add(i15);listimg.add(i16);listimg.add(i17);
        listimg.add(i18);listimg.add(i19);listimg.add(i20);listimg.add(i21);listimg.add(i22);listimg.add(i23);listimg.add(i24);
        ten1 = getIntent().getBundleExtra("bd99").getString("tenn1");
        tvnc.setText(ten1+" 's TURN");
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        s1 = MediaPlayer.create(this,R.raw.click);
        s2 = MediaPlayer.create(this,R.raw.win);
        s3 = MediaPlayer.create(this,R.raw.click_play);
        s4 = MediaPlayer.create(this,R.raw.lose);
        s5 = MediaPlayer.create(this,R.raw.draw);
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
        hbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_DOWN){
                    hbtn.startAnimation(Up);
                    s1.start();
                } else if (event.getAction()==MotionEvent.ACTION_UP) {
                    hbtn.startAnimation(Down);
                    Intent i89t = new Intent(chedo5x5may.this,MainActivity.class);
                    startActivity(i89t);
                }
                return true;
            }
        });
    }
}