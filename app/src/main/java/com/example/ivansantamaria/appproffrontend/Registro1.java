package com.example.ivansantamaria.appproffrontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Rubenbros on 14/04/2017.
 */

public class Registro1 extends AppCompatActivity {

    private ImageButton profesor;
    private ImageButton alumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);
        profesor = (ImageButton) findViewById(R.id.imageButton2);
        alumno = (ImageButton) findViewById(R.id.imageButton);
        final Intent i = new Intent(this, Registro2.class);
        profesor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i.putExtra("persona", 1);
                startActivity(i);
            }
        });
        profesor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                i.putExtra("persona", 0);
                startActivity(i);
            }
        });
    }
}