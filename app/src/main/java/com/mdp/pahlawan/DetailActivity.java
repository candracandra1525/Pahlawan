package com.mdp.pahlawan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivFoto;
    private TextView tvNama, tvTentang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivFoto = findViewById(R.id.iv_foto);
        tvNama = findViewById(R.id.tv_nama);
        tvTentang = findViewById(R.id.tv_tentang);

        Intent intent = getIntent();
        String nama = intent.getStringExtra("varNama");
        String tentang = intent.getStringExtra("varTentang");
        String foto = intent.getStringExtra("varFoto");

        setTitle(nama);
        tvNama.setText(nama);
        tvTentang.setText(tentang);
        Glide.with(this)
                .load(foto)
                .into(ivFoto);

    }
}