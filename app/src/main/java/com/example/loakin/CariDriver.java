package com.example.loakin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Button;
import android.provider.MediaStore;
import android.graphics.BitmapFactory;
import android.net.Uri;
import java.io.File;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class CariDriver extends AppCompatActivity {

    private ImageButton searchButton;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_driver);

        searchButton = findViewById(R.id.search_btn);
        image = findViewById(R.id.image);
        Button btnSearchDriver = findViewById(R.id.btnSearchDriver);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariDriver.this, MapsActivity.class);
                startActivity(i);
            }
        });
        btnSearchDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CariDriver.this, OrderActivity.class);
                startActivity(i);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        getSupportActionBar().hide();
    }

    private void chooseImage(){
        Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
    }

//    @Override
//    void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 2 && resultCode == RESULT_OK && data != null && data.data != null){
//            Uri filePath = data.data;
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                image.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }

}
