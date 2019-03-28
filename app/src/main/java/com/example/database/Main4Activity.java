package com.example.database;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Main4Activity extends AppCompatActivity {
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main4 );
        addListenerOnButton();
    }
    public void addListenerOnButton() {
        final Context context = this;
        imageButton=(ImageButton)findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Main4Activity.this).setTitle("NGO Selected")
                        .setMessage("Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, success.class);
                                startActivity(intent);
                            }

                        }).setNegativeButton("No", null).show();

            }
        });
        imageButton=(ImageButton)findViewById(R.id.imageButton2);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Main4Activity.this).setTitle("NGO Selected")
                        .setMessage("Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, success.class);
                                startActivity(intent);
                            }

                        }).setNegativeButton("No", null).show();
            }
        });
        imageButton=(ImageButton)findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Main4Activity.this).setTitle("NGO Selected")
                        .setMessage("Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, success.class);
                                startActivity(intent);
                            }

                        }).setNegativeButton("No", null).show();
            }
        });
        imageButton=(ImageButton)findViewById(R.id.imageButton4);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Main4Activity.this).setTitle("NGO Selected")
                        .setMessage("Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, success.class);
                                startActivity(intent);
                            }

                        }).setNegativeButton("No", null).show();
            }
        });
        imageButton=(ImageButton)findViewById(R.id.imageButton6);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Main4Activity.this).setTitle("NGO Selected")
                        .setMessage("Continue?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, success.class);
                                startActivity(intent);
                            }

                        }).setNegativeButton("No", null).show();
            }
        });
    }
}
