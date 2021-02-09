package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdQ extends AppCompatActivity {

    private Button q3;
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_q);

        q3 = (Button) findViewById(R.id.button4);
        q3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), AllQ.class);
                intent.putExtra("Step", 3);
                startActivity(intent);
            }
        });
    }
}
