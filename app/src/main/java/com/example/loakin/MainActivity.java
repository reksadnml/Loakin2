package com.example.loakin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.input_email);
        password = findViewById(R.id.input_password);

        Button buttonMasuk = findViewById(R.id.button_masuk);
        Button daftarDriver = findViewById(R.id.button4);
        Button daftarPengguna = findViewById(R.id.button3);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(MainActivity.this, "You are Logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, bottomNav.class);
                    startActivity(i);
                } else {
                    Toast.makeText(MainActivity.this, "Please Log in", Toast.LENGTH_SHORT).show();
                }
            }
        };

        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();

                if (sEmail.isEmpty()) {
                    email.setError("Please enter your email");
                    email.requestFocus();
                } else if (sPassword.isEmpty()) {
                    password.setError("Please enter your password");
                    password.requestFocus();
                } else if (!(sEmail.isEmpty() && sPassword.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(sEmail, sPassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login error, please try again", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent i = new Intent(MainActivity.this, bottomNav.class);
                                startActivity(i);
                            }
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error Occured", Toast.LENGTH_SHORT).show();
                }
            }
        });

        daftarDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarDriverActivity.class);
                startActivity(i);

            }
        });

        daftarPengguna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DaftarPengguna.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
