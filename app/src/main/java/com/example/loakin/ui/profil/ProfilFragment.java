package com.example.loakin.ui.profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.loakin.EditProfil;
import com.example.loakin.MainActivity;
import com.example.loakin.R;
import com.example.loakin.bottomNav;
import com.example.loakin.ui.beranda.DashboardViewModel;

public class ProfilFragment extends Fragment {
    private ProfilViewModel profilViewModel;
    private LinearLayout buttonEdit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.profil, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        buttonEdit = root.findViewById(R.id.edit_profil);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditProfil.class);
                startActivity(i);
            }
        });

//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}
