package com.example.cocaro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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
    TextView tvten, tvboqua;
    String ten1, ten2;
    ConstraintLayout mainLayout;
    AnimationDrawable an1;
    Animation Up, Down;

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

        Up = AnimationUtils.loadAnimation(this, R.anim.up);
        Down = AnimationUtils.loadAnimation(this, R.anim.down);
        final MediaPlayer s1 = MediaPlayer.create(this, R.raw.click);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        btnnext = findViewById(R.id.btnnext);
        btn3h = findViewById(R.id.btn3h);
        btnq2 = findViewById(R.id.btnq2);
        tvten = findViewById(R.id.tvten);
        tvboqua = findViewById(R.id.tvboqua);

        // SỬA: Lấy đúng Bundle và xử lý null
        Bundle bundle = getIntent().getBundleExtra("data_2"); // Đổi từ data_2 thành data_3
        String modee = "";
        String gridd = "";
        String levell = "";

        if (bundle != null) {
            modee = bundle.getString("mode_2", ""); // Đổi từ mode_2 thành mode_3
            gridd = bundle.getString("grid_2", ""); // Đổi từ grid_2 thành grid_3
            levell = bundle.getString("level_2", ""); // Thêm level
            Log.d("NHAP_TEN", "Nhận được từ activity trước:");
            Log.d("NHAP_TEN", "modee = '" + modee + "'");
            Log.d("NHAP_TEN", "gridd = '" + gridd + "'");
            Log.d("NHAP_TEN", "levell = '" + levell + "'");
        }
        else {
            Log.e("NHAP_TEN", "Bundle data_2 is NULL!");
        }


        final String finalMode = modee;
        final String finalGrid = gridd;
        final String finalLevel = levell;

        // Thiết lập giao diện dựa trên chế độ
        if (Objects.equals(modee, "may")) {
            // Chế độ vs máy: chỉ cần 1 EditText cho người chơi
            edt1.setVisibility(View.VISIBLE);
            edt2.setVisibility(View.GONE);
            tvten.setVisibility(View.VISIBLE); // Hiển thị tiêu đề
            edt1.setHint("NHẬP TÊN CỦA BẠN");
        } else if (Objects.equals(modee, "nguoi")) {
            // Chế độ vs người: cần 2 EditText cho 2 người chơi
            edt1.setVisibility(View.VISIBLE);
            edt2.setVisibility(View.VISIBLE); // Đảm bảo edt2 được hiển thị
            tvten.setVisibility(View.VISIBLE); // Hoặc có thể ẩn: View.GONE
            edt1.setHint("NHẬP TÊN NGƯỜI CHƠI 1");
            edt2.setHint("NHẬP TÊN NGƯỜI CHƠI 2");
        }

        btnnext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnnext.startAnimation(Up);
                    s1.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnnext.startAnimation(Down);

                    ten1 = edt1.getText().toString().trim();
                    ten2 = edt2.getText().toString().trim();

                    if (ten1.isEmpty()) ten1 = "Player 1";
                    if (ten2.isEmpty()) ten2 = "Player 2";

                    Bundle bd4 = new Bundle();
                    bd4.putString("mode_4", finalMode);
                    bd4.putString("grid_4", finalGrid);
                    bd4.putString("level_4", finalLevel);
                    bd4.putString("ten_441", ten1);
                    bd4.putString("ten_442", ten2);
                    Log.d("NHAP_TEN", "Gửi đến chon_background:");
                    Log.d("NHAP_TEN", "mode_4 = '" + finalMode + "'");
                    Log.d("NHAP_TEN", "grid_4 = '" + finalGrid + "'");
                    Log.d("NHAP_TEN", "ten_441 = '" + ten1 + "'");
                    Log.d("NHAP_TEN", "ten_442 = '" + ten2 + "'");
                    Intent it4 = new Intent(nhap_ten_ng_choi.this, chon_background.class);
                    it4.putExtra("data_4", bd4);
                    startActivity(it4);
                }
                return true;
            }
        });

        btnq2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btnq2.startAnimation(Up);
                    s1.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btnq2.startAnimation(Down);
                    finish();
                }
                return true;
            }
        });

        btn3h.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn3h.startAnimation(Up);
                    s1.start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn3h.startAnimation(Down);
                    Intent it11 = new Intent(nhap_ten_ng_choi.this, MainActivity.class);
                    startActivity(it11);
                }
                return true;
            }
        });
    }
}
