package com.example.workoutbuds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.workoutbuds.Adapters.ProfilePostAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    public static final String TAG = "ProfileActivity";

    ParseUser user;
    List<ParseObject> posts;
    ProfilePostAdapter profilePostAdapter;

    private com.mikhaellopez.circularimageview.CircularImageView ivProfileImage;
    private TextView tvProfileUsername;
    private TextView tvProfileSchool;
    private TextView tvProfileMajor;
    private RecyclerView rvProfilePosts;
    private TextView tvProfileSquatsInt;
    private TextView tvProfilePushUpsInt;
    private TextView tvProfileBenchPressInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user = Parcels.unwrap(getIntent().getParcelableExtra("userData"));

        user.fetchInBackground();

        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvProfileUsername = findViewById(R.id.tvProfileUsername);
        tvProfileSchool = findViewById(R.id.tvProfileSchool);
        tvProfileMajor = findViewById(R.id.tvProfileMajor);
        rvProfilePosts = findViewById(R.id.rvProfilePosts);
        tvProfileSquatsInt = findViewById(R.id.tvProfileSquatsInt);
        tvProfilePushUpsInt = findViewById(R.id.tvProfilePushUpsInt);
        tvProfileBenchPressInt = findViewById(R.id.tvProfileBenchPressInt);
        ParseFile image = user.getParseFile("image");
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivProfileImage);
            ivProfileImage.setClipToOutline(true);
        }

        tvProfileUsername.setText(user.getUsername());
        tvProfileSchool.setText(user.getString("School"));
        tvProfileMajor.setText(user.getString("major"));
        tvProfileSquatsInt.setText("Squats: " + Integer.toString(user.getInt("squats")));
        tvProfileBenchPressInt.setText("BenchPress: " + Integer.toString(user.getInt("BenchPress")));
        tvProfilePushUpsInt.setText("PushUps: " + Integer.toString(user.getInt("PushUps")));

        posts = new ArrayList<>();
        profilePostAdapter = new ProfilePostAdapter(this, posts);
        rvProfilePosts.setAdapter(profilePostAdapter);
        rvProfilePosts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        queryPosts();
    }

    private void queryPosts() {
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