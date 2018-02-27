package com.example.daniel.fittracker.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daniel.fittracker.R;
import com.example.daniel.fittracker.json.JSONHandler;

/**
 * Created by Daniel on 25.02.2018.
 */

public class FoodFragment extends Fragment {
    public FoodFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JSONHandler jsonHandler = new JSONHandler();
        jsonHandler.makeJsonRequest(this.getActivity().getApplicationContext());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fooddiary, container, false);
    }
}
