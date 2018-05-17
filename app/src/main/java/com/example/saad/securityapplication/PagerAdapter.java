package com.example.saad.securityapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.saad.securityapplication.Affine.AffineFragment;
import com.example.saad.securityapplication.RSA.RSAFragment;
import com.example.saad.securityapplication.Transposition.TranspositionFragment;

/**
 * Created by Saad on 11-Dec-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                AffineFragment tab1 = new AffineFragment();
                return tab1;
            case 1:
                RSAFragment tab2 = new RSAFragment();
                return tab2;
            case 2:
                TranspositionFragment tab3 = new TranspositionFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
