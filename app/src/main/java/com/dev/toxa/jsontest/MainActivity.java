package com.dev.toxa.jsontest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    String LOG_TAG = getClass().getName() + ": ";

    TabHost tabHost;
    FragmentRequests fragmentRequests;
    FragmentContacts fragmentContacts;
    android.support.v4.app.FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentRequests = new FragmentRequests();
        fragmentContacts = new FragmentContacts();


        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabspec = tabHost.newTabSpec("Screen1");

        tabspec.setIndicator("screen 1");
        tabspec.setContent(R.id.screen_requests);
        tabHost.addTab(tabspec);
        tabspec = tabHost.newTabSpec("Screen2");
        tabspec.setIndicator("screen 2");
        tabspec.setContent(R.id.screen_contacts);
        tabHost.addTab(tabspec);
        final android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragmentRequests);
        fragmentTransaction.commit();


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                fragmentTransaction = fragmentManager.beginTransaction();
                Log.d(LOG_TAG, "Tab ID: " + s);
                if (s.equals("Screen1")) {
                    fragmentTransaction.replace(R.id.framelayout, fragmentRequests);
                } else {
                    fragmentTransaction.replace(R.id.framelayout, fragmentContacts);
                }
                fragmentTransaction.commit();
            }
        });
    }

}
