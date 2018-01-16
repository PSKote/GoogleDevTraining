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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prajwal.stampcollectorapp.R;

import java.util.ArrayList;


public class StampAdapter extends
        RecyclerView.Adapter<StampAdapter.StampHolder> {

    private ArrayList<com.example.prajwal.stampcollectorapp.StampData> mStampDataAd;
    private LayoutInflater mLayoutInflater;

    public StampAdapter(Context context,
                        ArrayList<com.example.prajwal.stampcollectorapp.StampData> data) {

        mLayoutInflater = LayoutInflater.from(context);
        mStampDataAd = data;
    }


    @Override
    public StampAdapter.StampHolder onCreateViewHolder
            (ViewGroup parent, int viewType) {

        View view = mLayoutInflater.
                inflate(R.layout.recycle_row_layout,
                        parent, false);
        return new StampHolder(view);
    }

    @Override
    public void onBindViewHolder
            (StampAdapter.StampHolder holder, int position) {

        com.example.prajwal.stampcollectorapp.StampData cureentStampData = mStampDataAd.get(position);

        holder.mStampTitleHolder.setText(
                cureentStampData.getmStampName());

        holder.mStampIconHolder.setImageResource(
                cureentStampData.getmStampIcon());

        holder.mStampCounterHolder.setText(
                String.valueOf(cureentStampData.getmStampCount()));

    }

    @Override
    public int getItemCount() {
        return mStampDataAd.size();
    }


    public class StampHolder extends RecyclerView.ViewHolder {

        TextView mStampTitleHolder;
        TextView mStampCounterHolder;
        ImageView mStampIconHolder;

        Button mAddButton;
        Button mSubButton;

        private int mcounter;

        private com.example.prajwal.stampcollectorapp.StampData mEditStampData;

        public StampHolder(View itemView) {
            super(itemView);

            mStampTitleHolder = itemView.
                    findViewById(R.id.stamp_title);

            mStampIconHolder = itemView.
                    findViewById(R.id.stamp_pic);

            mStampCounterHolder = itemView.
                    findViewById(R.id.stamp_count);

            mAddButton = itemView.
                    findViewById(R.id.add_button);

            mSubButton = itemView.
                    findViewById(R.id.sub_button);

            //Use OnClick Listeners to Handle Add Button
            mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get the Current StampData Object by using
                    // getAdapterPosition() method
                    mEditStampData = mStampDataAd.
                            get(getAdapterPosition());

                    //Access the current counter value from StampData Object
                    mcounter = mEditStampData.getmStampCount();

                    //Increment the counter value by one every time
                    mcounter++;

                    //Update the new counter value in StampData object
                    mEditStampData.setmStampCount(mcounter);

                    //Update the current object in the ArrayList object too
                    mStampDataAd.set(getAdapterPosition(),mEditStampData);

                    //At Last Notify the adapter about the changes
                    // made at the current position
                    notifyItemChanged(getAdapterPosition());
                }
            });

            //Use OnClick Listeners to Handle Sub Button
            mSubButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //get the Current StampData Object by using
                    // getAdapterPosition() method
                    mEditStampData = mStampDataAd.
                            get(getAdapterPosition());

                    //Access the current counter value from StampData Object
                    mcounter = mEditStampData.getmStampCount();

                    //Check if counter Value is greater than 0
                    // then only decrement
                    if (mcounter > 0){
                        //Decrement counter value by one each time
                        mcounter--;

                        //Update the new counter value in StampData object
                        mEditStampData.setmStampCount(mcounter);

                        //Update the current object in the ArrayList object too
                        mStampDataAd.set(getAdapterPosition(),mEditStampData);

                        //At Last Notify the adapter about the changes
                        // made at the current position
                        notifyItemChanged(getAdapterPosition());
                    }
                }
            });

        }
    }
}
