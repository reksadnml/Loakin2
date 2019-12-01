package com.example.loakin.ui.riwayat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loakin.CariDriver;
import com.example.loakin.R;

public class HomeFragment extends Fragment {

    private ImageView antarBarang;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.home, container, false);
        antarBarang = root.findViewById(R.id.antar_barang);

        antarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CariDriver.class);
                startActivity(i);
            }
        });
        antarBarang = root.findViewById(R.id.antar_barang);

        antarBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CariDriver.class);
                startActivity(i);
            }
        });

        return root;
    }
}