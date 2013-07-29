package com.simon.practice.triviaforkids;

import android.support.v4.app.Fragment;

public class TriviaActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TriviaFragment();
    }
    
}
