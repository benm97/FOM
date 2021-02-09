package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AllQ extends AppCompatActivity {

    private Button q1;
    private Button q2;
    private Button q3;
    private Button q4;
    private Button q5;
    private Button q6;
    private Button q7;
    private Button q8;
    private Button q9;
    private Button q10;
    private Button adding;
    private void updateQ(String q){
        q=q.replace("Recommended question: ","");
        if(Data.getInstance().question1.equals("")) {
            Data.getInstance().question1 = q;
        } else if(Data.getInstance().question2.equals("")){
                Data.getInstance().question2=q;
        } else if(Data.getInstance().question3.equals("")) {
            Data.getInstance().question3 = q;
        }
    }
    @Override
    public void onBackPressed() {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_q);


        q1 = (Button) findViewById(R.id.button5);
        q1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateQ((String)q1.getText());
                all_quest_acti(false);
            }
        });

        q2 = (Button) findViewById(R.id.button6);
        q2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateQ((String)q2.getText());
                all_quest_acti(false);
            }
        });

        q3 = (Button) findViewById(R.id.button7);
        q3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                updateQ((String)q3.getText());
                all_quest_acti(false);
            }
        });

//        q4 = (Button) findViewById(R.id.button8);
//        q4.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q4.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q5 = (Button) findViewById(R.id.button9);
//        q5.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q5.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q6 = (Button) findViewById(R.id.button10);
//        q6.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q6.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q7 = (Button) findViewById(R.id.button11);
//        q7.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//                updateQ((String)q7.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q8 = (Button) findViewById(R.id.button12);
//        q8.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q8.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q9 = (Button) findViewById(R.id.button13);
//        q9.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q9.getText());
//                all_quest_acti(false);
//            }
//        });
//
//        q10 = (Button) findViewById(R.id.button14);
//        q10.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
//                updateQ((String)q10.getText());
//                all_quest_acti(false);
//            }
//        });

        adding = (Button) findViewById(R.id.button22);
        adding.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                all_quest_acti(true);
            }
        });


    }


     public void all_quest_acti(boolean isNewQ){
         //Bundle extras = getIntent().getExtras();
         Intent intent;
         if(isNewQ){
             intent = new Intent(getApplicationContext(), YourQ.class);
         }else{
             Data.getInstance().step+=1;
             intent = new Intent(getApplicationContext(), RecordPlayQ1.class);
         }

//         if(extras != null)
//         {
//             intent.putExtra("NEXT_STEP", extras.getInt("Step"));
//
//         }

         startActivity(intent);
     }
}
