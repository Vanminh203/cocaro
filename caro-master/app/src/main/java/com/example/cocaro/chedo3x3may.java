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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;


import java.util.ArrayList;

public class chedo3x3may extends AppCompatActivity {
    String ten1;
    Animation Up,Down;
    ConstraintLayout backgr;
    ImageButton obtn, xbtn, rsbtn, hbtn, btnve, btnchoilai;
    TextView tvnc;
    ImageView i0,i1, i2, i3, i4, i5, i6, i7, i8;
    AnimationDrawable an1;
    MediaPlayer s1, s2, s3, s4, s5;
    //0: x; 1: o; 2: empty;
    int[] gameState ={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int activePlayer = 0;
    boolean gameActive = true;
    private final Object stateLock = new Object();
    private ExecutorService aiExecutor;
    private Handler mainHandler;
    private volatile boolean isThinking = false;
    ArrayList<Integer> lichsu1 = new ArrayList<>();
    ArrayList<Integer> listtag = new ArrayList<>();// khai bao array list
    ArrayList<ImageView> listimg = new ArrayList<>();
    ArrayList<ImageView> lichsu2 = new ArrayList<>();
    public void checkwin(){
        for (int[] winningPosition: winningPositions){
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]] &&
                    gameState[winningPosition[1]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2){
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
                rsbtn.setEnabled(false);
                return;
            }
        }
        if (listtag.isEmpty()) {
            gameActive=false;
            tvnc.setText("HÒA");
            obtn.setImageResource(R.drawable.draw);
            s5.start();
            rsbtn.setEnabled(false);
        }
    }
    @SuppressLint("SetTextI18n")
    public void dropIn(View view) {
        // Chặn nếu game kết thúc hoặc bot đang nghĩ
        if (!gameActive || isThinking) return;

        ImageView cell = (ImageView) view;
        int idx = Integer.parseInt(cell.getTag().toString());

        // ===== Người đi (UI thread) =====
        synchronized (stateLock) {
            if (gameState[idx] != 2) return;      // ô đã có quân

            if (s3 != null) s3.start();           // âm click_play nếu muốn
            cell.setImageResource(R.drawable.x1);  // đặt X
            gameState[idx] = 1;                    // người = 1
            activePlayer = 1;

            // lịch sử
            lichsu1.add(idx);
            lichsu2.add(cell);

            // XÓA THEO GIÁ TRỊ — nhớ ép kiểu Integer
            listtag.remove((Integer) idx);

            // cập nhật lượt
            xbtn.setVisibility(View.GONE);
            obtn.setVisibility(View.VISIBLE);
        }

        // Kiểm tra thắng sau nước của người
        checkwin();
        if (!gameActive) return;
        tvnc.setText("Bot's thinking...");
        // ===== Bot suy nghĩ ở background thread =====
        isThinking = true;
        aiExecutor.submit(() -> {
            Integer botMove;

            // (A) chọn nước cho bot (random) trong nền
            synchronized (stateLock) {
                if (!gameActive || listtag.isEmpty()) {
                    botMove = null; // hết ô → hoà
                } else {
                    int irandom = (int) Math.floor(Math.random() * listtag.size());
                    botMove = listtag.get(irandom);
                }
            }

            // (B) tạo cảm giác đang nghĩ (tuỳ chọn)
            SystemClock.sleep(250);

            // (C) cập nhật UI trên main thread
            final Integer finalBotMove = botMove;
            mainHandler.post(() -> {
                try {
                    if (!gameActive) return; // có thể đã reset trong lúc bot nghĩ

                    if (finalBotMove == null) {
                        // Không còn ô; checkwin sẽ xử lý hoà nếu cần
                        checkwin();
                        return;
                    }

                    synchronized (stateLock) {
                        if (gameState[finalBotMove] != 2) return; // guard

                        ImageView botCell = listimg.get(finalBotMove);
                        botCell.setImageResource(R.drawable.o1);
                        gameState[finalBotMove] = 0;   // bot = 0
                        activePlayer = 0;

                        // lịch sử
                        lichsu1.add(finalBotMove);
                        lichsu2.add(botCell);

                        // XÓA THEO GIÁ TRỊ — ép kiểu
                        listtag.remove((Integer) finalBotMove);

                        // lượt về người
                        obtn.setVisibility(View.GONE);
                        xbtn.setVisibility(View.VISIBLE);
                        tvnc.setText(ten1+" 's turn");
                    }

                    checkwin();
                } finally {
                    isThinking = false;
                }
            });
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chedo3x3may);
        aiExecutor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.backgr89), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this,R.raw.click);
        final MediaPlayer s2 = MediaPlayer.create(this,R.raw.win);
        final MediaPlayer s3 = MediaPlayer.create(this,R.raw.click_play);
        for (int j= 0; j<9;j++){
            listtag.add(j);
        }//them cac tag vao array list
        obtn = findViewById(R.id.obtn2);
        obtn.setVisibility(View.GONE);
        rsbtn = findViewById(R.id.rsbtn2);
        hbtn = findViewById(R.id.hbtn3);
        btnve = findViewById(R.id.btnve2);
        backgr = findViewById(R.id.backgr89);
        tvnc = findViewById(R.id.tvnc2);
        i0 = findViewById(R.id.c1);
        i1 = findViewById(R.id.c2);
        i2 = findViewById(R.id.c3);
        i3 = findViewById(R.id.c4);
        i4 = findViewById(R.id.c5);
        i5 = findViewById(R.id.c6);
        i6 = findViewById(R.id.c7);
        i7 = findViewById(R.id.c8);
        i8 = findViewById(R.id.c9);
        listimg.add(i0);listimg.add(i1);listimg.add(i2);listimg.add(i3);listimg.add(i4);listimg.add(i5);listimg.add(i6);listimg.add(i7);listimg.add(i8);
        ten1 = getIntent().getBundleExtra("bd99").getString("tenn1");
        tvnc.setText(ten1+" 's TURN");
        obtn = findViewById(R.id.obtn2);
        xbtn = findViewById(R.id.xbtn2);
        tvnc = findViewById(R.id.tvnc2);
        rsbtn = findViewById(R.id.rsbtn2);
        hbtn = findViewById(R.id.hbtn3);
        btnchoilai = findViewById(R.id.btnchoilai2);
        backgr = findViewById(R.id.backgr89);
        Up = AnimationUtils.loadAnimation(this,R.anim.up);
        Down = AnimationUtils.loadAnimation(this,R.anim.down);
        s5 = MediaPlayer.create(this,R.raw.draw);
        int bck1 = Integer.parseInt(getIntent().getBundleExtra("bd99").getString("bck").toString());
        rsbtn.setOnTouchListener((v,e)->{
            if (e.getAction()==MotionEvent.ACTION_DOWN){ v.startAnimation(Up); if (s1!=null) s1.start(); }
            else if (e.getAction()==MotionEvent.ACTION_UP){
                v.startAnimation(Down);
                if (isThinking) {
                    Toast.makeText(this, "Đợi bot đi xong rồi hoàn tác nhé!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                synchronized (stateLock) {
                    if (lichsu1.size() < 2) {
                        Toast.makeText(this, "Chưa đủ 2 nước để hoàn tác!", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    int last = lichsu1.size()-1;   // bot
                    int prev = last-1;             // người

                    int idxBot  = lichsu1.get(last);
                    int idxUser = lichsu1.get(prev);
                    ImageView ivBot  = lichsu2.get(last);
                    ImageView ivUser = lichsu2.get(prev);

                    // trả về trống
                    gameState[idxBot]  = 2;
                    gameState[idxUser] = 2;
                    ivBot.setImageResource(R.drawable.png1);
                    ivUser.setImageResource(R.drawable.png1);

                    // thêm lại vào danh sách ô trống
                    listtag.add(idxBot);
                    listtag.add(idxUser);
                    // Collections.sort(listtag); // nếu muốn

                    // xóa lịch sử từ cuối
                    lichsu1.remove(last);
                    lichsu1.remove(prev);
                    lichsu2.remove(last);
                    lichsu2.remove(prev);

                    // lượt người
                    activePlayer = 0;
                    obtn.setVisibility(View.GONE);
                    xbtn.setVisibility(View.VISIBLE);
                    tvnc.setText(ten1+" 's turn");
                    gameActive = true;
                }
            }
            return true;
        });

        btnchoilai.setOnTouchListener((v,e)->{
            if (e.getAction()==MotionEvent.ACTION_DOWN){ v.startAnimation(Up); if (s1!=null) s1.start(); }
            else if (e.getAction()==MotionEvent.ACTION_UP){
                v.startAnimation(Down);
                if (isThinking) {
                    Toast.makeText(this, "Đợi bot đi xong rồi hãy chơi lại!", Toast.LENGTH_SHORT).show();
                    return true;
                }
                synchronized (stateLock) {
                    // reset full
                    for (int i=0;i<gameState.length;i++) gameState[i]=2;
                    for (ImageView iv: listimg) iv.setImageResource(R.drawable.png1);
                    lichsu1.clear(); lichsu2.clear();
                    listtag.clear(); for (int j=0;j<9;j++) listtag.add(j);

                    activePlayer=0; gameActive=true;
                    obtn.setVisibility(View.GONE);
                    xbtn.setVisibility(View.VISIBLE);
                    xbtn.setImageResource(R.drawable.back1);
                    obtn.setImageResource(R.drawable.o);
                    tvnc.setText(ten1+" 's turn");
                }
            }
            rsbtn.setEnabled(true);
            return true;
        });
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
                    Intent i79t = new Intent(chedo3x3may.this,MainActivity.class);
                    startActivity(i79t);
                }
                return true;
            }
        });
    }
}