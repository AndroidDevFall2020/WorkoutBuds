package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    public static final String TAG = "ChatActivity";

    List<ParseObject> chats;
    ParseObject groupData;
    ChatAdapter chatAdapter;

    private RecyclerView rvChats;
    private TextView tvChatName;
    private Button btnInfo;
    private Button btnSend;
    private EditText etItem;

    Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            loadChats();
            handler.postDelayed(this, 2000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        rvChats = findViewById(R.id.rvChats);
        tvChatName = findViewById(R.id.tvChatName);
        btnInfo = findViewById(R.id.btnInfo);
        btnSend = findViewById(R.id.btnSend);
        etItem = findViewById(R.id.etItem);

        chats = new ArrayList<>();
        chatAdapter = new ChatAdapter(ChatActivity.this, chats);

        rvChats.setAdapter(chatAdapter);
        rvChats.setLayoutManager(new LinearLayoutManager(ChatActivity.this));

        groupData = Parcels.unwrap(getIntent().getParcelableExtra("groupData"));

        tvChatName.setText(groupData.getString("name"));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etItem.getText().toString();
                uploadChat(message);
                etItem.setText("");
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatActivity.this, ChatDetailsActivity.class);
                i.putExtra("groupData", Parcels.wrap(groupData));
                startActivity(i);
            }
        });


        runnableCode.run();
        loadChats();
    }

    private void loadChats() {
        String groupName = groupData.getString("name");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Chat");
        query.whereEqualTo("chatName", groupName);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Couldn't get chats");
                    return;
                }
                chatAdapter.clear();
                chatAdapter.addAll(objects);
                rvChats.scrollToPosition(objects.size()-1);
            }
        });
    }

    private void uploadChat(String message) {
        String groupName = groupData.getString("name");
        String user = ParseUser.getCurrentUser().getUsername();

        ParseObject object = new ParseObject("Chat");
        object.put("chat", message);
        object.put("chatName", groupName);
        object.put("messanger", user);
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.i(TAG, "Successfully uploaded Chat");
                    loadChats();
                } else {
                    Log.e(TAG, "Issue uploading Chat", e);
                }
            }
        });
    }

}