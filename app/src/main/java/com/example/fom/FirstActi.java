package com.example.fom;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirstActi extends AppCompatActivity {

    private Button buttonStart;
    private EditText et1,et2,et3,et4;
    void checkFieldsForEmptyValues(){
        Button b = (Button) findViewById(R.id.RegisterButton);

        String s1 = et1.getText().toString();
        String s2 = et2.getText().toString();
        String s3 = et3.getText().toString();
        String s4 = et4.getText().toString();
        if(s1.equals("")|| s2.equals("")|| s3.equals("")|| s4.equals("")){
            b.setEnabled(false);
            b.setVisibility(View.INVISIBLE);
        } else {
            b.setEnabled(true);
            b.setVisibility(View.VISIBLE);
        }
    }
    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // check Fields For Empty Values
            checkFieldsForEmptyValues();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        et1 = (EditText) findViewById(R.id.editMail);
        et2 = (EditText) findViewById(R.id.editName);
        et3 = (EditText) findViewById(R.id.editNum);
        et4 = (EditText) findViewById(R.id.editDate);





        buttonStart = (Button) findViewById(R.id.RegisterButton);
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openActivity();
            }
        });
        // set listeners
        et1.addTextChangedListener(mTextWatcher);
        et2.addTextChangedListener(mTextWatcher);
        et3.addTextChangedListener(mTextWatcher);
        et4.addTextChangedListener(mTextWatcher);
        // run once to disable if empty
        checkFieldsForEmptyValues();
    }

    public void openActivity(){
        Data.getInstance().mail=et1.getText().toString();
        Data.getInstance().name=et2.getText().toString();
        Data.getInstance().phone=et3.getText().toString();
        Data.getInstance().date=et4.getText().toString();
        Intent intent = new Intent(getApplicationContext(), EmptyQ.class);
        startActivity(intent);
    }
}
