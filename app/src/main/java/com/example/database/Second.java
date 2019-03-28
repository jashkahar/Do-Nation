package com.example.database;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.droidsonroids.gif.GifImageView;

public class Second extends AppCompatActivity {
    GifImageView img1;
    GifImageView img2;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_second );

        img1 = findViewById( R.id.gifImageView2 );
        img2 = findViewById( R.id.gifImageView3 );

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


            img1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent a = new Intent( Second.this, Food.class );
                    startActivity( a );
                }
            } );
            img2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent( Second.this, Books.class );
                    startActivity( i );
                }
            } );
        }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item){

        switch (item.getItemId()) {
            case R.id.account:
                Intent intent = new Intent( Second.this, Profile.class );
                startActivity( intent );
                return true;
//            case R.id.delete:
//                FirebaseDatabase.getInstance().getReference( "users" )
//                        .child( FirebaseAuth.getInstance().getCurrentUser().getUid() ).removeValue();
//                mAuth.getCurrentUser().delete().addOnCompleteListener( new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        FirebaseAuth.getInstance().signOut();
//                        Intent intent = new Intent( Second.this, MainActivity.class );
//                        startActivity( intent );
//                    }
//                } );
//                return true;
            case R.id.aboutus:
                new AlertDialog.Builder( Second.this ).setTitle( "Team Do-Nation" )
                        .setMessage(R.string.team)
                        .setPositiveButton( "Ok", null ).show();
                return true;
        }
        return true;
    }




//    public void signOut(){
//        FirebaseAuth.getInstance().signOut();
//        Intent intent = new Intent(Second.this, MainActivity.class);
//        startActivity(intent);
//    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}