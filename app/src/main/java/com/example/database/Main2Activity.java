package com.example.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Main2Activity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, names, contact;
    Button create;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );

        create = (Button) findViewById( R.id.bt1 );
        FirebaseApp.initializeApp( Main2Activity.this );
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        editTextEmail = (EditText) findViewById( R.id.et );
        editTextPassword = (EditText) findViewById( R.id.et1 );
        contact = (EditText) findViewById( R.id.editContact );
        names = (EditText) findViewById( R.id.editName );

        progressDialog = new ProgressDialog( this );


        create.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        } );
    }

    private void registerUser() {

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name = names.getText().toString().trim();
        final String number = contact.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            names.requestFocus();
            Toast.makeText(this, "Please enter your First Name", Toast.LENGTH_LONG).show();
            return;
        }

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

        if (TextUtils.isEmpty(number)) {
            contact.requestFocus();
            Toast.makeText(this, "Please enter your contact details", Toast.LENGTH_LONG).show();
            return;
        }

        if (number.length() < 10) {
            contact.requestFocus();
            Toast.makeText(this, "Number Should Be of 10 Digits", Toast.LENGTH_LONG).show();
            return;
        }

        if (number.length() > 10) {
            contact.requestFocus();
            Toast.makeText(this, "Number Should Be of 10 Digits", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage( "Signing up please Wait..." );
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword( email, password ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        User user = new User(email,name,number);
                        FirebaseDatabase.getInstance().getReference("users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText( getApplicationContext(), "User Registered Successfully", Toast.LENGTH_LONG ).show();
                                        Intent a = new Intent( Main2Activity.this, Second.class );
                                        startActivity( a );
                                    } else {
                                        Toast.makeText( Main2Activity.this, "Error: Email Already Exists", Toast.LENGTH_LONG ).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            } );
                }
            }
        } );
    }

}
