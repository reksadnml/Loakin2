package com.example.loakin.ui.pesanMasuk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

import com.example.loakin.R;

import java.util.ArrayList;

public class DataPesanAdapter extends RecyclerView.Adapter<DataPesanAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<DataPesan> values;

    public DataPesanAdapter(Context context, ArrayList<DataPesan> values) {
        this.context = context;
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_pesan_masuk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DataPesan data = values.get(position);
        holder.tvKeteranganPesan.setText(data.keteranganPesan);
        holder.tvWaktuPesan.setText(data.waktuPesan);
        Glide.with(holder.itemView.getContext())
                .load(data.getFoto())
                .format(DecodeFormat.PREFER_ARGB_8888)
                .apply(new RequestOptions().override(50, 50))
                .into(holder.ivFoto);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKeteranganPesan;
        TextView tvWaktuPesan;
        ImageView ivFoto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKeteranganPesan = itemView.findViewById(R.id.deskripsi_pesan_masuk);
            tvWaktuPesan = itemView.findViewById(R.id.waktu_pesan_masuk);
            ivFoto = itemView.findViewById(R.id.gambar_pesan_masuk);
        }
    }
}
