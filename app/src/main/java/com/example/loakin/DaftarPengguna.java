package com.example.loakin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DaftarPengguna extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_user);
        getSupportActionBar().setTitle("Daftar Pengguna");
    }
}
