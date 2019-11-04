package com.example.loakin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loakin.ui.recyclerViewPesanMasuk.PesanMasuk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        this.getSupportActionBar().hide();

        Button buttonMasuk = findViewById(R.id.buttonMasuk);
        Button daftarDriver = findViewById(R.id.button4);
        Button daftarPengguna = findViewById(R.id.button3);

        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, bottomNav.class);
                startActivity(i);
            }
        });

        daftarDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, PesanMasuk.class);
                startActivity(i);
            }
        });

        daftarPengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarPengguna.class);
                startActivity(i);
            }
        });
    }
}
