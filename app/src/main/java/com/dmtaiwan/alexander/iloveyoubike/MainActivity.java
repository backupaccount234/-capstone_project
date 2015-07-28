package com.dmtaiwan.alexander.iloveyoubike;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onListItemClick(int position) {
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                Intent intent = new Intent(this, StationListActivity.class);
                startActivity(intent);
                break;
            case 3:
                break;
            default:
                break;
        }
    }
}