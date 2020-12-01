package com.example.workoutbuds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {

    public static final String TAG = "GroupChatAdapter";

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
        private RelativeLayout rlGroupContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            tvMembersCount = itemView.findViewById(R.id.tvMembersCount);
            tvGroupDescription = itemView.findViewById(R.id.tvGroupDescription);
            ivGroupImage = itemView.findViewById(R.id.ivGroupImage);
            rlGroupContainer = itemView.findViewById(R.id.rlGroupContainer);

        }

        public void bind(final ParseObject groupChat) {

            tvGroupName.setText(groupChat.getString("name"));
            tvMembersCount.setText(Integer.toString(groupChat.getInt("members_count")) + " members");
            tvGroupDescription.setText(groupChat.getString("description"));

            int image_number = groupChat.getInt("image_number");
            if (image_number == 0) {
                ivGroupImage.setImageResource(R.drawable.orangegradient);
            } else if (image_number == 1) {
                ivGroupImage.setImageResource(R.drawable.purplegradient);
            } else {
                ivGroupImage.setImageResource(R.drawable.greengradient);
            }

            rlGroupContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> members = groupChat.getList("members");
                    String username = ParseUser.getCurrentUser().getUsername();

                    if (members.contains(username)) {
                        Intent i = new Intent(context, ChatActivity.class);
                        i.putExtra("groupData", Parcels.wrap(groupChat));
                        i.putExtra("groupName", groupChat.getString("name"));
                        context.startActivity(i);
                    }

                }
            });

        }
    }

}
