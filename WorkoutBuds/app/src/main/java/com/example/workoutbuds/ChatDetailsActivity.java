package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.parse.ParseObject;

import org.parceler.Parcels;

public class ChatDetailsActivity extends AppCompatActivity {

    public static final String TAG = "ChatDetailsActivity";

    ParseObject groupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        groupData = Parcels.unwrap(getIntent().getParcelableExtra("groupData"));
    }
}