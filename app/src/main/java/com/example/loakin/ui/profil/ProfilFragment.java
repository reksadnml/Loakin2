package com.example.loakin.ui.profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.loakin.EditProfil;
import com.example.loakin.MainActivity;
import com.example.loakin.Pengguna;
import com.example.loakin.R;
import com.example.loakin.bottomNav;
import com.example.loakin.ui.beranda.DashboardViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfilFragment extends Fragment {
    private ProfilViewModel profilViewModel;
    private LinearLayout buttonEdit, buttonLogout;
    private TextView namaAkun;
    FirebaseAuth  mFirebaseAuth;
    FirebaseFirestore mDb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.profil, container, false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        buttonEdit = root.findViewById(R.id.edit_profil);
        namaAkun = root.findViewById(R.id.kotakNama);
        buttonLogout = root.findViewById(R.id.logout);
        mFirebaseAuth = FirebaseAuth.getInstance();

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), EditProfil.class);
                startActivity(i);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                getActivity().finish();
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);

            }
        });

        mDb = FirebaseFirestore.getInstance();
        DocumentReference docRef = mDb.collection("Users").document(FirebaseAuth.getInstance().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Pengguna pengguna = documentSnapshot.toObject(Pengguna.class);
                namaAkun.setText(pengguna.nama);
            }
        });
        return root;
    }
}
