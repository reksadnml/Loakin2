package com.example.loakin.ui.pesanMasuk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loakin.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    public ArrayList<DataPesan> PesanValues;
    private RecyclerView recyclerView;
    private DataPesanAdapter itemAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        notificationsViewModel =
//                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.activity_pesan_masuk, container, false);
        recyclerView = root.findViewById(R.id.recycler_pesan_masuk);

        PesanValues = new ArrayList<>();
        PesanValues.addAll(DataPesanMasuk.getListData());

        itemAdapter = new DataPesanAdapter(getContext(), PesanValues);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(itemAdapter);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}