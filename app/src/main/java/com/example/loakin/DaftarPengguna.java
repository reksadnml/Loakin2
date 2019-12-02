package com.example.loakin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loakin.model.Pengguna;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class DaftarPengguna extends AppCompatActivity {

    private EditText etEmail, etPassword, etAlamat, etNama, etTelpon;
    private Button btnDaftar;
    FirebaseAuth mFirebaseAuth;
    public FirebaseFirestore mDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_user);
        getSupportActionBar().setTitle("Daftar Pengguna");

        // inisiasi databse firestore
        mDb = FirebaseFirestore.getInstance();

        mFirebaseAuth = FirebaseAuth.getInstance();
        etNama = findViewById(R.id.daftar_nama);
        etEmail = findViewById(R.id.daftar_email);
        etPassword = findViewById(R.id.daftar_password);
        etAlamat = findViewById(R.id.daftar_alamat);
        etTelpon = findViewById(R.id.daftar_telpon);
        btnDaftar = findViewById(R.id.button_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String sNama = etNama.getText().toString();
                final String sEmail = etEmail.getText().toString();
                String sPassword = etPassword.getText().toString();
                final String sAlamat = etAlamat.getText().toString();
                final String sTelpon = etTelpon.getText().toString();

                if(sNama.isEmpty()) {
                    etNama.setError("Please enter your name");
                    etNama.requestFocus();
                } else if (sEmail.isEmpty()) {
                    etEmail.setError("Please enter your email");
                    etEmail.requestFocus();
                } else if (sPassword.isEmpty()) {
                    etPassword.setError("Please enter your password");
                    etPassword.requestFocus();
                } else if (sAlamat.isEmpty()) {
                    etAlamat.setError("Please enter your address");
                    etAlamat.requestFocus();
                } else if (sTelpon.isEmpty()) {
                    etTelpon.setError("Please enter your number");
                    etTelpon.requestFocus();
                } else if (!(sNama.isEmpty() && sEmail.isEmpty()
                        && sPassword.isEmpty() && sAlamat.isEmpty()
                        && sTelpon.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                            .addOnCompleteListener(DaftarPengguna.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(DaftarPengguna.this, "Tidak berhasil mendaftar", Toast.LENGTH_SHORT).show();
                                    } else {
                                        String uid = FirebaseAuth.getInstance().getUid();
                                        insertDataToFirestore(sNama, sEmail, sAlamat, sTelpon, uid);
                                        startActivity(new Intent(DaftarPengguna.this, bottomNav.class));
                                    }
                                }
                            });
                } else {
                    Toast.makeText(DaftarPengguna.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void insertDataToFirestore(String sNama, String sEmail, String sAlamat, String sTelpon, String uid) {
        // insert user to firestore
        Pengguna pengguna = new Pengguna();
        pengguna.setNama(sNama);
        pengguna.setEmail(sEmail);
        pengguna.setAlamat(sAlamat);
        pengguna.setNomorTelepon(sTelpon);
        pengguna.setUserId(uid);

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        mDb.setFirestoreSettings(settings);

        DocumentReference newUserRef = mDb
                .collection("Users")
                .document(uid);

        newUserRef.set(pengguna);
    }
}
