package com.example.saad.securityapplication.RSA;

import java.util.ArrayList;

/**
 * @author Saad
 */
public class RSAAlgorithm {

    private final int PrimeNumbersRange = 100;
    private final int unlikelyNumber = 2;
    private final int ascii_size = 127;
    private String plainText;
    private String encryptedText;
    private String decryptedText;
    private long PNumber;
    private long QNumber;
    private long NNumber;
    private long ZNumber;
    private long ENumber;
    private long DNumber;
//    static ArrayList<Character> chars = new ArrayList<>();

    public void solve() {
        long P, Q, N, Z, E, D;
//        String plainText = "HELP/ldkj;:359 oiu+-";
//        chars = a.prepareChars();
//        System.out.println("chars " + chars);
        P = generateP();
        Q = generateQ();
        N = calculateN(P, Q);
        Z = calculateZ(P, Q);
        E = calculateE(Z);
        D = modularMultiplicativeInverse(E, Z);
        PNumber = P;
        QNumber = Q;
        NNumber = N;
        ZNumber = Z;
        ENumber = E;
        DNumber = D;
        System.out.println("P " + P + "\nQ " + Q + "\nN " + N + "\nZ " + Z + "\nE " + E + "\nD \"MMI\" " + D);
        if (D == Q) {
            System.err.println("Constraint : D equal Q");
            encryptedText = "Can't be encrypted D equal Q";
            decryptedText = "Can't be decrypted D equal Q";
        } else {
            ArrayList<Long> encrypted = encryptMessage(plainText, E, N);
            encryptedText = encrypted.toString();
            System.out.println("Encrypted " + encryptedText);
            decryptedText = decryptMessage(encrypted, D, N);
            System.out.println("Decrypted " + decryptedText);
            if (!decryptedText.equals(plainText)) {
                solve();
            }
        }
    }

    private long calculateN(long P, long Q) {
        return P * Q;
    }

    private long calculateZ(long P, long Q) {
        return (P - 1) * (Q - 1);
    }

    private long generateP() {
        while (true) {
            int p = (int) (Math.random() * PrimeNumbersRange);
            if (isPrime(p) && p > unlikelyNumber) {
                return p;
            }
        }
    }

    private long generateQ() {
        while (true) {
            int q = (int) (Math.random() * PrimeNumbersRange);
            if (isPrime(q) && q > unlikelyNumber) {
                return q;
            }
        }
    }

    private boolean isPrime(int n) {
        int i;
        for (i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private long calculateE(long Z) {
        while (true) {
            long e = (int) (Math.random() * Z);
            if (eculideanGCD(e, Z)) {
                return e;
            }
        }
    }

    private boolean eculideanGCD(long M, long N) {
        long Reminder;
        while (N != 0) {
            Reminder = M;
            M = N;
            N = Reminder % N;
        }
        return M == 1;
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

    private ArrayList<Long> encryptMessage(String plainText, long E, long N) {
        ArrayList<Long> ecrypted = new ArrayList<>();
        for (int i = 0; i < plainText.length(); i++) {
            //C = (P^E) MOD N
            long P = plainText.charAt(i);
//            long c = (p ^ E) % N;
            long c = check(P, E, N);
            ecrypted.add(c);
        }
        return ecrypted;
    }

    private String decryptMessage(ArrayList<Long> encrypted, long D, long N) {
        String decrypted = "";
        for (int i = 0; i < encrypted.size(); i++) {
            //P = (C^D) MOD N
            long C = encrypted.get(i);
//            long p = (C ^ D) % N;
            long p = check(C, D, N);
            decrypted += (char) (p % ascii_size);
        }
        return decrypted;
    }

    private ArrayList<Character> prepareChars() {
        ArrayList<Character> chars = new ArrayList<>();
        char c = 'A';
        while (c <= 'Z') {
            chars.add(c);
            c++;
        }
        return chars;
    }

    public long check(long constant, long oss, long n) {
        long hold = oss;
        ArrayList<Integer> rs = new ArrayList<>();
        if (hold % 2 != 0) {
            rs.add(1);
            hold--;
        }
        while (hold > 0) {
//            if (hold >= 8) {
//                rs.add(8);
//                hold -= 8;
//            } else
            if (hold >= 4) {
                rs.add(4);
                hold -= 4;
            } else {
                rs.add(2);
                hold -= 2;
            }
        }
        long climax = 1;
        for (int i = 0; i < rs.size(); i++) {
            int item = rs.get(i);
            long x = (long) Math.pow(constant, item);
            x %= n;
            climax *= x;
            climax %= n;
        }
        climax %= n;
        return climax;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public String getDecryptedText() {
        return decryptedText;
    }

    public long getPNumber() {
        return PNumber;
    }

    public long getQNumber() {
        return QNumber;
    }

    public long getNNumber() {
        return NNumber;
    }

    public long getZNumber() {
        return ZNumber;
    }

    public long getENumber() {
        return ENumber;
    }

    public long getDNumber() {
        return DNumber;
    }

}
