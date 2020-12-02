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
import com.parse.ParseUser;

import java.util.List;

public class ChatDetailAdapter extends RecyclerView.Adapter<ChatDetailAdapter.ViewHolder> {

    public static final String TAG = "ChatDetailAdapter";

    Context context;
    List<ParseUser> members;

    public ChatDetailAdapter(Context context, List<ParseUser> members) {
        this.context = context;
        this.members = members;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_detail, parent, false);
        return new ChatDetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseUser member = members.get(position);
        holder.bind(member);
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView member_image;
        private TextView member_name;
        private TextView member_school;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            member_image = itemView.findViewById(R.id.member_image);
            member_name = itemView.findViewById(R.id.member_name);
            member_school = itemView.findViewById(R.id.member_school);
        }

        public void bind(ParseUser member) {
            String name = member.getUsername();
            String school = member.getString("School");
            ParseFile image = member.getParseFile("image");
            member_name.setText(name);
            member_school.setText(school);
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(member_image);
            }
        }
    }
}
