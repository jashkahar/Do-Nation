package com.example.database;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    Button login;
    TextView textView;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        login = (Button) findViewById( R.id.bt1 );
        FirebaseApp.initializeApp( MainActivity.this );
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById( R.id.et1 );
        editTextPassword = (EditText) findViewById( R.id.et2 );
        progressDialog = new ProgressDialog( this );
        addListenerOnButton();

        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }

        } );

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    startActivity( new Intent( MainActivity.this, Second.class ) );
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener( authStateListener );
    }




    private void loginUser() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if (editTextEmail.getText().toString().trim().length() < 0) {
            editTextEmail.setError( "Email is required" );
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            editTextEmail.setError( "Please Enter a Valid Email ID" );
            editTextEmail.requestFocus();
            return;
        }

        if (editTextPassword.getText().toString().trim().length() < 0) {
            editTextPassword.setError( "Password id required" );
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError( "Minimum Length of Password is 6" );
            editTextPassword.requestFocus();
            return;
        }

        progressDialog.setMessage( "Logging in please Wait..." );
        progressDialog.show();

        mAuth.signInWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText( getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG ).show();
                    Intent i = new Intent( MainActivity.this, Second.class );
                    startActivity( i );
                } else {
                    Toast.makeText( MainActivity.this, "Error: Login Failed", Toast.LENGTH_LONG ).show();
                }
                progressDialog.dismiss();
            }
        } );

    }

    public void addListenerOnButton(){
        final Context context=this;
        textView=(TextView)findViewById(R.id.tw1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }

                }).setNegativeButton("No", null).show();
        moveTaskToBack(false);
    }
}
