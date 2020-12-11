package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workoutbuds.Adapters.ChatDetailAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ChatDetailsActivity extends AppCompatActivity {

    public static final String TAG = "ChatDetailsActivity";

    ParseObject groupData;
    List<String> groupOccupants;
    List<ParseUser> members;
    ChatDetailAdapter chatDetailAdapter;

    private RecyclerView rv_chat_detail;
    private ImageView iv_chat_image;
    private TextView tv_chat_name;
    private TextView tv_chat_description;
    private TextView tv_add_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_details);

        rv_chat_detail = findViewById(R.id.rv_chat_detail);
        iv_chat_image = findViewById(R.id.iv_chat_image);
        tv_chat_description = findViewById(R.id.tv_chat_description);
        tv_add_info = findViewById(R.id.tv_add_info);
        tv_chat_name = findViewById(R.id.tv_chat_name);

        groupData = Parcels.unwrap(getIntent().getParcelableExtra("groupData"));

        populateView();
        members = new ArrayList<>();
        chatDetailAdapter = new ChatDetailAdapter(ChatDetailsActivity.this, members);
        rv_chat_detail.setAdapter(chatDetailAdapter);
        rv_chat_detail.setLayoutManager(new LinearLayoutManager(ChatDetailsActivity.this));
        queryUsers();
    }

    private void populateView() {
        int image_number = groupData.getInt("image_number");
        String groupName = groupData.getString("name");
        String creationTime = groupData.getCreatedAt().toString();
        String creator = groupData.getParseUser("author").getUsername();
        String description = groupData.getString("description");
        if (image_number == 0) {
            iv_chat_image.setImageResource(R.drawable.orangegradient);
        } else if (image_number == 1) {
            iv_chat_image.setImageResource(R.drawable.purplegradient);
        } else {
            iv_chat_image.setImageResource(R.drawable.greengradient);
        }
        tv_chat_name.setText(groupName);
        tv_chat_description.setText(description);
        tv_add_info.setText("Created at " + creationTime + ", by " + creator);
        groupOccupants = groupData.getList("members");
    }

    private void queryUsers() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereContainedIn("username", groupOccupants);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e != null) {
                    Log.i(TAG, "Issue querying user");
                    return;
                }
                members.addAll(objects);
                Log.i(TAG, Integer.toString(members.size()) + Integer.toString(groupOccupants.size()));
                chatDetailAdapter.notifyDataSetChanged();
            }
        });
    }

}