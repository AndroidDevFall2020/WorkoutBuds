package com.example.workoutbuds.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workoutbuds.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    public static final String TAG = "ChatAdapter";

    private Context context;
    private List<ParseObject> chats;

    public ChatAdapter(Context context, List<ParseObject> chats) {
        this.context = context;
        this.chats = chats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseObject chat = chats.get(position);
        holder.bind(chat);
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public void clear() {
        chats.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<ParseObject> list) {
        chats.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView message_sent;
        private TextView message_received;
        private TextView sender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message_sent = itemView.findViewById(R.id.message_sent);
            message_received = itemView.findViewById(R.id.message_received);
            sender = itemView.findViewById(R.id.sender);
        }

        public void bind(ParseObject chat) {
            String messanger = chat.getString("messanger");
            String message = chat.getString("chat");
            String current_user = ParseUser.getCurrentUser().getUsername();

            if (messanger.equals(current_user)) {
                message_received.setVisibility(itemView.GONE);
                sender.setVisibility(itemView.GONE);
                message_sent.setVisibility(itemView.VISIBLE);
                message_sent.setText(message);
            } else {
                message_sent.setVisibility(itemView.GONE);
                sender.setVisibility(itemView.VISIBLE);
                sender.setText(messanger);
                message_received.setVisibility(itemView.VISIBLE);
                message_received.setText(message);
            }
        }
    }
}
