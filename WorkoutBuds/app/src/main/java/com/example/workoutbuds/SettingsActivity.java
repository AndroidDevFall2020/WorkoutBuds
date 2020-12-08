package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SettingsActivity extends AppCompatActivity {

    public static final String TAG = "SettingsActivity";
    private EditText etSchoolName;
    private TextView tvSettings;
    private EditText etClass;
    private EditText etProfilePic;
    private Button btnBack;
    private ImageView ivPicture;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        etSchoolName = findViewById(R.id.etSchoolName);
        tvSettings = findViewById(R.id.tvSettings);
        etClass = findViewById(R.id.etClass);
        etProfilePic = findViewById(R.id.etProfilePic);
        btnBack = findViewById(R.id.btnBack);
        ivPicture = findViewById(R.id.ivPicture);
        btnUpdate = findViewById(R.id.btnUpdate);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick back button");
                goMainActivity();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick update button");
                etClass.getText();
                etSchoolName.getText();
                UpdateSettings();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void UpdateSettings() {
        ParseUser user = ParseUser.getCurrentUser();
        String school = etSchoolName.getText().toString();
        String className = etClass.getText().toString();
        user.put("School", school);
        user.put("major", className);
        user.saveEventually(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Successfully got updated");
                    finish();
                } else {
                    Log.i(TAG, "Could not get updated");
                }
            }
        });
    }

}