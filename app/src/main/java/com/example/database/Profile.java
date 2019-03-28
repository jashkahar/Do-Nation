package com.example.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextView fullName;
    private TextView email;
    private TextView number;
    private FirebaseAuth mAuth;
    private String currentId;
    private DatabaseReference databaseRef;
    Button donate;
    Button signout;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );

        fullName = findViewById(R.id.Name);
        email = findViewById(R.id.Email);
        number = findViewById(R.id.Number);
        mAuth = FirebaseAuth.getInstance();
        currentId = mAuth.getCurrentUser().getUid();
        databaseRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentId);

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    FirebaseUser user = mAuth.getCurrentUser();

                    String profEmail = dataSnapshot.child("email").getValue().toString();
                    String profName = dataSnapshot.child("name").getValue().toString();
                    String profNumber = dataSnapshot.child("number").getValue().toString();

//                    Glide.with(ActivityProfile.this)
//                            .load(user.getPhotoUrl())
//                            .into(pri);

                    email.setText(profEmail);
                    fullName.setText(profName);
                    number.setText(profNumber);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        signout=(Button)findViewById(R.id.logout);
        delete=(Button)findViewById(R.id.delete);
        donate=(Button)findViewById(R.id.bt);

        donate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(Profile.this, Second.class);
                startActivity(a);
            }
        } );

        signout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        } );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
                mAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        signOut();
                    }
                });
            }
        });
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Profile.this, MainActivity.class);
        startActivity(intent);
    }
}
