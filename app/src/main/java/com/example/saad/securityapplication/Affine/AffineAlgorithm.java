package com.example.saad.securityapplication.Affine;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.ContentValues.TAG;


/**
 * Created by dinagouda on 12/2/2017.
 */

public class AffineAlgorithm {
    encrypt b= new encrypt();
    int module =65;

    public ArrayList text(String plainText, ArrayList<Character> charch) {
        ArrayList<Long> plain = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i++) {
            long P = plainText.charAt(i)-module;
            Log.d("Plain ["+i+"] ",P+"");
            plain.add(P);
        }
        return plain;
    }
    public ArrayList<Character> prepareChars() {
        ArrayList<Character> chars = new ArrayList<>();
        char c = 'A';
        while (c <= 'Z') {
            chars.add(c);
            c++;
        }
        return chars;
    }

      public String solve(ArrayList<Long> pl , ArrayList<Character> charch, Integer m,Integer key,int n){
           String cipher="lkl";
          String cip = "";
          for(int i=0;i<pl.size();i++){
              long c = pl.get(i);
              long st=(m*c)+key;
              long C= st % n;
              cip+=charch.get((int) C);
          }
        return cip;
       }
    public String solveD(ArrayList<Long> pl , ArrayList<Character> charch, Integer m,Integer key,int n){
        String cipher="lkl";
        String cip = "";
        for(int i=0;i<pl.size();i++){
            long c = pl.get(i);
           long M= modularMultiplicativeInverse(m,n);
            long st=M*(c-key);
            while (st < 0){
                st += m;
            }
            Log.d(TAG, "solveD:the st is "+st);
            long C= st % n;

            cip += charch.get((int) C);
        }
        return cip;
    }
    private long modularMultiplicativeInverse(long E, long Z) {
        E = E % Z;
        for (long x = 1; x < Z; x++) {
            if ((E * x) % Z == 1) {
                return x;
            }
        }
        return E;
    }
    public boolean eculideanGCD(long M, long N) {
        long Reminder;
        while (N != 0) {
            Reminder = M;
            M = N;
            N = Reminder % N;
        }
        return M == 1;
    }


}
