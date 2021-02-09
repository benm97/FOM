package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyQ extends AppCompatActivity {

    private Button q1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_q);


        q1 = (Button) findViewById(R.id.button2);
        q1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AllQ.class);
                intent.putExtra("Step", 1);
                startActivity(intent);
            }
        });

    }

}
