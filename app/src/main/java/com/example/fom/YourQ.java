package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class YourQ extends AppCompatActivity {

    private Button buttonStart;
    private void updateQ(String q){
        if(Data.getInstance().question1.equals("")) {
            Data.getInstance().question1 = q;
        } else if(Data.getInstance().question2.equals("")){
            Data.getInstance().question2=q;
        } else if(Data.getInstance().question3.equals("")) {
            Data.getInstance().question3 = q;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_q);
        buttonStart = (Button) findViewById(R.id.button27);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText question = (EditText) findViewById(R.id.editQ);
                updateQ(question.getText().toString());
                openFirstActivity();
            }
        });
    }

    public void openFirstActivity(){
        //Bundle extras = getIntent().getExtras();
        Intent intent = new Intent(getApplicationContext(), RecordPlayQ1.class);
//        if(extras != null)
//        {
//            intent.putExtra("NEXT_STEP", extras.getInt("Step"));
//
//        }
        Data.getInstance().step+=1;
        startActivity(intent);
    }
}
