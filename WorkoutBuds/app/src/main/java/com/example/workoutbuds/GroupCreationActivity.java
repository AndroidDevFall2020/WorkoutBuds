package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupCreationActivity extends AppCompatActivity {

    public static final String TAG = "GroupCreationActivity";

    private EditText etGroupName;
    private EditText etGroupDescription;
    private Button btnRegisterGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_creation);

        etGroupName = findViewById(R.id.etGroupName);
        etGroupDescription = findViewById(R.id.etGroupDescription);
        btnRegisterGroup = findViewById(R.id.btnRegisterGroup);

        btnRegisterGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etGroupName.getText().toString();
                String description = etGroupDescription.getText().toString();
                if (name == "") {
                    Toast.makeText(GroupCreationActivity.this, "Cannot leave name field blank", Toast.LENGTH_SHORT).show();
                } else {
                    createGroup(name, description);
                    finish();
                }
            }
        });
    }


    public void createGroup(String name, String description) {
        if (description == "") {
            description = "No Description";
        }
        description = "- " + description;

        Random rand = new Random();
        int image_number = rand.nextInt(3);
        int members_count = 1;
        List<String> members;
        members = new ArrayList<>();
        members.add(ParseUser.getCurrentUser().getUsername());

        ParseObject object = new ParseObject("GroupChat");
        object.put("name", name);
        object.put("description", description);
        object.put("author", ParseUser.getCurrentUser());
        object.put("members", members);
        object.put("image_number", image_number);
        object.put("members_count", members_count);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(GroupCreationActivity.this, "GroupChat Successfully created", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(GroupCreationActivity.this, "Issue Creating GroupChat", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}