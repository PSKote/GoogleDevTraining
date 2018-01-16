/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.prajwal.stampcollectorapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class StampCollector extends AppCompatActivity {

    private String mStampTitle[];

    private int mStampImages[] = {R.drawable.indian,
            R.drawable.gandhi, R.drawable.vivek,
            R.drawable.bismillah, R.drawable.apj,
            R.drawable.mother};

    private int mStampCounter[];

    RecyclerView mRecyclerView;
    ArrayList<com.example.prajwal.stampcollectorapp.StampData> mStampData;

    StampAdapter mAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_collector);

        mStampTitle = getResources().
                getStringArray(R.array.stamp_title_array);

        mStampCounter = getResources().
                getIntArray(R.array.stamp_counter_array);

        setDataSet(mStampTitle,mStampImages,mStampCounter);

        mAdapter = new StampAdapter(this,mStampData);

        setUpRecyclerView();

    }

    private void setDataSet(String names[], int images[], int count[]) {
        mStampData = new ArrayList<com.example.prajwal.stampcollectorapp.StampData>();
        for (int i = 0; i < names.length; i++) {
            com.example.prajwal.stampcollectorapp.StampData instance = new com.example.prajwal.stampcollectorapp.StampData();
            instance.setmStampName(names[i]);
            instance.setmStampIcon(images[i]);
            instance.setmStampCount(count[i]);
            mStampData.add(instance);
        }
    }

    private void setUpRecyclerView(){

        //Initializing RecyclerView Object
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //Setting up a Line After Each row, to look like list
        mRecyclerView.addItemDecoration(new DividerItemDecoration(
                this,DividerItemDecoration.VERTICAL));

        //Setting up the Layout Manager for RecyclerView
        mRecyclerView.setLayoutManager(new
                LinearLayoutManager(this));

        //Attaching Adapter object with RecyclerView
        mRecyclerView.setAdapter(mAdapter);

    }


}
