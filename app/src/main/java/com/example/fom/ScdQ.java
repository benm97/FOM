package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ScdQ extends AppCompatActivity {

    private Button q2;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scd_q);

        q2 = (Button) findViewById(R.id.button3);
        q2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AllQ.class);
                intent.putExtra("Step", 2);
                startActivity(intent);
            }
        });
    }
}
