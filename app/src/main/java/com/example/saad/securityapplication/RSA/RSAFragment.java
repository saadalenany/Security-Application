package com.example.saad.securityapplication.RSA;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.saad.securityapplication.R;

public class RSAFragment extends Fragment {

    EditText plainTextField ;
    Button encryptButton;
    TextView encryptedWord , decryptedWord , PWord , QWord , NWord , ZWord , EWord , DWord;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rsa_fragment, container, false);

        plainTextField = (EditText) v.findViewById(R.id.plain_text);
        encryptButton = (Button) v.findViewById(R.id.encrypt_button);
        encryptedWord = (TextView) v.findViewById(R.id.encrypted_text);
        decryptedWord = (TextView) v.findViewById(R.id.decrypted_text);
        PWord = (TextView) v.findViewById(R.id.p);
        QWord = (TextView) v.findViewById(R.id.q);
        NWord = (TextView) v.findViewById(R.id.n);
        ZWord = (TextView) v.findViewById(R.id.z);
        EWord = (TextView) v.findViewById(R.id.e);
        DWord = (TextView) v.findViewById(R.id.d);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!plainTextField.getText().toString().equals("")) {
                    String plain = plainTextField.getText().toString();
                    setData(plain);
                }
            }
        });
        return v;
    }

    private void setData(String plain) {
        RSAAlgorithm a = new RSAAlgorithm();
        a.setPlainText(plain);
        a.solve();
        if (a.getDecryptedText().contains("Can't")) {
            encryptedWord.setTextColor(Color.parseColor("#FF3333"));
            decryptedWord.setTextColor(Color.parseColor("#FF3333"));
        } else {
            encryptedWord.setTextColor(Color.parseColor("#5555FF"));
            decryptedWord.setTextColor(Color.parseColor("#55FF55"));
        }
        encryptedWord.setText("Encrypted : " + a.getEncryptedText());
        decryptedWord.setText("Decrypted : " + a.getDecryptedText());
        PWord.setText("P \"generated\" : " + a.getPNumber());
        QWord.setText("Q \"generated\" : " + a.getQNumber());
        NWord.setText("N ( P * Q ) : " + a.getNNumber());
        ZWord.setText("Z ((P - 1) * (Q - 1)) : " + a.getZNumber());
        EWord.setText("E \"generated knowing GCD(E,Z) = 1\" : " + a.getENumber());
        DWord.setText("D \"MMI calculated\" : " + a.getDNumber());
    }

}
