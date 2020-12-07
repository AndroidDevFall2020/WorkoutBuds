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

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public static final String TAG = "PostAdapter";

    Context context;
    List<ParseObject> posts;

    public PostAdapter(Context context, List<ParseObject> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostAdapter.ViewHolder(view);
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

        private TextView tvPostUsername;
        private ImageView ivPostImage;
        private TextView tvPostCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPostUsername = itemView.findViewById(R.id.tvPostUsername);
            ivPostImage = itemView.findViewById(R.id.ivPostImage);
            tvPostCaption = itemView.findViewById(R.id.tvPostCaption);
        }

        public void bind(ParseObject post) {
            String username = post.getParseUser("author").getUsername();
            String caption = post.getString("caption");
            ParseFile image = post.getParseFile("image");
            tvPostUsername.setText(username);
            tvPostCaption.setText(caption);
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPostImage);
                ivPostImage.setClipToOutline(true);
            }
        }
    }
}
