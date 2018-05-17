package com.example.saad.securityapplication.Transposition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.saad.securityapplication.R;

public class TranspositionMessage extends AppCompatActivity {

    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transposition_message);

        output = (TextView) findViewById(R.id.message);

        //Intent intent = getIntent();
        String message_out = getIntent().getStringExtra("1");

        output.setText(message_out);
    }
}

