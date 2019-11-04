package com.example.loakin.ui.recyclerViewPesanMasuk;

import com.example.loakin.R;

import java.util.ArrayList;

public class DataPesanMasuk {
    static ArrayList<DataPesan> getListData() {
        ArrayList<DataPesan> list = new ArrayList<>();
        for (int i = 0; i<5; i++) {
            DataPesan item = new DataPesan();
            item.setKeteranganPesan("Terima kasih atas penjualan barang bekasnya");
            item.setWaktuPesan("10 menit yang lalu");
            item.setFoto(R.drawable.ic_man);
            list.add(item);
        }
        return list;
    }
}
