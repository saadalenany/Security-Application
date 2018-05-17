package com.example.saad.securityapplication.Affine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.saad.securityapplication.R;

import java.util.ArrayList;

public class decrypt extends AppCompatActivity {
    EditText e1;
    EditText e2;
    EditText e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.affine_decrypt);
        e1=(EditText) findViewById(R.id.cipher);
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1.setText("");
            }
        });
        e2=(EditText) findViewById(R.id.M1);
        e3=(EditText) findViewById(R.id.key1);
        b1=(Button) findViewById(R.id.dec);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (e1.getText().toString().equals("")&&e2.getText().toString().equals("")&&e3.getText().toString().equals("")) {
                    Toast.makeText(decrypt.this, "please fill all fields", Toast.LENGTH_SHORT).show();
                }else{
                    AffineAlgorithm a= new AffineAlgorithm();
                    String plain = e1.getText().toString().toUpperCase();
                    String m1 = e2.getText().toString();

                    int m=Integer.parseInt(m1);
                    if(a.eculideanGCD(m,26)){
                        String key1 = e3.getText().toString();
                        int key=Integer.parseInt(key1);
                        ArrayList<Character> charch= a.prepareChars();
                        ArrayList<Long> pla= a.text(plain,charch);
                        System.out.println("encrypt array "+pla.toString());
                        int n= 26;
                        String res= a.solveD(pla,charch,m,key,n);
                        Toast.makeText(decrypt.this, ""+res , Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(decrypt.this, "GCD ("+m+",26) != 1" , Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

    }
}
