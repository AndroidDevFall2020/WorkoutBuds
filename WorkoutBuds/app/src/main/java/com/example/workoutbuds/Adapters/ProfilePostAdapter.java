package com.example.workoutbuds.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.workoutbuds.R;
import com.parse.ParseFile;
import com.parse.ParseObject;

import java.util.List;

public class ProfilePostAdapter extends RecyclerView.Adapter<ProfilePostAdapter.ViewHolder> {

    public static final String TAG = "ProfilePostAdapter";

    Context context;
    List<ParseObject> posts;

    public ProfilePostAdapter(Context context, List<ParseObject> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_post, parent, false);
        return new ProfilePostAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseObject post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView ivProfilePostImage;
        private TextView tvProfilePostCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            ivProfilePostImage = itemView.findViewById(R.id.ivProfilePostImage);
            tvProfilePostCaption = itemView.findViewById(R.id.tvProfilePostCaption);
        }

        public void bind(ParseObject post) {
            String username = post.getParseUser("author").getUsername();
            String caption = post.getString("caption");
            ParseFile image = post.getParseFile("image");

            tvProfilePostCaption.setText(caption);
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivProfilePostImage);
            }
        }
    }

}
