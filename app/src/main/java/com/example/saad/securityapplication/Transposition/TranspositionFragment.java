package com.example.saad.securityapplication.Transposition;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Arrays;
import com.example.saad.securityapplication.R;

public class TranspositionFragment extends Fragment {

    EditText edit_message;
    EditText edit_key;
    Button encrypt_bt;
    Button decrypt_bt;
    String message;
    String key;
    String output_encrypt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transposition_fragment, container, false);

        edit_message = (EditText) v.findViewById(R.id.ed_message);
        edit_key = (EditText) v.findViewById(R.id.ed_key);
        encrypt_bt = (Button) v.findViewById(R.id.encrypt);

        encrypt_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = edit_message.getText().toString();
                key = edit_key.getText().toString();
                output_encrypt = increption(message, key);
                Intent intent_encrypt = new Intent(getActivity(), TranspositionMessage.class);
                intent_encrypt.putExtra("1", output_encrypt);
                startActivity(intent_encrypt);
                Toast.makeText(getActivity(), output_encrypt, Toast.LENGTH_LONG).show();
            }
        });

        decrypt_bt = (Button) v.findViewById(R.id.decrypt);
        decrypt_bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                message = edit_message.getText().toString();
                key = edit_key.getText().toString();
                String output_decrypt = decryption(message, key);
                Intent intent_decrypt = new Intent(getActivity(), TranspositionMessage.class);
                intent_decrypt.putExtra("1", output_decrypt);
                startActivity(intent_decrypt);

            }
        });
        return v;
    }

    public String increption(String message, String key) {

        String key_message = key.concat(message);

        int key_message_len = key_message.length();
        int key_len = key.length();
        int message_len = message.length();

        //check if message/key == int or not to specific num of row
        int row;
        if (message_len % key_len == 0) {
            row = (message_len / key_len) + 1;
        } else {
            row = (message_len / key_len) + 2;
        }

        char[] key_message_alphapet = key_message.toCharArray();
        char[][] matrix;
        matrix = new char[row][key_len];

        char[] key_alphapet = key.toCharArray();
        //  char [] message_alphapet=message.toCharArray();

        String alphapet = "abcdefghijklmnopqrstuvwxyz";
        char[] alphapets = alphapet.toCharArray();

        // print key
       /* for(int i=0;i<key_alphapet.length;i++){
            System.out.print(key_alphapet[i]);
        } System.out.println(" ");  */

        //fill matrix
        //  matrix[0]=key_alphapet;

        int alpha = 0; //toadd ABC
        int m = 0; //toadd TranspositionMessage
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < key_len; c++) {
                if (m < key_message_len) {
                    matrix[r][c] = key_message_alphapet[m];
                } else {
                    matrix[r][c] = alphapets[alpha];
                    alpha++;
                }
                m++;
            }
        }

        //print matrix
       /* for(int i = 0; i<row; i++){
            for(int j = 0; j<key_len; j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        } */

        Arrays.sort(key_alphapet);
        System.out.println(key_alphapet);

        String cipher = "";

        for (int i = 0; i < key_len; i++) {
            for (int c = 0; c < key_len; c++) {
                if (key_alphapet[i] == matrix[0][c]) {

                    for (int r = 1; r < row; r++) {
                        cipher += matrix[r][c];
                    }
                }
            }
        }
        return cipher;
    }

    public String decryption(String message, String key) {

        char[] key_alphapet = key.toCharArray();
        char[] message_alphapet = message.toCharArray();

        int key_len = key.length();
        int message_len = message.length();
        int row;
        if (message_len % key_len == 0) {
            row = message_len / key_len + 1;
        } else {
            row = message_len / key_len + 2;
        }

        char[][] matrix = new char[row][key_len];
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < key_len; j++) {
                matrix[i][j] = key_alphapet[j];
            }
        }

        Arrays.sort(key_alphapet);
        System.out.println(key_alphapet);

        int m = 0;
        for (int k = 0; k < key_len; k++) {
            for (int c = 0; c < key_len; c++) {
                if (key_alphapet[k] == matrix[0][c]) {
                    for (int r = 1; r < row; r++) {
                        matrix[r][c] = message_alphapet[m];
                        m++;
                    }
                }
            }
        }

        //print
      /*  for(int i = 0; i<row; i++){
            for(int j = 0; j<key_len; j++)
            {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        } */

        String plain = "";
        for (int r = 1; r < row; r++) {
            for (int c = 0; c < key_len; c++) {
                plain += matrix[r][c];
            }
        }
        return plain;
    }

}
