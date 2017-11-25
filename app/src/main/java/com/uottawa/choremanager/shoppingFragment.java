package com.uottawa.choremanager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Raymo on 2017-11-24.
 */

public class shoppingFragment extends Fragment{
    //taken from https://www.youtube.com/watch?v=bNpWGI_hGGg

    private static final String TAG = "shoppingFragment";

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping, container, false);

        return view;
    }
    //End of citation
}
