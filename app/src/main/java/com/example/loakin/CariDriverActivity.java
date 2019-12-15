package com.example.loakin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loakin.model.Maps2Activity;

import java.io.IOException;

public class CariDriverActivity extends AppCompatActivity {

    private ImageButton searchButton;
    private ImageView image;
    private EditText searchLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_driver);

        searchButton = findViewById(R.id.search_btn);
        searchLocation = findViewById(R.id.search_lokasi);
        image = findViewById(R.id.image);
        Button btnSearchDriver = findViewById(R.id.btnSearchDriver);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariDriverActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        btnSearchDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariDriverActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        if (!MapsActivity.lokasi.isEmpty()) searchLocation.setText(MapsActivity.lokasi);

        getSupportActionBar().hide();
    }

    private void chooseImage(){
        Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!MapsActivity.lokasi.isEmpty()) searchLocation.setText(MapsActivity.lokasi);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
