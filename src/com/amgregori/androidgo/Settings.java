/*
 * Copyright (C) 2013 Andre Gregori and Mark Garro 
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

package com.amgregori.androidgo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

/**
 * 
 * Settings activity.
 *
 */
public class Settings extends SherlockPreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
	}

	
//	@Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//	    // Set summary to be the user-description for the selected value
//	    int koRule = sharedPreferences.getString("ko","") == "0" ? Game.SITUATIONAL : Game.POSITIONAL;
//	    boolean suicideRule = sharedPreferences.getString("suicide","") == "1" ? true : false;
//	}
}
