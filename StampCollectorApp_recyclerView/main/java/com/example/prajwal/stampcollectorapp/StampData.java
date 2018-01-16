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
package com.example.prajwal.stampcollectorapp;;

public class StampData {

    private String mStampName;
    private int mStampCount;
    private int mStampIcon;

    public StampData(){
        //Default constructor
    }

    // setters & getters
    public String getmStampName() {
        return mStampName;
    }

    public void setmStampName(String mStampName) {
        this.mStampName = mStampName;
    }

    public int getmStampCount() {
        return mStampCount;
    }

    public void setmStampCount(int mStampCount) {
        this.mStampCount = mStampCount;
    }

    public int getmStampIcon() {
        return mStampIcon;
    }

    public void setmStampIcon(int mStampIcon) {
        this.mStampIcon = mStampIcon;
    }
}
