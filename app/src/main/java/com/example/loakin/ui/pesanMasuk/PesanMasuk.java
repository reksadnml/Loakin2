package com.example.loakin.ui.pesanMasuk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Html;

import com.example.loakin.R;

import java.util.ArrayList;

public class PesanMasuk extends AppCompatActivity {

    public ArrayList<DataPesan> PesanValues;
    private RecyclerView recyclerView;
    private DataPesanAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_masuk);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#008577\">" + "Pesan Masuk" + "</font>"));

        recyclerView = findViewById(R.id.recycler_pesan_masuk);

        PesanValues = new ArrayList<>();
        PesanValues.addAll(DataPesanMasuk.getListData());

        itemAdapter = new DataPesanAdapter(this, PesanValues);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);

    }
}
