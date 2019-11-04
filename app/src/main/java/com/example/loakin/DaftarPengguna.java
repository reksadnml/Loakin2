package com.example.loakin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarPengguna extends AppCompatActivity {

    private EditText etEmail, etPassword, etAlamat, etNama, etTelpon;
    private Button btnDaftar;
    FirebaseAuth mFirebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daftar_user);
        getSupportActionBar().setTitle("Daftar Pengguna");

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
                String sNama = etNama.getText().toString();
                String sEmail = etEmail.getText().toString();
                String sPassword = etPassword.getText().toString();
                String sAlamat = etAlamat.getText().toString();
                String sTelpon = etTelpon.getText().toString();

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
}
