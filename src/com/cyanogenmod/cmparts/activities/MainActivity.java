package com.cyanogenmod.cmparts.activities;

import java.util.List;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

import com.cyanogenmod.cmparts.R;


public class MainActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.cmparts);
        
        this.enableIntents();
        
    }
    
    private void enableIntents(){
    	this.enablePreference("com.android.launcher", 2);
    	this.enablePreference("com.android.spare_parts", 2);
    	this.enablePreference("com.android.development", 2);
    }
    
    private boolean checkAppAvailability(String pName){
    	boolean ret = false;
    	PackageManager manager = this.getPackageManager();
        List<ApplicationInfo> apps = manager.getInstalledApplications(  PackageManager.GET_META_DATA );
    	for(int i = 0; i < apps.size(); i++){
        	if(apps.get(i).packageName.equals(pName)){
        		ret = true;
        	}
    	}
    	return ret;
    }
    
    private void enablePreference(String pName, int cat){
    	if(this.checkAppAvailability(pName)){
    		PreferenceCategory category =(PreferenceCategory) this.getPreferenceScreen().getPreference(cat-1);
    		for(int i=0; i < category.getPreferenceCount(); i++){
    			if(category.getPreference(i).getKey().equals(pName)){
    				category.getPreference(i).setEnabled(true);
    			}
    		}
    	}	
    }
    
}

