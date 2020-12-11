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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.workoutbuds.Adapters.PostAdapter;
import com.example.workoutbuds.Adapters.ProfilePostAdapter;
import com.example.workoutbuds.R;
import com.example.workoutbuds.SettingsActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";

    ParseUser user;
    List<ParseObject> posts;
    ProfilePostAdapter profilePostAdapter;

    private com.mikhaellopez.circularimageview.CircularImageView ivProfileImage;
    private TextView tvProfileUsername;
    private TextView tvProfileSchool;
    private TextView tvProfileMajor;
    private ImageView ivSettings;
    private RecyclerView rvProfilePosts;
    private TextView tvProfileSquatsInt;
    private TextView tvProfilePushUpsInt;
    private TextView tvProfileBenchPressInt;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();

        populateView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = ParseUser.getCurrentUser();

        user.fetchInBackground();

        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        tvProfileUsername = view.findViewById(R.id.tvProfileUsername);
        tvProfileSchool = view.findViewById(R.id.tvProfileSchool);
        tvProfileMajor = view.findViewById(R.id.tvProfileMajor);
        rvProfilePosts = view.findViewById(R.id.rvProfilePosts);
        tvProfileSquatsInt = view.findViewById(R.id.tvProfileSquatsInt);
        tvProfilePushUpsInt = view.findViewById(R.id.tvProfilePushUpsInt);
        tvProfileBenchPressInt = view.findViewById(R.id.tvProfileBenchPressInt);
        ivSettings = view.findViewById(R.id.ivSettings);

        populateView();

        posts = new ArrayList<>();
        profilePostAdapter = new ProfilePostAdapter(getContext(), posts);
        rvProfilePosts.setAdapter(profilePostAdapter);
        rvProfilePosts.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        queryPosts();

        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "settings icon");
                goSettingsActivity();
                populateView();
            }
        });
    }

    private void populateView() {
        ParseFile image = user.getParseFile("image");
        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(ivProfileImage);
            ivProfileImage.setClipToOutline(true);
        }

        tvProfileUsername.setText(user.getUsername());
        tvProfileSchool.setText(user.getString("School"));
        tvProfileMajor.setText(user.getString("major"));
        tvProfileSquatsInt.setText("Squats: " + Integer.toString(user.getInt("squats")));
        tvProfileBenchPressInt.setText("BenchPress: " + Integer.toString(user.getInt("BenchPress")));
        tvProfilePushUpsInt.setText("PushUps: " + Integer.toString(user.getInt("PushUps")));
    }

    private void goSettingsActivity() {
        Intent i = new Intent(getContext(), SettingsActivity.class);
        startActivity(i);
    }

    private void queryPosts(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.include("author");
        query.whereEqualTo("author", user);
        query.addDescendingOrder("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with retrieving groups", e);
                    return;
                }
                Log.i(TAG, Integer.toString(objects.size()));
                posts.clear();
                posts.addAll(objects);
                profilePostAdapter.notifyDataSetChanged();
            }
        });
    }
}