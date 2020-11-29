package com.example.workoutbuds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.w3c.dom.Text;

import java.util.List;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {

    private Context context;
    private List<ParseObject> groupChats;

    public GroupChatAdapter(Context context, List<ParseObject> posts) {
        this.context = context;
        this.groupChats = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_group_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseObject groupChat = groupChats.get(position);
        holder.bind(groupChat);
    }

    @Override
    public int getItemCount() {
        return groupChats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvGroupName;
        private TextView tvMembersCount;
        private TextView tvGroupDescription;
        private ImageView ivGroupImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            tvMembersCount = itemView.findViewById(R.id.tvMembersCount);
            tvGroupDescription = itemView.findViewById(R.id.tvGroupDescription);
            ivGroupImage = itemView.findViewById(R.id.ivGroupImage);
        }

        public void bind(ParseObject groupChat) {
            tvGroupName.setText(groupChat.getString("name"));
            tvMembersCount.setText(Integer.toString(groupChat.getInt("members_count")) + " members");
            tvGroupDescription.setText(groupChat.getString("description"));
            ParseFile image = groupChat.getParseFile("image");
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivGroupImage);
            }
        }
    }

}
