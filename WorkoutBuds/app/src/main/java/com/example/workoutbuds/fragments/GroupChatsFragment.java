package com.example.workoutbuds.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.workoutbuds.GroupChatAdapter;
import com.example.workoutbuds.GroupCreation;
import com.example.workoutbuds.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class GroupChatsFragment extends Fragment {

    public static final String TAG = "GroupChatsFragment";

    private RecyclerView rvGroupChats;
    private GroupChatAdapter groupChatAdapter;
    private List<ParseObject> allGroupChats;
    private Button btnCreateGroup;

    public GroupChatsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCreateGroup = view.findViewById(R.id.btnCreateGroup);
        btnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroup();
            }
        });

        rvGroupChats = view.findViewById(R.id.rvGroupChats);
        allGroupChats = new ArrayList<>();
        groupChatAdapter = new GroupChatAdapter(getContext(), allGroupChats);
        rvGroupChats.setAdapter(groupChatAdapter);
        rvGroupChats.setLayoutManager(new LinearLayoutManager(getContext()));
        queryGroups();
    }

    private void queryGroups() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("GroupChat");
        query.include("author");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with retrieving groups", e);
                    return;
                }
                allGroupChats.addAll(objects);
                groupChatAdapter.notifyDataSetChanged();
            }
        });
    }

    private void createGroup() {
        Intent i = new Intent(getContext(), GroupCreation.class);
        startActivity(i);
    }

}