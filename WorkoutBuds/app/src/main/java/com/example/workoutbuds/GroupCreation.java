package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class GroupCreation extends AppCompatActivity {

    public static final String TAG = "GroupCreation";

    private EditText etGroupName;
    private EditText etGroupDescription;
    private Button btnRegisterGroup;

    public void showPopupWindow(final View view) {
        LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.activity_group_creation, null);

        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        boolean focusable = true;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        etGroupName = findViewById(R.id.etGroupName);
        etGroupDescription = findViewById(R.id.etGroupDescription);
        btnRegisterGroup = findViewById(R.id.btnRegisterGroup);

        btnRegisterGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etGroupName.getText().toString();
                String description = etGroupDescription.getText().toString();
                if (name == "") {
                    Toast.makeText(view.getContext(), "Cannot leave name field blank", Toast.LENGTH_SHORT).show();
                } else {
                    createGroup(name, description);
                }
            }
        });

    }

    private void createGroup(String name, String description) {
        if (description == "") {
            description = "No Description";
        }
        description = "- " + description;

    }
}