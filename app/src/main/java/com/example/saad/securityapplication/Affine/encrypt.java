package com.example.saad.securityapplication.Affine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saad.securityapplication.R;

import java.util.ArrayList;

public class encrypt extends AppCompatActivity {
    EditText v1;
    EditText v2;
    EditText v3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affine_encrypt);

       v1=(EditText) findViewById(R.id.pltext);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                v1.setText("");
            }
        });
       v2=(EditText) findViewById(R.id.m);

       v3=(EditText) findViewById(R.id.k);
       b1=(Button) findViewById(R.id.encry);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (v1.getText().toString().equals("")&&v2.getText().toString().equals("")&&v3.getText().toString().equals("")) {
                    Toast.makeText(encrypt.this, "please fill all fields", Toast.LENGTH_SHORT).show();

                }else{
                    AffineAlgorithm a= new AffineAlgorithm();
                    String plain = v1.getText().toString().toUpperCase();
                    String m1 = v2.getText().toString();

                    int m=Integer.parseInt(m1);
                    if(a.eculideanGCD(m,26)){
                        String key1 = v3.getText().toString();
                        int key=Integer.parseInt(key1);
                        ArrayList<Character> charch= a.prepareChars();
                        ArrayList<Long> pla= a.text(plain,charch);
                        System.out.println("plain array "+pla.toString());
                        int n= 26;
                        String res= a.solve(pla,charch,m,key,n);
                        Toast.makeText(encrypt.this, ""+res, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(encrypt.this, "GCD ("+m+",26) != 1" , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
