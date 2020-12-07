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

import com.example.workoutbuds.ComposePostActivity;
import com.example.workoutbuds.Adapters.PostAdapter;
import com.example.workoutbuds.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class PostsFragment extends Fragment {

    public static final String TAG = "PostsFragment";

    private Button btnCreatePost;
    private RecyclerView rvPosts;
    private PostAdapter postAdapter;
    private List<ParseObject> posts;

    public PostsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCreatePost = view.findViewById(R.id.btnCreatePost);
        rvPosts = view.findViewById(R.id.rvPosts);

        btnCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ComposePostActivity.class);
                startActivity(i);
            }
        });

        posts = new ArrayList<>();
        postAdapter = new PostAdapter(getContext(), posts);
        rvPosts.setAdapter(postAdapter);
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryPosts();
    }

    private void queryPosts() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.include("author");
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with retrieving groups", e);
                    return;
                }
                posts.clear();
                posts.addAll(objects);
                postAdapter.notifyDataSetChanged();
            }
        });
    }

}