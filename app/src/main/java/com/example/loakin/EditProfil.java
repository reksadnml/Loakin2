package com.example.loakin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loakin.ui.profil.ProfilFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class EditProfil extends AppCompatActivity {

    private FirebaseFirestore mDb;
    TextView titleNama;
    EditText editNama;
    EditText editNomor;
    EditText editAlamat;
    Button buttonSave;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profil);
        getSupportActionBar().setTitle("Edit Profil");

        // inisiasi databse firestore
        mDb = FirebaseFirestore.getInstance();

        titleNama = findViewById(R.id.title_nama);
        editNama = findViewById(R.id.edit_nama);
        editNomor = findViewById(R.id.edit_telepon);
        editAlamat = findViewById(R.id.edit_alamat);
        buttonSave = findViewById(R.id.button_save);

        DocumentReference docRef = mDb.collection("Users").document(FirebaseAuth.getInstance().getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Pengguna pengguna = documentSnapshot.toObject(Pengguna.class);
                titleNama.setText(pengguna.nama);
                editNama.setHint(pengguna.nama);
                editNomor.setHint(pengguna.nomorTelepon);
                editAlamat.setHint(pengguna.alamat);
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = mDb.collection("Users").document(FirebaseAuth.getInstance().getUid());
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Pengguna pengguna = documentSnapshot.toObject(Pengguna.class);
                        pengguna.nama = editNama.getText().toString();
                        pengguna.nomorTelepon = editNomor.getText().toString();
                        pengguna.alamat = editAlamat.getText().toString();
                        editProfil(pengguna.nama, pengguna.email, pengguna.alamat,
                                pengguna.nomorTelepon, FirebaseAuth.getInstance().getUid());
                    }
                });
            }
        });
    }

    private void editProfil(String nama, String email, String alamat, String nomorTelepon, String uid) {
        // insert user to firestore
        Pengguna pengguna = new Pengguna();
        pengguna.setNama(nama);
        pengguna.setEmail(email);
        pengguna.setAlamat(alamat);
        pengguna.setNomorTelepon(nomorTelepon);
        pengguna.setUserId(uid);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        mDb.setFirestoreSettings(settings);

        DocumentReference newUserRef = mDb
                .collection("Users")
                .document(uid);

        newUserRef.set(pengguna).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(EditProfil.this, ProfilFragment.class);
                startActivity(intent);
            }
        });
    }
}
